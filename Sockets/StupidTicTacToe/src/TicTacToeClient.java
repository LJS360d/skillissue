import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TicTacToeClient {

    private static final int PORT = 6868;
    private static final String ADDRESS = "127.0.0.1";
    private static final String CROSS = "X";
    private static final String CIRCLE = "O";
    private static String[] board = { " ", " ", " ", " ", " ", " ", " ", " ", " " };

    public static void main(String[] args) {
        try (final Socket socket = new Socket(ADDRESS, PORT); final Scanner scanner = new Scanner(System.in)) {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while (true) {
                System.out.println("Enter the cell where you want to place your cross (1-9): ");
                int cell = scanner.nextInt() - 1;
                while (board[cell] != " ") {
                    System.out.println("Position invalid, try again (1-9): ");
                    cell = scanner.nextInt() - 1;
                }
                board[cell] = CROSS;
                dos.writeInt(cell);
                dos.flush();
                System.out.println("Your move: ");
                printBoard();

                int serverCell = dis.readInt();
                board[serverCell] = CIRCLE;

                String result = br.readLine();
                if (result.equals(" ")) {
                    System.out.println("Server's move: ");
                    printBoard();
                } else {
                    System.out.println(result);
                    Thread.sleep(1500);
                    resetBoard();
                    Runtime.getRuntime().exec("cls");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printBoard() {
        for (int i = 0; i < 9; i += 3) {
            System.out.println(board[i] + " | " + board[i + 1] + " | " + board[i + 2]);
            if (i < 6) {
                System.out.println("---------");
            }
        }
        System.out.println();
    }

    private static void resetBoard() {
        String[] temp = { " ", " ", " ", " ", " ", " ", " ", " ", " " };
        board = temp;

    }

}
