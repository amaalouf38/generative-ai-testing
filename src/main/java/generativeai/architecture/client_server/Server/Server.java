package generativeai.architecture.client_server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import generativeai.architecture.client_server.File;
import generativeai.architecture.client_server.Request;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(12345);

        while (true) {
            Socket socket = serverSocket.accept();

            // Receive request
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Request request = (Request) ois.readObject();

            // Locate file based on request.getFilename()
            File file = locateFile(request.getFilename());

            // Send file back to client
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(file);
            oos.flush();

            socket.close();
        }
    }

    // Simulate locating the file based on filename
    private static File locateFile(String filename) {
        // ... locate file based on filename
        return new File(filename, 1024, new byte[1024]);
    }
}