package org.example.controllers;

import org.example.dao.DAOInterface;
import org.example.view.View;

public class UpdateEmployerController implements Controller{
    DAOInterface dao;
    View view;

    public UpdateEmployerController(View view, DAOInterface dao) {
        this.dao = dao;
        this.view = view;
    }

    @Override
    public void start() {
        int id = view.getId();
        String name = view.getName();
        dao.updateEmployer(id, name);
        System.out.println("Update is successfully\n");
        Controller controller = new MainController(view, dao);
        controller.start();
    }
}
