import java.net.*;

public class dobClient21mis028 {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            System.out.println("Enter your date of birth (yyyy-mm-dd): ");
            String dob = System.console().readLine();
            byte[] sendData = dob.getBytes();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);
            String responseMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println(responseMessage);
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
