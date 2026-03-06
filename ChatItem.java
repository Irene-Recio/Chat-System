public class ChatItem {
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

    


}