import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.List;

public class ChatSession {
    private User localUser;
    private List<User> connectedUsers;

    public ChatSession(User localUser) {
        this.localUser = localUser;
        this.connectedUsers = new ArrayList<>();
    }

    public void addUser(User user) {
        connectedUsers.add(user);
    }

    public void removeUser(User user) {
        connectedUsers.remove(user);
    }

    public List<User> getConnectedUsers() {
        return connectedUsers;
    }

    public ChatSession getUserSession(User user) {
        if (connectedUsers.contains(user)) {
            return this;
        }
        return null;
    }
    
}