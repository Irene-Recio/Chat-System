import java.util.Date;
import java.util.List;

public abstract class ChatItem{
    private String id;
    private Date timestamp;
    private User sender;
    private List<User> recipients;

    public ChatItem(String id, Date timestamp, User sender, List<User> recipients) {
        this.id = id;
        this.timestamp = timestamp;
        this.sender = sender;
        this.recipients = recipients;
    }
    
    public String getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public User getSender() {
        return sender;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setRecipients(List<User> recipients) {
        this.recipients = recipients;
    }

}