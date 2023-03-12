import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DatetimeServer {
   public static void main(String args[]) throws Exception {
      try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
         byte[] receiveData = new byte[1024];
         byte[] sendData = new byte[1024];

         int count = 0;
         InetAddress clientIP = null;
         int clientPort = 0;

         while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            sentence = sentence.trim();

            clientIP = receivePacket.getAddress();
            clientPort = receivePacket.getPort();

            String capitalizedSentence;
            if (count < 10) {
               capitalizedSentence = new SimpleDateFormat("HH:mm - dd/MM/yyyy").format(new Date());
            } else {
               capitalizedSentence = "Puppa";
            }
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, clientPort);
            serverSocket.send(sendPacket);


            count++;
            if (count == 11) {
               count = 0;
            }
         }
      }
   }
}
