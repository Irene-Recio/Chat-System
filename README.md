# Chat-System
Chat-System project

Requirements:

This application allow users to communicate by sending and receiving text messages using interconnected devices.
The user uses a username to connect to the chat system.
When a user connects to the system, the list of the other connected users is presented. This list includes connected user names and information about their remote system (i.e. remote host information).
Only connected users are able to communicate using the chat system functions.
When a user connects or disconnects, the other users are informed about it.	
When a user wants to communicate with another user, he has to select the remote user from the connected users' list. The message/file to be sent needs to be indicated. Optionally, all the connected users will be selected as the destination.
When the system receives a message or file targeted to the connected local user, the user is informed about it.

Make the ChatSystem work:
First, compile:
    Write on the terminal:
    
        javac User.java ChatServer.java ChatSystem.java ChatItem.java File,java Message.java