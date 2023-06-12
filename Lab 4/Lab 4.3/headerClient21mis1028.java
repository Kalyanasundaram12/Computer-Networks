import java.io.*;
import java.net.*;

public class headerClient21mis1028 {
    public static void main(String[] args) throws IOException {
        String serverHostname = "localhost";
        int serverPort = 1234;

        System.out.println("Connecting to server " + serverHostname + " on port " + serverPort + "...");

        Socket socket = new Socket(serverHostname, serverPort);
        System.out.println("Connected to server!");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        String message = "Hello, This is Kalyan!"; // Message to be sent
        int checksum = calculateChecksum(message); // Calculate the checksum of the message

        // Prepare and send the header and message to the server
        String header = "Checksum: " + checksum;
        out.writeBytes(header + "\n");
        out.writeBytes(message + "\n");

        // Receive acknowledgement from the server
        String response = in.readLine();
        if (response.equals("ACK")) {
            System.out.println("Message sent successfully without any data loss.");
        } else {
            System.out.println("Data loss detected during transmission. Message is corrupted.");
        }

        in.close();
        out.close();
        socket.close();
    }

    // Function to calculate the checksum of a given message
    private static int calculateChecksum(String message) {
        int checksum = 0;
        for (int i = 0; i < message.length(); i++) {
            checksum += message.charAt(i);
        }
        return checksum;
    }
}
