package servlets;

import java.sql.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Element;

public class DBService {
    public final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE = "rubrica";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DATABASE;
    private static final String USER = "root";
    private static final String PASS = "RISOSCOTTI";
    public Connection sqlConnection;
    public Statement stmt;

    public DBService() {
        try {
            Class.forName(JDBC_DRIVER);
            this.sqlConnection = DriverManager.getConnection(DB_URL, USER, PASS);
            this.stmt = sqlConnection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAllContactsJSON(int limit) throws SQLException {
        ResultSet res = this.stmt.executeQuery("SELECT * FROM contacts LIMIT " + limit);
        JSONArray contactsArray = new JSONArray();

        while (res.next()) {
            JSONObject contact = new JSONObject();
            contact.put("name", res.getString("name"));
            contact.put("surname", res.getString("surname"));
            contact.put("prefix", res.getString("prefix"));
            contact.put("number", res.getString("number"));
            contactsArray.put(contact);
        }

        return contactsArray.toString();
    }

    public String getAllContactsJSON() throws SQLException {
        ResultSet res = this.stmt.executeQuery("SELECT * FROM contacts");
        JSONArray contactsArray = new JSONArray();

        while (res.next()) {
            JSONObject contact = new JSONObject();
            contact.put("name", res.getString("name"));
            contact.put("surname", res.getString("surname"));
            contact.put("prefix", res.getString("prefix"));
            contact.put("number", res.getString("number"));
            contactsArray.put(contact);
        }

        return contactsArray.toString();
    }

    public String getAllContactsXML(int limit) throws SQLException {
        ResultSet res = this.stmt.executeQuery("SELECT * FROM contacts LIMIT " + limit);

        try {
            XMLHelper xmh = new XMLHelper();
            Element contactsElement = xmh.createContactsElement(xmh.doc);
            xmh.doc.appendChild(contactsElement);

            while (res.next()) {
                Element contactElement = xmh.createContactElement(xmh.doc, contactsElement);
                xmh.createContactAttributeElements(xmh.doc, contactElement, res);
            }

            return xmh.transformDocumentToString(xmh.doc);
        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
            throw new SQLException("Failed to create XML document");
        } catch (TransformerException e) {
            e.printStackTrace();
            throw new SQLException("Failed to transform XML document to string");
        }
    }
    public String getAllContactsXML() throws SQLException {
        ResultSet res = this.stmt.executeQuery("SELECT * FROM contacts");

        try {
            XMLHelper xmh = new XMLHelper();
            Element contactsElement = xmh.createContactsElement(xmh.doc);
            xmh.doc.appendChild(contactsElement);

            while (res.next()) {
                Element contactElement = xmh.createContactElement(xmh.doc, contactsElement);
                xmh.createContactAttributeElements(xmh.doc, contactElement, res);
            }

            return xmh.transformDocumentToString(xmh.doc);
        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
            throw new SQLException("Failed to create XML document");
        } catch (TransformerException e) {
            e.printStackTrace();
            throw new SQLException("Failed to transform XML document to string");
        }
    }

    public int insertContactRecord(String name, String surname, String prefix, String number) throws SQLException {
        String sql = "INSERT INTO contacts (name, surname, prefix, number) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = this.sqlConnection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, surname);
        statement.setString(3, prefix);
        statement.setString(4, number);
        return statement.executeUpdate();
    }

    public int updateContactRecord(String name, String surname, String prefix, String number, int id) throws SQLException {
        String sql = "UPDATE contacts SET name=?, surname=?, prefix=?, number=? WHERE id=?";
        PreparedStatement statement = this.sqlConnection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, surname);
        statement.setString(3, prefix);
        statement.setString(4, number);
        statement.setInt(5, id);
        return statement.executeUpdate();
    }

    public int deleteContactRecord(int id) throws SQLException {
        String sql = "DELETE FROM contacts WHERE id=?";
        PreparedStatement statement = this.sqlConnection.prepareStatement(sql);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    public boolean isRequiredFieldMissing(String name, String surname, String prefix, String number) {
        return name.isEmpty() || surname.isEmpty() || prefix.isEmpty() || number.isEmpty();
    }
}
