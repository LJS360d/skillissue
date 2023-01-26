import java.sql.*;
import java.util.Scanner;

public class JDBCScuola {
    static final String DATABASE = "scuola";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/" + DATABASE;
    static final String USER = "root";
    static final String PASS = getPassword();

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            System.err.println("An Error occured");
        }
        while (true) {
            System.out.println("1: Esegui e stampa i risultati di una qualsiasi query");
            System.out.println("2: Inserisci uno studente");
            System.out.println("3: Modifica uno studente");
            System.out.println("4: Elimina uno studente");
            System.out.println("5: Visualizza uno studente");
            System.out.println("6: Visualizza tutti gli studenti");
            System.out.println("--------------------------------");
            System.out.println("9: Exit");
            int choice = scan.nextInt();

            if (choice == 9) {
                scan.close();
                break;
            }

            scan.nextLine();
            String query = null;
            ResultSet rs = null;
            String codice_fiscale, nome, cognome, date, corso, sezione, anno, campi, valore;
            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.println("Enter your query:");
                        query = scan.nextLine();
                        rs = stmt.executeQuery(query);
                        printQueryResults(rs);
                        break;
                    case 2:// INSERT into
                           // studenti(codice_fiscale,nome,cognome,data_nascita,corso,sezione,anno
                        System.out.println("Codice fiscale:");
                        codice_fiscale = "'" + scan.nextLine() + "'";
                        System.out.println("Nome");
                        nome = "'" + scan.nextLine() + "'";
                        System.out.println("Cognome:");
                        cognome = "'" + scan.nextLine() + "'";
                        System.out.println("Data di nascita (YYYY-MM-DD)");
                        date = "'" + scan.nextLine() + "'";
                        System.out.println("Corso:");
                        corso = "'" + scan.nextLine() + "'";
                        System.out.println("Sezione:");
                        sezione = "'" + scan.nextLine() + "'";
                        System.out.println("Anno:");
                        anno = "'" + scan.nextLine() + "'";
                        query = "INSERT into studenti VALUES (" + codice_fiscale + "," + nome + "," + cognome + ","
                                + date + "," + corso + "," + sezione + "," + anno + ")";
                        printUpdateResults(stmt.executeUpdate(query));
                        break;
                    case 3:// UPDATE $table SET $campo = $valore
                        System.out.println("Codice fiscale dello studente da modificare:");
                        codice_fiscale = "'" + scan.nextLine() + "'";
                        System.out.println("Inserisci il campo da modificare:");
                        System.out.println("(nome,cognome,nascita,corso,sezione,anno)");
                        campi = scan.nextLine();
                        System.out.println("Inserisci il valore da inserire in " + campi);
                        valore = "'" + scan.nextLine() + "'";
                        query = "UPDATE studenti SET " + campi + "=" + valore + "WHERE codice_fiscale="
                                + codice_fiscale;
                        printUpdateResults(stmt.executeUpdate(query));
                        break;
                    case 4:// DELETE from $table WHERE $pk = $value
                        System.out.println("Codice fiscale dello studente da eliminare:");
                        codice_fiscale = "'" + scan.nextLine() + "'";
                        query = "DELETE from studenti WHERE codice_fiscale=" + codice_fiscale;
                        printUpdateResults(stmt.executeUpdate(query));
                        break;
                    case 5:// SELECT * from studenti where pk=$pk
                        System.out.println("Codice fiscale dello studente");
                        codice_fiscale = "'" + scan.nextLine() + "'";
                        query = "SELECT * from studenti where codice_fiscale=" + codice_fiscale;
                        rs = stmt.executeQuery(query);
                        printQueryResults(rs);
                        break;
                    case 6:// SELECT * from studenti
                        rs = stmt.executeQuery("SELECT * from studenti");
                        printQueryResults(rs);
                        break;
                }
            } catch (SQLException e) {
                System.err.println("Could not execute the query");
            }
        }
        // While Breakline
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Could not close the connection");
        }
    }

    private static void printQueryResults(ResultSet rs) {
        try {
            while (rs.next()) {
                for (int i = 1; i < rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "|");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printUpdateResults(int r) {
        if (r > 0) {
            System.out.println("Success!");
        } else {
            System.err.println("Command Could not be executed");
        }
    }

    private static String getPassword() {
        return "RISOSCOTTI";
    }

}
