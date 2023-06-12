public class CRCclient21mis1028 {
    private static final int POLYNOMIAL = 0b100101;
    private static final int DATA_SIZE = 12;

    public static void main(String[] args) {
        int[] data = {1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1};

        System.out.println("Data received at the client: ");
        printData(data);

        boolean hasError = checkError(data);
        if (hasError) {
            System.out.println("Error detected!");
        } else {
            System.out.println("No error detected.");
            int[] decodedData = decodeData(data);
            System.out.println("Decoded data: ");
            printData(decodedData);
        }
    }

private static boolean checkError(int[] receivedData) {
    int dataSize = receivedData.length - 5;
    for (int i = 0; i < dataSize; i++) {
        if (receivedData[i] == 1) {
            for (int j = 0; j < 6; j++) {
                receivedData[i + j] ^= POLYNOMIAL >> j & 1;
            }
        }
    }
    for (int i = dataSize; i < receivedData.length; i++) {
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
