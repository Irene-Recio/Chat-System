public class User {
    private String username;
    private HostInfo hostInfo;
    private ConnectionStatus status;
    private String notification;

    public User(String username, HostInfo hostInfo, ConnectionStatus status) {
        this.username = username;
        this.hostInfo = hostInfo;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HostInfo getHostInfo() {
        return hostInfo;
    }

    public void setHostInfo(HostInfo hostInfo) {
        this.hostInfo = hostInfo;
    }

    public ConnectionStatus getStatus() {
        return status;
    }

    public void setStatus(ConnectionStatus status) {
        this.status = status;
    }

    public void notifyMessage(Message message) {
        this.notification = "New message from " + message.getSender().getUsername() + ": " + message.getText();
    }

    public void notifyFile(File file) {
        this.notification = "New file from " + file.getSender().getUsername() + ": " + file.getFileName();
    }
}