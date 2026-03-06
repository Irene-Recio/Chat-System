import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class UserListView {
    //View class to dsplay the user list
    
    JFrame frame;
    JList<String> userList;
    DefaultListModel<String> listModel;

    public UserListView() {
        frame = new JFrame("User List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 400);
        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(userList);
        frame.add(scrollPane);
        frame.setVisible(true);
    }
    public void updateUserList(List<User> users) {
        listModel.clear();
        for (User user : users) {
            listModel.addElement(user.getUsername());
        }
    }
    
}