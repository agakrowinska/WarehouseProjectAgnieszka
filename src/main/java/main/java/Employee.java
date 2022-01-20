package main.java;

import java.util.List;

public class Employee extends User{

    private String password;
    private List<Employee> headOf;
    //empty List by deafult

    public Employee(String userName) {
        super(userName);
    }
}
