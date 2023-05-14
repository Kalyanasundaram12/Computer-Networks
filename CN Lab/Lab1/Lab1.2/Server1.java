import java.net.*;
import java.io.*;
import java.util.*;
class Server1{
public static void main(String args[]) {
try
{
ServerSocket ss=new ServerSocket(6666);
Socket s=ss.accept();
DataInputStream dis=new DataInputStream(s.getInputStream());
String str=(String)dis.readUTF();
System.out.println("message= "+str);
ss.close();
}catch(Exception e){System.out.println(e);}
}
}
