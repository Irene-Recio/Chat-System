public class User {
    private String username;
    private hostInfo hostInfo;
    private status status;

    public User(String username, hostInfo hostInfo, status status) {
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