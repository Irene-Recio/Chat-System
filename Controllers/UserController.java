public class UserController {
    private Map<String, User> users;

    public UserController() {
        this.users = new HashMap<>();
    }

    public void refreshUserList(List<User> userList) {
        for (User user : userList) {
            users.put(user.getId(), user);
        }
    }

    public void notifyUserConnected(User user) {
        users.put(user.getId(), user);
        Message message = new Message(randomId(), new Date(), user, new ArrayList<>(), "User " + user.getUsername() + " has connected.");
        // Notify all users about the new connection
        for (User otherUser : users.values()) {
            if (!otherUser.getId().equals(user.getId())) {
                // Send message to otherUser
                otherUser.notifyConnection(message);
            }
        }
    }

    public void notifyUserDisconnected(User user) {
        users.remove(user.getId());
        Message message = new Message(randomId(), new Date(), user, new ArrayList<>(), "User " + user.getUsername() + " has disconnected.");
        // Notify all users about the disconnection
        for (User otherUser : users.values()) {
            if (!otherUser.getId().equals(user.getId())) {
                // Send message to otherUser
                otherUser.notifyDisconnection(message);
            }
        }
    }
    

    
}