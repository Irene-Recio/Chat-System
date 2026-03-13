import java.net.*;
import java.io.*;
import java.util.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // verif username
            while (true) {
                String requestedName = in.readLine();
                if (requestedName == null) return;

                if (ChatServer.clients.containsKey(requestedName)) {
                    out.println("USERNAME_TAKEN");
                } else {
                    username = requestedName;
                    ChatServer.clients.put(username, this);
                    out.println("USERNAME_OK");
                    break;
                }
            }

            // Notifier tout le monde
            broadcast("SERVER|" + username + " a rejoint le chat.");
            sendUserList();

            // Boucle messages
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("MSG|")) {
                    // MSG|message  → broadcast
                    String message = line.substring(4);
                    broadcast("MSG|" + username + "|" + message);

                } else if (line.startsWith("PRIVATE|")) {
                    // PRIVATE|destinataire|message
                    String[] parts = line.split("\\|", 3);
                    if (parts.length == 3) {
                        String target = parts[1];
                        String message = parts[2];
                        ClientHandler targetHandler = ChatServer.clients.get(target);
                        if (targetHandler != null) {
                            targetHandler.send("PRIVATE|" + username + "|" + message);
                            send("PRIVATE|" + username + "|" + message); // echo à soi-même
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Erreur client : " + e.getMessage());
        } finally {
            if (username != null) {
                ChatServer.clients.remove(username);
                broadcast("SERVER|" + username + " a quitté le chat.");
                sendUserList();
            }
            try { socket.close(); } catch (IOException e) {}
        }
    }

    public void send(String message) {
        out.println(message);
    }

    private void broadcast(String message) {
        for (ClientHandler c : ChatServer.clients.values()) {
            c.send(message);
        }
    }

    private void sendUserList() {
        String userList = "USERS|" + String.join(",", ChatServer.clients.keySet());
        for (ClientHandler c : ChatServer.clients.values()) {
            c.send(userList);
        }
    }
}