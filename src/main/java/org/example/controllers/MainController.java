package org.example.controllers;

import org.example.dao.DAOInterface;
import org.example.entities.Employer;
import org.example.view.View;

import java.util.List;

public class MainController implements Controller {
    View view;
    DAOInterface dao;
    Controller controller;

    public MainController(View view, DAOInterface dao) {
        this.dao = dao;
        this.view = view;

    }

    public void start() {
        int select = view.show();
        switch (select) {
            case 1:
                showAllEmployers();
            case 2:
                controller = new ShowController(view, dao);
                controller.start();
            case 3:
                controller = new AddController(view, dao);
                controller.start();
            case 4:
                controller = new UpdateEmployerController(view, dao);
                controller.start();
            case 5:
                controller = new RemoveController(view, dao);
                controller.start();
            case 6:
                System.exit(0);
        }

    }

    private void showAllEmployers() {
        System.out.println("ID\tName\tJob\tManager\tHiredate\tSalary\tDeptNumber\tLocation\tDeptName\tGrade");
        List<Employer> employerList = dao.getAllEmployers();
        employerList.forEach(System.out::println);
        System.out.println("");
        start();
    }
}
