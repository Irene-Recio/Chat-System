import java.net.*;
import java.io.*;
import java.util.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private ArrayList<ClientHandler> clients;
    private PrintWriter out;
    private String username;

    public ClientHandler(Socket socket, ArrayList<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            out = new PrintWriter(socket.getOutputStream(), true);
            username = in.readLine();
            System.out.println(username + " a rejoint le chat.");
            broadcast("[Serveur] " + username + " a rejoint le chat !", null);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("[" + username + "] " + message);
                broadcast("[" + username + "] " + message, this);
            }

        } catch (IOException e) {
            System.out.println(username + " s'est déconnecté.");
        } finally {
            clients.remove(this);
            broadcast("[Serveur] " + username + " a quitté le chat.", null);
            try { socket.close(); } catch (IOException e) {}
        }
    }
    private void broadcast(String message, ClientHandler exclude) {
        for (ClientHandler client : clients) {
            if (client != exclude) {
                client.out.println(message);
            }
        }
    }
}