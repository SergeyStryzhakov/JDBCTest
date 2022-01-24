package org.example.dao;

import org.example.entities.Employer;
import org.example.utils.ParseXML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOOracleImpl implements DAOInterface {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    private String connectionUrl;
    private String driverClass;
    private String userName;
    private String password;


    public DAOOracleImpl() throws ParserConfigurationException, IOException, SAXException {
        getConnectionProp();
    }

    private void getConnectionProp() throws ParserConfigurationException, IOException, SAXException {
        ParseXML prop = new ParseXML();
        prop.readXML();
        connectionUrl = prop.getConnectionUrl();
        driverClass = prop.getDriverClass();
        userName = prop.getUserName();
        password = prop.getPassword();
    }

    @Override
    public void connect() {
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(connectionUrl, userName, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            if (!resultSet.isClosed()) {
                resultSet.close();
            }
            if (!preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addEmployer(int id, String name, String job, int mgr, String hiredate, int sal, int deptno) {
        connect();
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, DEPTNO)" +
                            "VALUES (:1, :2, :3, :4, TO_DATE(:5, 'YYYY-MM-DD'), :6, :7)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, job);
            preparedStatement.setInt(4, mgr);
            preparedStatement.setString(5, hiredate);
            preparedStatement.setInt(6, sal);
            preparedStatement.setInt(7, deptno);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        disconnect();

    }


    @Override
    public void updateEmployer(int id, String name) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("UPDATE EMP SET ENAME = :1 WHERE EMPNO = :2");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        disconnect();

    }

    @Override
    public List<Employer> getAllEmployers() {
        List<Employer> employerList = new ArrayList<>();
        connect();
        try {
            preparedStatement = connection.prepareStatement("select e.EMPNO, e.ENAME, e.JOB, e.MGR, " +
                    " e.HIREDATE, e.SAL, e.COMM, e.DEPTNO, d.LOC, d.DNAME, " +
                    "(SELECT GRADE from SALGRADE where e.SAL between MINSAL and HISAL) as Grade " +
                    "from emp e, DEPT d where e.DEPTNO = d.DEPTNO");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employerList.add(parseEmp(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return employerList;
    }

    @Override
    public Employer getEmployer(int id) {
        Employer employer = null;
        connect();
        try {
            preparedStatement = connection.prepareStatement("select e.EMPNO, e.ENAME, e.JOB, e.MGR, " +
                    " e.HIREDATE, e.SAL, e.COMM, e.DEPTNO, d.LOC, d.DNAME, " +
                    "(SELECT GRADE from SALGRADE where e.SAL between MINSAL and HISAL) as Grade " +
                    "from emp e, DEPT d where e.DEPTNO = d.DEPTNO AND e.EMPNO = :1");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employer = parseEmp(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        disconnect();
        return employer;
    }

    private Employer parseEmp(ResultSet resultSet) {
        Employer employer = null;
        try {
            int number = resultSet.getInt("EMPNO");
            String name = resultSet.getString("ENAME");
            String job = resultSet.getString("JOB");
            int mgr = resultSet.getInt("MGR");
            String hiredate = resultSet.getDate("HIREDATE").toString();
            int sal = resultSet.getInt("SAL");
            int deptno = resultSet.getInt("DEPTNO");
            String location = resultSet.getString("LOC");
            String depName = resultSet.getString("DNAME");
            int grade = resultSet.getInt("Grade");
            employer = new Employer(number, name, job, mgr, hiredate, sal, deptno, location, depName, grade);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employer;
    }

    @Override
    public void removeEmployer(int id) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM EMP WHERE EMPNO = :1");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();

    }
}
