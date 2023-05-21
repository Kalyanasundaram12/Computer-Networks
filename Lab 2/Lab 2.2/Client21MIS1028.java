import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client21MIS1028 {

    public static void main(String[] args) {
        String serverAddress = "localhost"; 
        int portNumber = 1234;

        try {
            Socket socket = new Socket(serverAddress, portNumber);
            System.out.println("Connected to the server.");
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.print("Enter card number: ");
            String cardNumber = userInputReader.readLine();
            writer.println(cardNumber);


            String response;
            while ((response = reader.readLine()) != null) {
                System.out.println(response);


                if (response.equals("Enter PIN:")) {
                    System.out.print("Enter PIN: ");
                    String pin = userInputReader.readLine();


                    writer.println(pin);
                } else if (response.equals("Enter withdrawal amount:")) {
                    System.out.print("Enter withdrawal amount: ");
                    String withdrawalAmountStr = userInputReader.readLine();

                    writer.println(withdrawalAmountStr);
                } else {
                    break;
                }
            }


            userInputReader.close();
            writer.close();
            reader.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
