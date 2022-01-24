package org.example.controllers;

import org.example.dao.DAOInterface;
import org.example.view.View;

public class RemoveController implements Controller {
    DAOInterface dao;
    View view;

    public RemoveController(View view, DAOInterface dao) {
        this.dao = dao;
        this.view = view;
    }

    @Override
    public void start() {
        int id = view.getId();
        dao.removeEmployer(id);
        System.out.println("Employer with id " + id + " is removed successfully");
        Controller controller = new MainController(view, dao);
        controller.start();
    }
}
