import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer21mis1028 {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);

            System.out.println("Server started. Waiting for incoming messages...");
            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received message: " + message);
                receiveData = new byte[1024];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
