
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientATM_21MIS1028 {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 1234;

        try {

            Socket socket = new Socket(serverAddress, serverPort);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Card Number: ");
            String cardNumber = userInput.readLine();
            System.out.print("Enter PIN: ");
            String pin = userInput.readLine();

            out.println(cardNumber);
            out.println(pin);

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
