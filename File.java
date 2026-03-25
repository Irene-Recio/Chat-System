import java.util.Date;
import java.util.List;
public class File extends ChatItem {
    private String fileName;

    private long fileSize;

    private byte[] fileContent;

    public File(String id, Date timestamp, User sender, List<User> recipients, String fileName, long fileSize, byte[] fileContent) {
        super(id, timestamp, sender, recipients);
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileContent = fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    
}
