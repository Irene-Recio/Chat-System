// Main class is the main code that creates and runs the chat system

public class Main {
    public static void main(String[] args) {
        ChatController chatController = new ChatController();
        chatController.start();

            UserListView userListView = new UserListView();
            NotificationView notificationView = new NotificationView();
            
            //1- User login
                var username = LoginView.getUsername();
                chatController.connect(username);

            //2- Update user list
                userListView.updateUserList(chatController.getConnectedUsers());

            //3- Inform other users
                ChatItem chatItem = new ChatItem(username, " is connected.");
                chatController.sendMessage(chatItem, chatController.getConnectedUsers());

            //4- Send message
                message = ChatView.getMessage();
                chatItem = new ChatItem(username, message);
                chatController.sendMessage(chatItem, chatController.getConnectedUsers());
                

    }


}