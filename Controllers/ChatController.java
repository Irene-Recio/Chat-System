import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class ChatController {

    private List<ChatItem> chatItems;

    public ChatController() {
        this.chatItems = new ArrayList<>();
    }

    public void connect(String username) {
        User user = new User(username, new HostInfo("127.0.0.1", 8080), new ConnectionStatus(true));
        ChatSession session = new ChatSession(user);
        session.addUser(user);

        // Notify UserController about the new connection
        UserController.notifyUserConnected(user);
    }

    public void disconnect(User user) {
        ChatSession session = getUserSession(user);
        if (session != null) {
            session.removeUser(user);
        }

        // Notify UserController about the disconnection
        UserController.notifyUserDisconnected(user);
    }

    public void sendMessage(Message message, List<User> receivers) {
        chatItems.add(message);
        // Notify receiver about the new message
        for (User receiver : receivers) {
            receiver.notifyMessage(message);
        }
    }

     public void sendFile(File file, List<User> receivers) {
        chatItems.add(file);
        // Notify receiver about the new file
        for (User receiver : receivers) {
            receiver.notifyFile(file);
        }
     }
}