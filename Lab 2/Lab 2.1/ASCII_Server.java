import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ASCII_Server{

    public static void main(String[] args) {
        int portNumber = 1234; 

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server is running and waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to a client.");

                
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                
                String message = reader.readLine();
                int nValue = Integer.parseInt(reader.readLine());

                
                String encodedMessage = encodeMessage(message, nValue);

                
                writer.println(encodedMessage);

                
                reader.close();
                writer.close();
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String encodeMessage(String message, int nValue) {
            List<Integer> primes = generatePrimes(nValue);

            StringBuilder encodedMessage = new StringBuilder();
            for (char c : message.toCharArray()) {
                int asciiValue = (int) c;
                int nthPrime = primes.get(nValue - 1); 

                
                int encodedValue = asciiValue % nthPrime;
                encodedMessage.append(encodedValue);
            }

            return encodedMessage.toString();
        }

        private List<Integer> generatePrimes(int n) {
            List<Integer> primes = new ArrayList<>();
            int num = 2;

            while (primes.size() < n) {
                if (isPrime(num)) {
                    primes.add(num);
                }
                num++;
            }

            return primes;
        }

        private boolean isPrime(int num) {
            if (num <= 1) {
                return false;
            }

            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    return false;
                }
            }

            return true;
        }
    }
}