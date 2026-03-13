import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer {
    private static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4422);
        System.out.println("Serveur démarré sur le port 4422...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Nouveau client connecté : " + clientSocket.getInetAddress());

            ClientHandler handler = new ClientHandler(clientSocket, clients);
            clients.add(handler);
            new Thread(handler).start();
        }
    }
}