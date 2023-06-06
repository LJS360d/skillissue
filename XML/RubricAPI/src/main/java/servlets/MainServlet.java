package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MainServlet extends HttpServlet {
    DBService dbs = new DBService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            resp.setStatus(HttpServletResponse.SC_OK);
            String format = req.getParameter("format");
            String limitParam = req.getParameter("limit");
            int limit = 0;
            if (limitParam != null)
                limit = Integer.parseInt(limitParam);
            if (limit > 0) {
                if (format != null && format.equalsIgnoreCase("xml")) {
                    resp.setContentType("application/xml");
                    resp.getWriter().println(dbs.getAllContactsXML(limit));
                } else {
                    resp.setContentType("application/json");
                    resp.getWriter().println(dbs.getAllContactsJSON(limit));
                }
                return;
            }

            if (format != null && format.equalsIgnoreCase("xml")) {
                resp.setContentType("application/xml");
                resp.getWriter().println(dbs.getAllContactsXML());
            } else {
                resp.setContentType("application/json");
                resp.getWriter().println(dbs.getAllContactsJSON());
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Request Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String contentType = req.getContentType();
        if (contentType == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Missing Content-Type header");
            return;
        }
        switch (contentType) {
            case "application/x-www-form-urlencoded":
                handleFormPost(req, resp);
                break;
            case "application/json":
                handleJSONPost(req, resp);
                break;
            case "application/xml":
                handleXMLPost(req, resp);
                break;
            default:
                resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                resp.getWriter().println("Unsupported media type: " + contentType);
                break;
        }

    }

    protected void handleFormPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");

        try {
            // Retrieve the form field values from the request parameters
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String prefix = req.getParameter("prefix");
            String number = req.getParameter("number");

            // Validate the required fields
            if (dbs.isRequiredFieldMissing(name, surname, prefix, number)) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Missing required fields");
                return;
            }

            int rowsAffected = dbs.insertContactRecord(name, surname, prefix, number);

            if (rowsAffected > 0) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().println("Record inserted successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().println("Failed to insert the record");
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Database error:" + e.getMessage());
        }
    }

    protected void handleJSONPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");

        try {
            JSONHelper jsh = new JSONHelper();
            String requestData = jsh.readRequestData(req);
            JSONObject jsonData = jsh.parseRequestData(requestData);
            String name = jsh.extractField(jsonData, "name");
            String surname = jsh.extractField(jsonData, "surname");
            String prefix = jsh.extractField(jsonData, "prefix");
            String number = jsh.extractField(jsonData, "number");

            if (dbs.isRequiredFieldMissing(name, surname, prefix, number)) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Missing required fields");
                return;
            }

            int rowsAffected = dbs.insertContactRecord(name, surname, prefix, number);

            if (rowsAffected > 0) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().println("Record inserted successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().println("Failed to insert the record");
            }
        } catch (JSONException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Invalid JSON format:" + e.getMessage());
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Database error:" + e.getMessage());
        }
    }

    private void handleXMLPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/xml");

        try {
            // Read the XML data from the request body
            XMLHelper xmh = new XMLHelper();
            String requestData = xmh.readRequestData(req);
            xmh.doc = xmh.parseXMLData(requestData);
            // Extract the required fields from XML
            Element contactElement = xmh.doc.getDocumentElement();

            String name = xmh.getElementText(contactElement, "name");
            String surname = xmh.getElementText(contactElement, "surname");
            String prefix = xmh.getElementText(contactElement, "prefix");
            String number = xmh.getElementText(contactElement, "number");

            // Validate the required fields
            if (name.isEmpty() || surname.isEmpty() || prefix.isEmpty() || number.isEmpty()) {
                // Send HTTP 400 Bad Request if any required field is missing
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Missing required fields");
                return;
            }

            // Execute the SQL statement to insert the record
            int rowsAffected = dbs.insertContactRecord(name, surname, prefix, number);

            if (rowsAffected > 0) {
                // If the record was inserted successfully
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().println("Record inserted successfully");
            } else {
                // If the record was not inserted
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().println("Failed to insert the record");
            }
        } catch (ParserConfigurationException | SAXException e) {
            // If there was an error parsing the XML data
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Invalid XML format: " + e.getMessage());
        } catch (SQLException e) {
            // If there was an error executing the SQL statement
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Database error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Missing required parameter: id");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Invalid id parameter");
            return;
        }

        String contentType = req.getContentType();
        if (contentType == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Missing Content-Type header");
            return;
        }

        if (contentType.startsWith("application/json")) {
            handleJSONPut(req, resp, id);
        } else if (contentType.startsWith("application/xml")) {
            handleXMLPut(req, resp, id);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            resp.getWriter().println("Unsupported Content-Type: " + contentType);
        }
    }

    protected void handleJSONPut(HttpServletRequest req, HttpServletResponse resp, int id)
            throws ServletException, IOException {
        resp.setContentType("application/json");

        try {
            JSONHelper jsh = new JSONHelper();
            String requestData = jsh.readRequestData(req);
            JSONObject jsonData = jsh.parseRequestData(requestData);
            String name = jsh.extractField(jsonData, "name");
            String surname = jsh.extractField(jsonData, "surname");
            String prefix = jsh.extractField(jsonData, "prefix");
            String number = jsh.extractField(jsonData, "number");

            if (dbs.isRequiredFieldMissing(name, surname, prefix, number)) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Missing required fields");
                return;
            }

            int rowsAffected = dbs.updateContactRecord(name, surname, prefix, number, id);
            if (rowsAffected > 0) {
                // If the record was updated successfully
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("Record updated successfully");
            } else {
                // If the record was not found
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("Record not found");
            }

        } catch (JSONException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Invalid JSON format:" + e.getMessage());
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Database error: " + e.getMessage());
        }
    }

    protected void handleXMLPut(HttpServletRequest req, HttpServletResponse resp, int id)
            throws ServletException, IOException {
        resp.setContentType("application/json");

        try {
            // Read the XML data from the request body
            XMLHelper xmh = new XMLHelper();
            String requestData = xmh.readRequestData(req);
            xmh.doc = xmh.parseXMLData(requestData);

            // Extract the required fields from XML
            Element rootElement = xmh.doc.getDocumentElement();
            NodeList contactNodes = rootElement.getElementsByTagName("contact");
            if (contactNodes.getLength() == 0) {
                throw new IllegalArgumentException("No <contact> element found in XML");
            }

            Element contactElement = (Element) contactNodes.item(0);
            String name = xmh.getElementText(contactElement, "name");
            String surname = xmh.getElementText(contactElement, "surname");
            String prefix = xmh.getElementText(contactElement, "prefix");
            String number = xmh.getElementText(contactElement, "number");

            // Validate the required fields
            if (name.isEmpty() || surname.isEmpty() || prefix.isEmpty() || number.isEmpty()) {
                // Send HTTP 400 Bad Request if any required field is missing
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Missing required fields");
                return;
            }

            // Execute the SQL statement to update the record
            int rowsAffected = dbs.updateContactRecord(name, surname, prefix, number, id);

            if (rowsAffected > 0) {
                // If the record was updated successfully
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("Record updated successfully");
            } else {
                // If the record was not updated
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().println("Record not found");
            }
        } catch (ParserConfigurationException | SAXException e) {
            // If there was an error parsing the XML data
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Invalid XML format: " + e.getMessage());
        } catch (SQLException e) {
            // If there was an error executing the SQL statement
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Database error: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");

        try {
            // Extract the ID from the request parameters
            String idParam = req.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Missing ID parameter");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Invalid ID parameter");
                return;
            }

            // Execute the Delete
            int rowsAffected = dbs.deleteContactRecord(id);

            if (rowsAffected > 0) {
                // If the record was deleted successfully
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("Record deleted successfully");
            } else {
                // If the record was not found
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("Record not found");
            }
        } catch (SQLException e) {
            // If there was an error executing the SQL statement
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Database error: " + e.getMessage());
        }
    }

}
