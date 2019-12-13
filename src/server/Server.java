package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {
    private static final int PORT = 1024;

    static Set<ServerConnect> serverList = new HashSet<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server started");
        try {
            while (true) {
                Socket socket = server.accept();
                System.out.println(socket.getInetAddress() + " connected");
                try {
                    ServerConnect serverConnect = new ServerConnect(socket);
                    serverConnect.start();
                    serverList.add(serverConnect);
                    serverConnect.registerCallback(Server::onReceive);
                } catch (IOException e) {
                    socket.close();
                }
                if (!socket.isConnected()) {
                    break;
                }
            }
        } finally {
            server.close();
        }
    }

    private static void onReceive(ServerMessage message, ServerConnect server){
        switch (message.getCommand()) {


        }

    }
}
