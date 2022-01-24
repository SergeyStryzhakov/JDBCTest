package org.example.dao;

import org.example.entities.Employer;

import java.util.List;

public interface DAOInterface {
    void connect() throws ClassNotFoundException;

    void disconnect();

    void addEmployer(int id, String name, String job, int mgr, String hiredate, int sal, int deptno);

    void updateEmployer(int number, String name);

    List<Employer> getAllEmployers();
    Employer getEmployer(int id);

    void removeEmployer(int number);
}
