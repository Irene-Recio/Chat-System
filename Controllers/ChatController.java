public class ChatController {

    private List<ChatItem> chatItems;

    public ChatController() {
        this.chatItems = new ArrayList<>();
    }

    public void connect(String username) {
        User user = new User(randomId(), username);
        ChatSession session = new ChatSession(user);
        session.addUser(user);

        // Notify UserController about the new connection
        UserController.getInstance().notifyUserConnected(user);
    }

    public void disconnect(User user) {
        ChatSession session = getUserSession(user);
        if (session != null) {
            session.removeUser(user);
        }

        // Notify UserController about the disconnection
        UserController.getInstance().notifyUserDisconnected(user);
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