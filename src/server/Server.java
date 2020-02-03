package server;

import data.GenreDataObject;
import data.TrackDataObject;
import model.FullModel;
import model.GenreModel;
import net.ServerMessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.LinkedList;
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
                    FullModel fullModel = new FullModel(loadTracks(new File("tracks.txt")),loadGenres(new File("genres.txt")));
                    serverConnect.send(new ServerMessage(ServerCommands.CONNECT,fullModel));
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

    private static void saveTrack(File file, List list) {
        new SaveLoadService().saveTrack(file, list);
    }

    private static void saveGenres(File file, List list) {
        new SaveLoadService().saveGenre(file, list);
    }

    private static List<GenreDataObject> loadGenres(File file) {
        return new SaveLoadService().loadGenres(file);
    }

    private static List<TrackDataObject> loadTracks(File file) {
        return new SaveLoadService().loadTracks(file);
    }

    private static void onReceive(ServerMessage message, ServerConnect server) {
        List<GenreDataObject> genreList = loadGenres(new File("genre.txt"));
        List<TrackDataObject> trackList = loadTracks(new File("tracks.txt"));
        switch (message.getCommand()) {
            case ServerCommands.ADD_GENRE:
                genreList.add((GenreDataObject) message.getData());
                saveGenres(new File("genre.txt"), genreList);
                sendToAllClients(ServerCommands.ADD_GENRE);
                break;
            case ServerCommands.DELETE_GENRE:
                genreList = loadGenres(new File("genre.txt"));
                genreList.remove(message.getData());
                saveGenres(new File("genre.txt"), genreList);
                sendToAllClients(ServerCommands.DELETE_GENRE);
                break;
            case ServerCommands.DELETE_TRACK:
                trackList.remove(message.getData());
                saveTrack(new File("tracks.txt"),trackList);
                sendToAllClients(ServerCommands.DELETE_TRACK);
                break;
            case ServerCommands.ADD_TRACK:
                trackList.add((TrackDataObject) message.getData());
                saveTrack(new File("tracks.txt"),trackList);
                sendToAllClients(ServerCommands.ADD_TRACK);
                break;
        }

    }

    private static void sendToAllClients(int command) {
        FullModel fullModel = new FullModel(loadTracks(new File("tracks.txt")), loadGenres(new File("genres.txt")));
        for (ServerConnect vr : Server.serverList) {
            vr.send(new ServerMessage(command, fullModel));
        }
    }


}
