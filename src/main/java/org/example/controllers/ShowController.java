package org.example.controllers;

import org.example.dao.DAOInterface;
import org.example.entities.Employer;
import org.example.view.View;

public class ShowController implements Controller{
    DAOInterface dao;
    View view;

    public ShowController(View view, DAOInterface dao) {
        this.dao = dao;
        this.view = view;
    }

    @Override
    public void start() {
        int id = view.getId();
        Employer employer = dao.getEmployer(id);
        if(employer == null) {
            System.out.println("Employer with id " + id + " not found\n");
                    } else {
            System.out.println(employer + "\n");
        }
        Controller controller = new MainController(view, dao);
        controller.start();
    }
}
