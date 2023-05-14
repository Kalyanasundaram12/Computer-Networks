import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Calc_Client
{
    public static void main(String[] args) throws IOException
    {
        InetAddress ip = InetAddress.getLocalHost();
        int port = 4444;
        Scanner sc = new Scanner(System.in);
        Socket s = new Socket(ip, port);

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        while (true)
        {
            System.out.print("enter numbers or say bye: ");
            System.out.println("'enter n1 space n2 '");
            String inp = sc.nextLine();
            if (inp.equals("bye"))
                break;

            dos.writeUTF(inp);

            String ans = dis.readUTF();
            System.out.println("Answer=" + ans);
        }
    }
}
