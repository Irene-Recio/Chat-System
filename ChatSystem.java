import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

//Note : C'est juste une base fonctionnelle, pas encore adaptée pour le projet

public class ChatSystem {
    private static final String SERVER_IP = "10.8.22.214"; // A voir comment on gère ça ? On change l'ip d'un pc pour le faire et hop ? Jsp
    private static final int SERVER_PORT = 4422;

    private static PrintWriter out;
    private static String myUsername;
    private static String currentPrivateTarget = null;

    private static JTextArea chatArea;
    private static JTextField inputField;
    private static JLabel chatTitleLabel;
    private static DefaultListModel<String> userListModel;
    private static JList<String> userList;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);
        out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        myUsername = showUsernameDialog(in);
        if (myUsername == null) {
            System.exit(0);
        }

        SwingUtilities.invokeLater(() -> buildChatUI());

        new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    handleMessage(line);
                }
            } catch (IOException e) {
                // Pour prévenir qu'on quitte si on ferme
                appendMessage("[Connexion perdue]");
            }
        }).start();
    }

    private static String showUsernameDialog(BufferedReader in) throws IOException {
        JDialog dialog = new JDialog();
        dialog.setTitle("Connexion");
        dialog.setSize(300, 150);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout(8, 8));

        JTextField field = new JTextField();
        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        JButton btn = new JButton("Rejoindre");

        JPanel center = new JPanel(new GridLayout(3, 1, 4, 4));
        center.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        center.add(new JLabel("Nom d'utilisateur :"));
        center.add(field);
        center.add(errorLabel); // erreur connexion etc

        dialog.add(center, BorderLayout.CENTER);
        dialog.add(btn, BorderLayout.SOUTH);

        String[] result = {null};

        //Vérif que le nom soit unique etc
        ActionListener tryConnect = e -> {
            String name = field.getText().trim();
            if (name.isEmpty()) return;
            btn.setEnabled(false);

            new Thread(() -> {
                try {
                    out.println(name);
                    String response = in.readLine();
                    SwingUtilities.invokeLater(() -> {
                        if ("USERNAME_OK".equals(response)) {
                            result[0] = name;
                            dialog.dispose();
                        } else {
                            errorLabel.setText("Nom déjà utilisé.");
                            btn.setEnabled(true);
                            field.selectAll();
                        }
                    });
                } catch (IOException ex) {
                    SwingUtilities.invokeLater(() -> errorLabel.setText("Erreur de connexion."));
                }
            }).start();
        };

        btn.addActionListener(tryConnect);
        field.addActionListener(tryConnect);

        dialog.setVisible(true);
        return result[0];
    }

    private static void buildChatUI() {
        JFrame frame = new JFrame("Chat — " + myUsername);
        frame.setSize(650, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(4, 4));

        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        JScrollPane userScroll = new JScrollPane(userList);
        userScroll.setBorder(BorderFactory.createTitledBorder("Connectés"));
        userScroll.setPreferredSize(new Dimension(140, 0));

        // Pour selec dans le cas des chat privé
        userList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String selected = userList.getSelectedValue();
                if (selected == null || selected.equals(myUsername)) return;
                currentPrivateTarget = selected;
                chatTitleLabel.setText("Privé : " + selected);
                inputField.requestFocus();
            }
        });

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        chatTitleLabel = new JLabel("Chat général");

        JButton globalBtn = new JButton("Global");
        globalBtn.addActionListener(e -> {
            currentPrivateTarget = null;
            chatTitleLabel.setText("Chat général");
            userList.clearSelection();
            inputField.requestFocus();
        });

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.add(chatTitleLabel, BorderLayout.CENTER);
        topBar.add(globalBtn, BorderLayout.EAST);

        inputField = new JTextField();
        JButton sendBtn = new JButton("Envoyer");

        ActionListener sendAction = e -> {
            String text = inputField.getText().trim();
            if (text.isEmpty()) return;
            if (currentPrivateTarget != null) {
                out.println("PRIVATE|" + currentPrivateTarget + "|" + text);
            } else {
                out.println("MSG|" + text);
            }
            inputField.setText("");
        };

        sendBtn.addActionListener(sendAction);
        inputField.addActionListener(sendAction);

        JPanel inputPanel = new JPanel(new BorderLayout(4, 0));
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendBtn, BorderLayout.EAST);

        JPanel rightPanel = new JPanel(new BorderLayout(4, 4));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        rightPanel.add(topBar, BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        rightPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(userScroll, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void handleMessage(String line) {
        //Message reçu avec la liste des utilisateurs
        if (line.startsWith("USERS|")) {
            String[] users = line.substring(6).split(",");
            SwingUtilities.invokeLater(() -> {
                userListModel.clear();
                for (String u : users) {
                    if (!u.isEmpty()) userListModel.addElement(u);
                }
            });

            //Message broadcast (donc global)
        } else if (line.startsWith("MSG|")) {
            String[] parts = line.split("\\|", 3);
            if (parts.length == 3) appendMessage("[" + parts[1] + "] " + parts[2]);

            //Privé
        } else if (line.startsWith("PRIVATE|")) {
            String[] parts = line.split("\\|", 3);
            if (parts.length == 3) appendMessage("[Privé - " + parts[1] + "] " + parts[2]);

            //Message serveur (déco, ...)
        } else if (line.startsWith("SERVER|")) {
            appendMessage(">> " + line.substring(7));
        }
    }


    //Ajout d'un message dans la zone de tchat
    private static void appendMessage(String msg) {
        SwingUtilities.invokeLater(() -> {
            chatArea.append(msg + "\n");
            chatArea.setCaretPosition(chatArea.getDocument().getLength());
        });
    }
}