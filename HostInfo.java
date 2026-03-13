class HostInfo {
    private String ip;
    private int port;


    public HostInfo(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String toString() {
        return "HostInfo{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}