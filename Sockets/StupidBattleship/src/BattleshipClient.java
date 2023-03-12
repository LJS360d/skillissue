import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BattleshipClient {
    private static final BattleshipGrid grid = new BattleshipGrid();

    public static void main(String[] args) throws Exception {
        grid.at(0, 0, true);
        grid.at(1, 0, true);
        grid.at(2, 0, true);
        grid.at(3, 0, true);
        grid.at(4, 0, true);

        grid.printGrid();
        try (Socket socket = new Socket("localhost", 6767)) {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            
            OutputStream outputStream = socket.getOutputStream();

            String msg = bufferedReader.readLine();
            System.out.println(msg);
            BattleshipGrid obj = (BattleshipGrid) objectInputStream.readObject();
            System.out.println(obj.toString());

        }
    }

}
