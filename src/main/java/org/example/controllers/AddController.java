package org.example.controllers;

import org.example.dao.DAOInterface;
import org.example.view.View;

public class AddController implements Controller {
    View view;
    DAOInterface dao;

    public AddController(View view, DAOInterface dao) {
        this.view = view;
        this.dao = dao;
    }

    @Override
    public void start() {
        int id = view.getId();
        String name = view.getName();
        String job = view.getJob();
        int mgr = view.getMgr();
        String hiredate = view.getHireDate();
        int sal = view.getSalary();
        int deptno = view.getDeptNo();
        dao.addEmployer(id, name, job, mgr, hiredate, sal, deptno);
        Controller controller = new MainController(view, dao);
        controller.start();
    }
}
