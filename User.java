public class User {
    private enum Status {
        CONNECTED,
        DISCONNECTED
    }
    private String username;
    private HostInfo hostInfo;
    private Status status;

    public User(String username, HostInfo hostInfo, Status status) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}