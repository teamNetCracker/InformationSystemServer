package server;

import net.ServerMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ServerConnection extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Set<Callback> callbacks = new HashSet<>();

    public ServerConnection(Socket socket) throws IOException {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ServerMessage serverMessage = (ServerMessage) in.readObject();
                for (Callback callback : callbacks) {
                    callback.onReceive(serverMessage, this);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Loss of connection to one of the clients");
                e.printStackTrace();
                //downServer();
                break;
            }
        }
    }


    public void registerCallback(Callback callback) {
        callbacks.add(callback);
    }

    public void unregisterCallback(Callback callback) {
        callbacks.remove(callback);
    }

    public void send(ServerMessage word) {
        try {
            out.writeObject(word);
            out.flush();
            System.out.println("Send message");
        } catch (IOException ignored) {
        }
    }

    /*
    public void downServer() {
        try {
            callbacks.clear();
            if (!socket.isClosed()) {
                in.close();
                out.close();
                socket.close();
                for (ServerConnection vr : ServerEP.connectionList) {
                    if (equals(vr)) vr.interrupt();
                    ServerEP.connectionList.remove(this);
                }
            }
        } catch (IOException ignored) {
        }
    }

     */


    public interface Callback {
        void onReceive(ServerMessage message, ServerConnection server);
    }
}
