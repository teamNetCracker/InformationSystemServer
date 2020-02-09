package server;

import data.TrackDataObject;
import model.FullModel;
import model.GenreModel;
import model.TrackModel;
import net.ServerMessage;

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
        genreModel.subscribe(this);
        trackModel.subscribe(this);
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
                TrackDataObject trackDataObject = (TrackDataObject) serverMessage.getData();
                trackModel.removeTrack(trackDataObject.getId());
                break;
            }
            case ServerCommands.ADD_TRACK: {
                TrackDataObject trackDataObject = (TrackDataObject) serverMessage.getData();
                trackModel.addTrack(trackDataObject.getId(), trackDataObject.getTitle(), trackDataObject.getPerformer(), trackDataObject.getAlbum(), trackDataObject.getGenre(), trackDataObject.getDuration());
                break;
            }
            case ServerCommands.CHANGE_GENRE: {

            }
        }
    }

    @Override
    public void update() {
        FullModel fullModel = new FullModel(trackModel.getAllTracks(), genreModel.getAllGenres());
        for(ServerConnection serverConnection : connectionList){
            //serverConnection.send(new ServerMessage(ServerCommands.CONNECT, fullModel));
            serverConnection.send(trackModel.getAllTracks());
            System.out.println("Sending tracks:" + trackModel.getAllTracks().size());
        }
    }
}
