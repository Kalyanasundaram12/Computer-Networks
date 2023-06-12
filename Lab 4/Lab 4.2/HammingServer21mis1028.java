import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HammingServer21mis1028 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for a client...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String receivedData = in.readUTF();
            System.out.println("Received data: " + receivedData);

            String correctedData = performHammingDistanceErrorCorrection(receivedData);
            System.out.println("Corrected data: " + correctedData);

            out.writeUTF(correctedData);

            in.close();
            out.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String performHammingDistanceErrorCorrection(String receivedData) {
    StringBuilder correctedData = new StringBuilder(receivedData);
    int parityBitCount = 0; // Number of parity bits

    // Find the number of parity bits required
    while (Math.pow(2, parityBitCount) < correctedData.length() + parityBitCount + 1) {
        parityBitCount++;
    }

    // Insert placeholder values for parity bits
    for (int i = 0; i < parityBitCount; i++) {
        correctedData.insert((int) Math.pow(2, i) - 1, '_');
    }

    // Calculate parity bits
    for (int i = 0; i < parityBitCount; i++) {
        int position = (int) Math.pow(2, i) - 1; // Position of the parity bit
        int count = 0; // Number of ones in the block
        int index = position; // Starting index for calculating parity bit

        while (index < correctedData.length()) {
            for (int j = 0; j < position + 1 && index < correctedData.length(); j++) {
                if (correctedData.charAt(index) == '1') {
                    count++;
                }
                index++;
            }

            index += position + 1;
        }
        if (count % 2 == 0) {
            correctedData.setCharAt(position, '0');
        } else {
            correctedData.setCharAt(position, '1');
        }
    }
    for (int i = 0; i < parityBitCount; i++) {
        int position = (int) Math.pow(2, i) - 1; // Position of the parity bit
        int count = 0; // Number of ones in the block
        int index = position; // Starting index for calculating parity bit

        while (index < correctedData.length()) {
            for (int j = 0; j < position + 1 && index < correctedData.length(); j++) {
                if (correctedData.charAt(index) == '1') {
                    count++;
                }
                index++;
            }

            index += position + 1;
        }
        if (count % 2 != 0 && correctedData.charAt(position) == '1') {
            correctedData.setCharAt(position, '0');
        } else if (count % 2 != 0 && correctedData.charAt(position) == '0') {
            correctedData.setCharAt(position, '1');
        }
    }
    for (int i = parityBitCount - 1; i >= 0; i--) {
        correctedData.deleteCharAt((int) Math.pow(2, i) - 1);
    }
    return correctedData.toString();
}

}
