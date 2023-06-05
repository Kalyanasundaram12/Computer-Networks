import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class bgpclient21mis1028 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000); 
            OutputStream outputStream = socket.getOutputStream();
            String message = "Hello This is Kalyan";
            outputStream.write(message.getBytes());

            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);

            String responseMessage = new String(buffer, 0, bytesRead);
            System.out.println("Server response: " + responseMessage);

            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


