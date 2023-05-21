//Server code
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Serveratm_21MIS1028 {
    private static Map<String, Account> accounts;

    public static void main(String[] args) {
        int port = 1234;
        accounts = initializeAccounts();

        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                Thread thread = new Thread(() -> handleClientRequest(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClientRequest(Socket clientSocket) {
        try {
            // Get the input and output streams of the client socket
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String cardNumber = in.readLine();
            String pin = in.readLine();

            if (validateLogin(cardNumber, pin)) {

                Account account = accounts.get(cardNumber);
                String customerName = account.getCustomerName();
                double balance = account.getBalance();

                out.println("Welcome, " + customerName);
                out.println("Card Number: " + cardNumber);
                out.println("Balance: " + balance);
            } else {

                out.println("Invalid login credentials");
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean validateLogin(String cardNumber, String pin) {
        if (accounts.containsKey(cardNumber)) {
            Account account = accounts.get(cardNumber);
            return account.getPin().equals(pin);
        }
        return false;
    }

    private static Map<String, Account> initializeAccounts() {
        Map<String, Account> accounts = new HashMap<>();
        accounts.put("7904638223", new Account("Kalyan", "1386", 10000.0));
        accounts.put("0987654321", new Account("Krishna", "6381", 800.0));
        return accounts;
    }

    private static class Account {
        private String customerName;
        private String pin;
        private double balance;

        public Account(String customerName, String pin, double balance) {
            this.customerName = customerName;
            this.pin = pin;
            this.balance = balance;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getPin() {
            return pin;
        }

        public double getBalance() {
            return balance;
        }
    }
}
