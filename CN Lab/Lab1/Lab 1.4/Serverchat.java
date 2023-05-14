import java.io.*;
import java.util.*;
import java.net.*;

public class Serverchat{

    public static void main(String args[])throws  IOException {

      Socket socket = null;
      InputStreamReader inputStreamReader = null;
      OutputStreamWriter outStreamWriter = null;
      BufferedReader bufferedReader = null;
      BufferedWriter bufferedWriter = null;
      ServerSocket serverSocket = null;

       serverSocket = new ServerSocket(1234);

        while (true) {

          try {
             socket = serverSocket.accept();

             inputStreamReader = new InputStreamReader(socket.getInputStream());
             outStreamWriter = new OutputStreamWriter(socket.getOutputStream());

             bufferedReader = new BufferedReader(inputStreamReader);
             bufferedWriter = new BufferedWriter(outStreamWriter);

             while(true){
                 String clientmsg = bufferedReader.readLine();

                 System.out.println("Client:" + clientmsg);

                 bufferedWriter.write("MESSAGE RECEIVED..");
                 bufferedWriter.newLine();
                 bufferedWriter.flush();

                 if(clientmsg.equalsIgnoreCase("Bye"))
                 break;

             }
               socket.close();
               inputStreamReader.close();
               outStreamWriter.close();
               bufferedReader.close();
               bufferedWriter.close();


          }catch (IOException e){
            e.printStackTrace();
          }
        }
    }
}