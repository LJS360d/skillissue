import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        boolean running = true;
        while (running) {
            Socket socket = serverSocket.accept();
            new Thread(new ClientHandler(socket)).start();
        }
        serverSocket.close();
    }
}

class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String message = in.readLine();
                System.out.println(message);
                int vowelCount = countVowels(message);
                int consontantsCount = countConsonants(message);
                out.println("V:" + vowelCount + " C:" + consontantsCount);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int countVowels(String message) {
        int count = 0;
        for (int i = 0; i < message.length(); i++) {
            char c = message.toLowerCase().charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    int countConsonants(String message) {
        int count = 0;
        for (int i = 0; i < message.length(); i++) {
            char c = message.toLowerCase().charAt(i);
            if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') && Character.isLetter(c)) {
                count++;
            }
        }
        return count;
    }
}
