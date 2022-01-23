package main.java;

import java.lang.reflect.Method;
import java.util.List;

public class Employee extends User {

    private String password;
    private List<Employee> headOf;
    //empty List by default

    public Employee(String userName, String password) {
        super(userName);
        this.password = password;
    }

    @Override
    public boolean authenticate(String password) {
        if (password == this.password) {
            return true;
        }else{return false;}
    }

    public void order (String item, int amount){
        System.out.println("You have ordered " + amount +" " + item + ".");
    }

    public void greet(){
        System.out.println("Hello, " + name + "!\n" +
                "If you experience a problem with the system,\n" +
                "please contact technical support.");
    }

    @Override
    public void bye(){
        //super.bye();
        //Class action = Class.forName("TheWarehouseManager");
        //Object warehouseManagerInstance = action.newInstance();
        //Method sessions = action.getDeclaredMethod("listSessionActions",null);
        //sessions.setAccessible(true);
        TheWarehouseManager action = new TheWarehouseManager();
        System.out.println(action.SESSION_ACTIONS);



    }
}
