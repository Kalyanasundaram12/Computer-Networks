import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HammingClient21mis1028 {
    public static void main(String[] args) {
        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to the server.");

            // Create input and output streams for communication
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Send the data to the server
            String data = "Hello,This is Kalyan from VIT Chennai !";
            System.out.println("Sending data: " + data);
            out.writeUTF(data);

            // Receive the corrected data from the server
            String correctedData = in.readUTF();
            System.out.println("Received corrected data: " + correctedData);

            // Close the streams and socket
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
