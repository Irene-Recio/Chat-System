import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {
    static final Map<String, ClientHandler> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4422); //Probleme: le socket reste ouvert
        System.out.println("Serveur démarré sur le port 4422...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }
}