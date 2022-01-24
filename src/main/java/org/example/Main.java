package org.example;

import org.example.controllers.Controller;
import org.example.controllers.MainController;
import org.example.dao.DAOInterface;
import org.example.dao.DAOOracleImpl;
import org.example.view.View;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DAOInterface dao = new DAOOracleImpl();
        View view = new View();
        Controller controller = new MainController(view, dao);
        controller.start();

    }
}
