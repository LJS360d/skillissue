import java.io.*;
import java.net.*;

public class TicTacToeServer {

    private static final int PORT = 6868;
    private static final String CROSS = "X";
    private static final String CIRCLE = "O";
    private static String[] board = { " ", " ", " ", " ", " ", " ", " ", " ", " " };

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started at port " + PORT);
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);
            PrintWriter pw = new PrintWriter(os, true);

            while (true) {
                int clientCell = dis.readInt();
                board[clientCell] = CROSS;
                System.out.println("Move Recieved");
                if (checkWin(board, CROSS)) {
                    pw.write("You won");
                    resetBoard();
                }
                int serverCell = (int) (Math.random() * 8 + 1);
                while (!board[serverCell].equals(" ")) {
                    serverCell = (int) (Math.random() * 8 + 1);
                }
                board[serverCell] = CIRCLE;
                dos.writeInt(serverCell);
                dos.flush();
                if (checkWin(board, CIRCLE)) {
                    pw.write("You lost");
                    resetBoard();
                }
                System.out.println("Move Sent");
                pw.println(" ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean checkWin(String[] board, String value) {
        int[][] combinations = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 },
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };
        for (int i = 0; i < 8; i++) {

            if (board[combinations[i][0]] == board[combinations[i][1]]
                    && board[combinations[i][0]] == board[combinations[i][2]]
                    && board[combinations[i][0]] == value) {
                return true;
            }
        }
        return false;
    }

    private static void resetBoard() {
        String[] temp = { " ", " ", " ", " ", " ", " ", " ", " ", " " };
        board = temp;

    }
}
