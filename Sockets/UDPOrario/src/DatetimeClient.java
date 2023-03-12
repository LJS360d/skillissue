import java.net.*;
import java.util.Arrays;

public class DatetimeClient {
   public static void main(String args[]) throws Exception {
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName("localhost");
      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];

      for (int i = 0; i < 11; i++) {
         String sentence = "request";
         sendData = sentence.getBytes();
         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
         clientSocket.send(sendPacket);

         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
         clientSocket.receive(receivePacket);
         String modifiedSentence = new String(receivePacket.getData());
         System.out.println("FROM SERVER:" + modifiedSentence);
         Arrays.fill(receiveData,(byte)0);
      }

      clientSocket.close();
   }
}
