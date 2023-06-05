import java.net.*;

public class dobServer21mis1028 {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(12345);
            byte[] receiveBuffer = new byte[1024];
            
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                String[] dateParts = receivedData.split("-");
                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int day = Integer.parseInt(dateParts[2]);
                int currentYear = java.time.LocalDate.now().getYear();
                int age = currentYear - year;
                String responseMessage = "Your age is: " + age;
                byte[] responseBuffer = responseMessage.getBytes();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, clientAddress, clientPort);
                serverSocket.send(responsePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

