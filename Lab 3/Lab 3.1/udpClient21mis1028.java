import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class udpClient21mis1028 {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;
            
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.print("Enter a message to send (or 'exit' to quit): ");
                String message = scanner.nextLine();
                
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                
                String response = new String(receivePacket.getData()).trim();
                System.out.println("Server response: " + response);
            }
            
            clientSocket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
