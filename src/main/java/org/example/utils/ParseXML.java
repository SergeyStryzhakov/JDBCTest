package org.example.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class ParseXML {
    private String sourceName;
    private String connectionUrl;
    private String driverClass;
    private String userName;
    private String password;

    public String getSourceName() {
        return sourceName;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void readXML() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        documentBuilder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println(exception.getMessage());

            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.out.println(exception.getMessage());
                System.exit(0);
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println(exception.getMessage());
                System.exit(0);
            }
        });
        Document document = documentBuilder.parse("datasource.xml");
        document.getDocumentElement().normalize();
        NodeList list = document.getElementsByTagName("datasource");

        for (int i = 0; i < list.getLength(); i++) {
            Node datasource = list.item(i);
            if (datasource.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) datasource;
                sourceName = element.getElementsByTagName("source-name").item(0).getTextContent();
                connectionUrl = element.getElementsByTagName("connection-url").item(0).getTextContent();
                driverClass = element.getElementsByTagName("driver-class").item(0).getTextContent();
                userName = element.getElementsByTagName("user-name").item(0).getTextContent();
                password = element.getElementsByTagName("password").item(0).getTextContent();
            }
        }
    }
}
