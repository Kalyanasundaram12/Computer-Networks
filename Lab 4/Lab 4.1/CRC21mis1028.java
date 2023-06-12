import java.util.Random;

public class CRC21mis1028 {
    private static final int POLYNOMIAL = 0b100101;
    private static final int DATA_SIZE = 12;

    public static void main(String[] args) {
        int[] data = generateData();
        int[] encodedData = encodeData(data);

        System.out.println("Data to be transferred: ");
        printData(data);

        System.out.println("Encoded data: ");
        printData(encodedData);

        Random random = new Random();
        int errorIndex = random.nextInt(DATA_SIZE + 5);
        if (errorIndex < DATA_SIZE) {
            encodedData[errorIndex] ^= 1;
        }

        System.out.println("Data received at the server: ");
        printData(encodedData);

        boolean hasError = checkError(encodedData);
        if (hasError) {
            System.out.println("Error detected!");
        } else {
            System.out.println("No error detected.");
            int[] decodedData = decodeData(encodedData);
            System.out.println("Decoded data: ");
            printData(decodedData);
        }
    }

    private static int[] generateData() {
        int[] data = new int[DATA_SIZE];
        String binaryString = "111011010011001";
        for (int i = 0; i < DATA_SIZE; i++) {
            data[i] = Character.getNumericValue(binaryString.charAt(i));
        }
        return data;
    }

    private static int[] encodeData(int[] data) {
        int[] encodedData = new int[data.length + 5];
        System.arraycopy(data, 0, encodedData, 0, data.length);

        for (int i = 0; i < DATA_SIZE; i++) {
            if (encodedData[i] == 1) {
                for (int j = 0; j < 6; j++) {
                    encodedData[i + j] ^= POLYNOMIAL >> j & 1;
                }
            }
        }
        return encodedData;
    }

    private static boolean checkError(int[] receivedData) {
        for (int i = 0; i < DATA_SIZE; i++) {
            if (receivedData[i] == 1) {
                for (int j = 0; j < 6; j++) {
                    receivedData[i + j] ^= POLYNOMIAL >> j & 1;
                }
            }
        }
        for (int i = DATA_SIZE; i < receivedData.length; i++) {
            if (receivedData[i] != 0) {
                return true;
            }
        }
        return false;
    }

    private static int[] decodeData(int[] receivedData) {
        int[] decodedData = new int[DATA_SIZE];
        System.arraycopy(receivedData, 0, decodedData, 0, DATA_SIZE);
        return decodedData;
    }

    private static void printData(int[] data) {
        for (int bit : data) {
            System.out.print(bit);
        }
        System.out.println();
    }
}
