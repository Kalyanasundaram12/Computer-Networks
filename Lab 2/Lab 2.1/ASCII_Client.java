import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ASCII_Client{

    public static void main(String[] args) {
        String serverAddress = "localhost"; 
        int portNumber = 1234; 

        try {
            Socket socket = new Socket(serverAddress, portNumber);
            System.out.println("Connected to the server.");

            
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.print("Enter a message: ");
            String message = userInputReader.readLine();
            System.out.print("Enter the n-value: ");
            int nValue = Integer.parseInt(userInputReader.readLine());

            
            writer.println(message);
            writer.println(nValue);

            
            String encodedMessage = reader.readLine();
            System.out.println("Encoded message: " + encodedMessage);

            
            userInputReader.close();
            writer.close();
            reader.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
