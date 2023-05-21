import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server21MIS1028 {
    private static final Map<String, Customer> customers = new HashMap<>();

    public static void main(String[] args) {
        int portNumber = 1234; 
        Customer customer1 = new Customer("Kalyan", "123456789", "1234", 1000);
        Customer customer2 = new Customer("Krishna", "987654321", "4321", 5000);
        customers.put(customer1.getCardNumber(), customer1);
        customers.put(customer2.getCardNumber(), customer2);

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

                
                String cardNumber = reader.readLine();
                if (customers.containsKey(cardNumber)) {
                    Customer customer = customers.get(cardNumber);
                    boolean authenticated = false;

                    for (int i = 0; i < 3; i++) {
                        writer.println("Enter PIN:");
                        String pin = reader.readLine();

                        if (customer.validatePIN(pin)) {
                            authenticated = true;
                            break;
                        } else {
                            writer.println("Invalid PIN. Please try again.");
                        }
                    }


                    if (authenticated) {
                        writer.println("Login successful. Available balance: $" + customer.getBalance());

                        writer.println("Enter withdrawal amount:");
                        String withdrawalAmountStr = reader.readLine();
                        double withdrawalAmount = Double.parseDouble(withdrawalAmountStr);

                        if (customer.hasSufficientBalance(withdrawalAmount)) {
                            customer.withdraw(withdrawalAmount);
                            writer.println("Withdrawal successful. New balance: $" + customer.getBalance());
                        } else {
                            writer.println("Insufficient balance.");
                        }
                    } else {
                        writer.println("Authentication failed. Maximum attempts exceeded.");
                    }
                } else {
                    writer.println("Invalid card number.");
                }
                reader.close();
                writer.close();
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class Customer {
        private final String name;
        private final String cardNumber;
        private final String pin;
        private double balance;

        public Customer(String name, String cardNumber, String pin, double balance) {
            this.name = name;
            this.cardNumber = cardNumber;
            this.pin = pin;
            this.balance = balance;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public boolean validatePIN(String enteredPIN) {
            return enteredPIN.equals(pin);
        }

        public double getBalance() {
            return balance;
        }

        public boolean hasSufficientBalance(double amount) {
            return balance >= amount;
        }

        public void withdraw(double amount) {
            balance -= amount;
        }
    }
}
