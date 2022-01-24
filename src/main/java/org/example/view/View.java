package org.example.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {


    public int show() {
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select item: ");
        System.out.println("1. Show all employers");
        System.out.println("2. Show employer by id");
        System.out.println("3. Add new employer");
        System.out.println("4. Update employer by id");
        System.out.println("5. Remove employer");
        System.out.println("6. Exit");
        try {
            select = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Incorrect select. Try again!");
            show();
        }
        return select;
    }

    public int getId() {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        System.out.println("Enter id: ");
        try {
            id = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Incorrect id. Try again!");
            getId();
        }
        return id;
    }

    public String getName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter employer name: ");
        return scanner.nextLine();
    }

    public String getJob() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter employer job: ");
        return scanner.nextLine();
    }

    public int getMgr() {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        System.out.println("Enter employer managers id: ");
        try {
            id = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Incorrect id. Try again!");
            getMgr();
        }
        return id;
    }

    public int getSalary() {
        Scanner scanner = new Scanner(System.in);
        int sal = 0;
        System.out.println("Enter employers salary: ");
        try {
            sal = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Incorrect id. Try again!");
            getSalary();
        }
        return sal;
    }

    public int getDeptNo() {
        Scanner scanner = new Scanner(System.in);
        int dept = 0;
        System.out.println("Enter number of department: ");
        try {
            dept = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Incorrect id. Try again!");
            getDeptNo();
        }
        return dept;
    }

    public String getHireDate() {
        Scanner scanner = new Scanner(System.in);
        String patternDate = "\\d{4}-\\d{2}-\\d{2}";
        System.out.println("Enter hiredate in format yyyy-mm-dd: ");
        String hiredate = scanner.nextLine();
        if (!hiredate.matches(patternDate)) {
            System.out.println("Wrong date format? try again!");
            getHireDate();
        }
        return hiredate;
    }

}
