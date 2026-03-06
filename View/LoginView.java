import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView {
    JButton loginButton;
    JTextField usernameField;
    JLabel usernameLabel;
    public LoginView() {
        JFrame frame = new JFrame("Chat System - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 1));

        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        loginButton = new JButton("Login");

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if(username.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a username.");
                    return;
                }
                System.out.println("Attempting to login with username: " + username);
            }
        });

        frame.setVisible(true);
    }

}