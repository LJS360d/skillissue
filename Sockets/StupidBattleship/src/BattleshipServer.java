import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BattleshipServer {
    private static final BattleshipGrid serverGrid = new BattleshipGrid();

    public static void main(String[] args) throws Exception {
        serverGrid.at(0, 0, true);
        serverGrid.at(1, 0, true);
        serverGrid.at(2, 0, true);
        serverGrid.at(3, 0, true);
        serverGrid.at(4, 0, true);
        serverGrid.printGrid();

        try (ServerSocket serverSocket = new ServerSocket(6767)){
            final Socket client = serverSocket.accept();
            InputStream inputStream = client.getInputStream();

            OutputStream outputStream = client.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);  
            while (!serverSocket.isClosed()) {
                printWriter.println("Connection Esablished!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
