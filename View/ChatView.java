import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatView {
    //View class to display the chat interface
    
    JFrame frame;
        JTextArea chatArea;
        JTextField messageField;
        JButton sendButton;
        JButton addFileButton;
        JButton disconnectButton;

    public ChatView() {
        frame = new JFrame("Chat System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setVisible(true);
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        messageField = new JTextField();
        sendButton = new JButton("Send");
        addFileButton = new JButton("Add File");
        disconnectButton = new JButton("Disconnect");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(messageField, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(sendButton);
        buttonPanel.add(addFileButton);
        inputPanel.add(buttonPanel, BorderLayout.EAST);
        Jpanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        Jpanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(disconnectButton);
        mainPanel.add(topPanel, BorderLayout.SOUTH);
        
    }
}