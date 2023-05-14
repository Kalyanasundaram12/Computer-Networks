import java.util.*;
import java.io.*;
import java.net.*;

class Clientchat {
   
  public static void main(String args[]){
    
      Socket socket = null;
      InputStreamReader inputStreamReader = null;
      OutputStreamWriter outputStreamWriter = null;
      BufferedReader bufferedreader = null;
      BufferedWriter bufferedwriter = null;

      try{
             socket = new Socket("localhost",1234);

          inputStreamReader = new InputStreamReader(socket.getInputStream());
          outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

          bufferedreader = new BufferedReader(inputStreamReader);
          bufferedwriter = new BufferedWriter(outputStreamWriter);

          Scanner scanner = new Scanner(System.in);

          while (true) {

            String msg = scanner.nextLine();
            bufferedwriter.write(msg);
            bufferedwriter.newLine();
            bufferedwriter.flush();

            System.out.println("Server:" + bufferedreader.readLine());

            if(msg.equalsIgnoreCase("BYE"))
            break;
          } 
        }catch (Exception e){
            e.printStackTrace();
          } finally {
             try{
              if (socket != null)
                  socket.close();
              if(inputStreamReader != null)
                  inputStreamReader.close();
              if(outputStreamWriter != null)
                  outputStreamWriter.close();
              if(bufferedreader != null)
                   bufferedreader.close();
             if(bufferedwriter != null)
                   bufferedwriter.close();
                } catch (IOException e){
                e.printStackTrace();
              }
          }
        

  }
}