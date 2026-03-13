public class ChatSystem {
    public static void main(String[] args) {
        HostInfo hostInfo = new HostInfo("192.168.254.254", 4422);
        User user = new User("Seris", hostInfo, new ConnectionStatus("CONNECTED"));
        System.out.println("Username: " + user.getUsername());
        System.out.println("IP: " + user.getHostInfo().getIp());
        System.out.println("Port: " + user.getHostInfo().getPort());
        System.out.println("Status: " + user.getStatus().getStatus());
    }
}
