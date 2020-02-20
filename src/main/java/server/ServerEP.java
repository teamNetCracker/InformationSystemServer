package main.java.server;

import main.java.data.TrackDataObject;
import main.java.model.FullModel;
import main.java.model.GenreModel;
import main.java.model.TrackModel;
import main.java.net.ServerMessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ServerEP implements EventListener {
    private static final int PORT = 1025;
    GenreModel genreModel;
    TrackModel trackModel;

    Set<ServerConnection> connectionList = new HashSet<>();

    public ServerEP(GenreModel genreModel, TrackModel trackModel) {
        this.genreModel = genreModel;
        this.trackModel = trackModel;
    }

    public void start() throws IOException {

        ServerSocket server = new ServerSocket(PORT);
        System.out.println("ServerEP started");
        try {
            while (true) {
                Socket socket = server.accept();
                System.out.println(socket.getInetAddress() + " connected");
                try {
                    ServerConnection serverConnection = new ServerConnection(socket);
                    serverConnection.start();
                    connectionList.add(serverConnection);
                    serverConnection.registerCallback(this::onReceive);
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

    private void onReceive(ServerMessage serverMessage, ServerConnection serverConnection) {
        switch (serverMessage.getCommand()){
            case ServerCommands.DELETE_TRACK: {
                String id = (String) serverMessage.getData();
                trackModel.removeTrack(id);
                break;
            }
            case ServerCommands.ADD_TRACK: {
                TrackDataObject trackDataObject = (TrackDataObject) serverMessage.getData();
                trackModel.addTrack(trackDataObject.getId(), trackDataObject.getTitle(), trackDataObject.getPerformer(), trackDataObject.getAlbum(), trackDataObject.getGenre(), trackDataObject.getDuration());
                break;
            }
            case ServerCommands.UPDATE_DATA: {
                FullModel fullModel = new FullModel(trackModel.getAllTracks(), genreModel.getAllGenres());
                serverConnection.send(new ServerMessage(ServerCommands.UPDATE_DATA, fullModel));
            }
        }
    }

    @Override
    public void update() {
        FullModel fullModel = new FullModel(trackModel.getAllTracks(), genreModel.getAllGenres());
        for(ServerConnection serverConnection : connectionList){
            //serverConnection.send(new ServerMessage(ServerCommands.UPDATE_DATA, fullModel));
            serverConnection.send(new ServerMessage(ServerCommands.UPDATE_DATA, fullModel));
            System.out.println("Sending tracks:" + fullModel.getTackListArr().size());
        }
    }
}
