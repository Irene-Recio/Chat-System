import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainView {
    //Main view class to display the main interface
    ChatView chatView;
    UserListView userListView;

    public mainView() {
        chatView = new ChatView();
        userListView = new UserListView();
        JFrame frame = new JFrame("Chat System - Main View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.add(chatView.frame, BorderLayout.EAST);
        frame.add(userListView.frame, BorderLayout.WEST);
        frame.setVisible(true);

    }


}