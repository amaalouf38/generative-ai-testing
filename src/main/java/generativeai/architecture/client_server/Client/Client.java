package generativeai.architecture.client_server.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import generativeai.architecture.client_server.Request;
import generativeai.architecture.client_server.File;

public class Client {

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    // Get filename from user
    String filename = args[0];

    // Create request message
    Request request = new Request(filename);

    // Send request to server
    Socket socket = new Socket("localhost", 12345);
    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
    oos.writeObject(request);
    oos.flush();

    // Receive response
    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
    File response = (File) ois.readObject();

    // Handle response (e.g., print file content)
    System.out.println("Received file: " + response.getName());
    // ...
  }

}
