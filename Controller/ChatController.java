import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ChatController {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private ChatView chatView;
    private UserListView userListView;
    private NotificationsView notificationsView;

    public ChatController(ChatView chatView,
                          UserListView userListView,
                          NotificationsView notificationsView) {
        this.chatView = chatView;
        this.userListView = userListView;
        this.notificationsView = notificationsView;
    }

    public void connect(String username) {
        try {
            socket = new Socket("localhost", 12345); // adjust if needed
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println(username);

            notificationsView.setMessage("Connected as " + username);

            // Start listening thread
            new Thread(this::listen).start();

        } catch (IOException e) {
            notificationsView.setMessage("Connection failed");
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) {
        if (out != null) {
            out.println(msg);
        }
    }

    private void listen() {
        try {
            String line;
            while ((line = in.readLine()) != null) {

                // Example protocol handling
                if (line.startsWith("USERS|")) {
                    String[] users = line.substring(6).split(",");
                    userListView.updateUsers(Arrays.asList(users));
                } else {
                    chatView.addMessage(line);
                }
            }
        } catch (IOException e) {
            notificationsView.setMessage("Disconnected");
        }
    }

    public void disconnect() {
        try {
            socket.close();
            notificationsView.setMessage("Disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

