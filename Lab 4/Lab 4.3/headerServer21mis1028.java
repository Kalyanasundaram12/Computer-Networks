import java.io.*;
import java.net.*;

public class headerServer21mis1028 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234); // Server socket running on port 1234
        System.out.println("Server is running and waiting for a connection...");

        Socket clientSocket = serverSocket.accept(); // Accept incoming connection from client
        System.out.println("Client connected!");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

        String header = in.readLine(); // Read the header from the client
        String message = in.readLine(); // Read the message from the client

        // Extract checksum from the header
        int checksum = Integer.parseInt(header.split(":")[1].trim());

        // Calculate the checksum of the received message
        int calculatedChecksum = calculateChecksum(message);

        // Compare the received checksum with the calculated checksum
        if (checksum == calculatedChecksum) {
            System.out.println("Message received successfully without any data loss.");
            out.writeBytes("ACK\n"); // Send acknowledgement to the client
        } else {
            System.out.println("Data loss detected during transmission. Message is corrupted.");
            out.writeBytes("NACK\n"); // Send negative acknowledgement to the client
        }

        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
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
