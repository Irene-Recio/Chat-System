public class ChatController {

    privaete List<ChatItem> chatItems;

    public ChatController() {
        this.chatItems = new ArrayList<>();
    }

    public void connect(username) {
        User user = new User(randomId(), username);
        // Notify UserController about the new connection
        UserController.getInstance().notifyUserConnected(user);
    }

    public void disconnect(User user) {
        // Notify UserController about the disconnection
        UserController.getInstance().notifyUserDisconnected(user);
    }

    public void sendMessage(Message message, List<User> recipients) {
    
    }

     public void sendFile(File file, List<User> recipients) {
     }

     public void receiveItem(ChatItem item) {
     }
}