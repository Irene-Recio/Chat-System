import java.util.Date;
import java.util.List;
public class ChatItem {
    private Integer id;

    private Date timestamp;

    private User sender;

    private List<User> recipients;

        public ChatItem(Integer id, Date timestamp, User sender, List<User> recipients) {
            this.id = id;
            this.timestamp = timestamp;
            this.sender = sender;
            this.recipients = recipients;
        }

    public Integer getId() {
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

    


}