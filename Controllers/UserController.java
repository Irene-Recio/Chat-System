import java.util.*;
public class UserController {
    private Map<String, User> users;

    public UserController() {
        this.users = new HashMap<>();
    }

    public void refreshUserList(List<User> userList) {
        for (User user : userList) {
            users.put(user.getUsername(), user);
        }
    }

    public static void notifyUserConnected(User user) {
        Random random = new Random();
        users.put(user.getUsername(), user);
        Message message = new Message(random.nextInt(1000), new Date(), user, new ArrayList<>(), "User " + user.getUsername() + " has connected.");
        // Notify all users about the new connection
        for (User otherUser : users.values()) {
            if (!otherUser.getUsername().equals(user.getUsername())) {
                // Send message to otherUser
                otherUser.notifyConnection(message);
            }
        }
    }

    public static void notifyUserDisconnected(User user) {
        Random random = new Random();
        users.remove(user.getUsername());
        Message message = new Message(random.nextInt(1000), new Date(), user, new ArrayList<>(), "User " + user.getUsername() + " has disconnected.");
        // Notify all users about the disconnection
        for (User otherUser : users.values()) {
            if (!otherUser.getUsername().equals(user.getUsername())) {
                // Send message to otherUser
                otherUser.notifyDisconnection(message);
            }
        }
    }
    

    
}