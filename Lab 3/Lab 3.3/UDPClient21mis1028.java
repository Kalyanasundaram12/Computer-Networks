import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient21mis1028 {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            String message = "Hello this is Kalyan from VIT Chennai";
            byte[] sendData = message.getBytes();

            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);

            socket.send(sendPacket);
            System.out.println("Sent message: " + message);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
