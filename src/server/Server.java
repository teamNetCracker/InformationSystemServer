package server;

import data.GenreDataObject;
import data.TrackDataObject;
import model.GenreModel;
import net.ServerMessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Server {
    private static final int PORT = 1025;

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

    public static void saveTrack(File file, List list) {
        SaveLoadService.getInstance().saveTrack(file, list);
    }

    public static void saveGenres(File file, List list) {
        SaveLoadService.getInstance().saveGenre(file, list);
    }

    public static List<GenreDataObject> loadGenres(File file) {
        return SaveLoadService.getInstance().loadGenres(file);
    }

    public static List<TrackDataObject> loadTracks(File file) {
        return SaveLoadService.getInstance().loadTracks(file);
    }

    public static void onReceive(ServerMessage message, ServerConnect server) {
        switch (message.getCommand()) {
            case ServerCommands.ADD_GENRE:
                List<GenreDataObject> genreList = loadGenres(new File("genre.txt"));
                genreList.add((GenreDataObject) message.getData());
                saveGenres(new File("genre.txt"), genreList);
                for (ServerConnect vr : Server.serverList) {
                    vr.send(new ServerMessage(ServerCommands.ADD_GENRE_SUCCESS, genreList));
                }
                break;
        }

    }


}
