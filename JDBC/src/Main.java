import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/world";

    static final String USER = "root";
    static final String PASS = Main.getPassword();

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        Scanner scan = new Scanner(System.in);
        try {

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            System.out.println("Insert Query:");
            String query = scan.nextLine();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int ID = rs.getInt("ID");
                String Name = rs.getString("Name");
                String CountryCode = rs.getString("CountryCode");
                String District = rs.getString("District");
                int Population = rs.getInt("Population");

                System.out.print("ID: " + ID);
                System.out.print(", Name: " + Name);
                System.out.print(", CountryCode: " + CountryCode);
                System.out.print(", District: " + District);
                System.out.println(", Population: " + Population);
            }
            scan.close();
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static String getPassword() {
        return "RISOSCOTTI";
    }
}
