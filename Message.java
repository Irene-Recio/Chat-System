import java.util.Date;
import java.util.List;

public class Message extends ChatItem {
        private String text;

        public Message(Integer id, Date timestamp, User sender, List<User> recipients, String text) {
            super(id, timestamp, sender, recipients);
            this.text = text;
        }

        public String getText() {
            return text;
        }
        
}
