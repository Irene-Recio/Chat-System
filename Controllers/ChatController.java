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
        chatItems.add(message);
        // Notify recipients about the new message
        for (User recipient : recipients) {
            recipient.notifyMessage(message);
        }
    }

     public void sendFile(File file, List<User> recipients) {
        chatItems.add(file);
        // Notify recipients about the new file
        for (User recipient : recipients) {
            recipient.notifyFile(file);
        }
     }
}