package servlets;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import jakarta.servlet.http.HttpServletRequest;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class XMLHelper {
    public Document doc;
    public Schema schema;

    public XMLHelper() throws ParserConfigurationException {
        this.doc =  createDocument();
    }

    public XMLHelper(String schemaUrl) throws ParserConfigurationException {
        this.doc =  createDocument();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            this.schema = schemaFactory.newSchema(new StreamSource(new File(schemaUrl)));
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    public String readRequestData(HttpServletRequest req) throws IOException {
        StringBuilder requestData = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestData.append(line);
        }
        return requestData.toString();
    }
    
    public Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        return docBuilder.newDocument();
    }

    public Element createContactsElement(Document doc) {
        return doc.createElement("contacts");
    }

    public Element createContactElement(Document doc, Element contactsElement) {
        Element contactElement = doc.createElement("contact");
        contactsElement.appendChild(contactElement);
        return contactElement;
    }

    public void createContactAttributeElements(Document doc, Element contactElement, ResultSet res)
            throws SQLException {
        createElement(doc, contactElement, "name", res.getString("name"));
        createElement(doc, contactElement, "surname", res.getString("surname"));
        createElement(doc, contactElement, "prefix", res.getString("prefix"));
        createElement(doc, contactElement, "number", res.getString("number"));
    }

    public void createElement(Document doc, Element parentElement, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.setTextContent(textContent);
        parentElement.appendChild(element);
    }

    public String transformDocumentToString(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        return writer.toString();
    }

    public Document parseXMLData(String xmlData) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(xmlData));
        return builder.parse(inputSource);
    }
    
    public String extractXMLField(Document xmlData, String fieldName) {
        NodeList nodeList = xmlData.getElementsByTagName(fieldName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return "";
    }

    public String getElementText(Element parentElement, String elementName) {
        NodeList nodeList = parentElement.getElementsByTagName(elementName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return "";
    }
}
