import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost", 9999);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        int consonants = 10, vowels = 0;
        while (consonants != (vowels / 2)) {
            System.out.print("Enter a message to send to the server: ");
            String message = scanner.nextLine();
            out.println(message);

            String serverResponse = in.readLine();
            System.out.println(serverResponse);
            String[] parts = serverResponse.split(" ");
            String[] V = parts[0].split(":");
            String[] C = parts[1].split(":");
            vowels = Integer.parseInt(V[1]);
            consonants = Integer.parseInt(C[1]);

        }
        System.out.println("Hai sucato");
        scanner.close();
        socket.close();
    }
}
