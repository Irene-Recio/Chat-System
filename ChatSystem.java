import java.net.*;
import java.io.*;

public class ChatSystem {
    public static void main(String[] args) {
        HostInfo hostInfo = new HostInfo("10.8.22.214", 4422);
        User user = new User("A", hostInfo, new ConnectionStatus("CONNECTED"));

        try {
            Socket socket = new Socket(hostInfo.getIp(), hostInfo.getPort());
            System.out.println("Connecté au serveur !");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in)
            );

            out.println(user.getUsername());
            Thread reception = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Connexion perdue.");
                }
            });
            reception.setDaemon(true);
            reception.start();
            System.out.println("Tapez vos messages (ou 'quit' pour quitter) :");
            String input;
            while ((input = console.readLine()) != null) {
                if (input.equalsIgnoreCase("quit")) break;
                out.println(input);
            }

            socket.close();
            System.out.println("Déconnecté.");

        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}