import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = null;
        while (true) {
            try {
                scanner = new Scanner(System.in);
                System.out.print("Enter first number: ");
                double num1 = scanner.nextDouble();
                System.out.print("Enter second number: ");
                double num2 = scanner.nextDouble();
                System.out.print("Enter operation (+, -, *, /): ");
                String operation = scanner.next();
                @SuppressWarnings("resource")
                Socket socket = new Socket("localhost", 5000);
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                dout.writeDouble(num1);
                dout.writeDouble(num2);
                dout.writeUTF(operation);

                DataInputStream din = new DataInputStream(socket.getInputStream());
                double result = din.readDouble();
                System.out.println("Result: " + result);

                System.out.print("Do you want to continue? (y/n): ");
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("n")) {
                    scanner.close();
                    socket.close();
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
