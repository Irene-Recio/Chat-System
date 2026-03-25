public class User {
    private String username;
    private HostInfo hostInfo;
    private ConnectionStatus status;

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

    public hostInfo getHostInfo() {
        return hostInfo;
    }

    public void setHostInfo(hostInfo hostInfo) {
        this.hostInfo = hostInfo;
    }

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }

}