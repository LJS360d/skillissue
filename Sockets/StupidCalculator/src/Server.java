import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            @SuppressWarnings("resource")
            ServerSocket ss = new ServerSocket(5000);
            while (true) {
                Socket s = ss.accept();
                new ServerThread(s).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ServerThread extends Thread {
    Socket s;

    ServerThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            DataInputStream din = new DataInputStream(s.getInputStream());
            double num1 = din.readDouble();
            double num2 = din.readDouble();
            String operation = din.readUTF();

            double result = 0;
            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                default:
                    System.out.println("Invalid operation");
            }

            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeDouble(result);

            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
