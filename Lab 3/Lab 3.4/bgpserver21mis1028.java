import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class bgpserver21mis1028 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000); 

            System.out.println("Waiting for a client connection...");

            Socket clientSocket = serverSocket.accept(); 

            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);

            String receivedMessage = new String(buffer, 0, bytesRead);
            System.out.println("Received message: " + receivedMessage);

            OutputStream outputStream = clientSocket.getOutputStream();
            String responseMessage = "Message received!";
            outputStream.write(responseMessage.getBytes());

            inputStream.close();
            outputStream.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
