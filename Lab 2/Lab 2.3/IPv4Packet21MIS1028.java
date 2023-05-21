public class IPv4Packet21MIS1028 {
    public static void main(String[] args) {
        String headerLengthBinary = "1000"; // Binary header length value
        
        // Convert binary to decimal
        int headerLengthDecimal = Integer.parseInt(headerLengthBinary, 2);
        
        // Calculate the length of the IPv4 header in bytes
        int headerLengthBytes = headerLengthDecimal * 4;
        
        // Subtract minimum header length to get options length in bytes
        int optionsLengthBytes = headerLengthBytes - 20;
        
        System.out.println("Options Length: " + optionsLengthBytes + " bytes");
    }
}
