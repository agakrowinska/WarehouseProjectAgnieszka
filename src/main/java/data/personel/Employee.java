package data.personel;

import main.java.TheWarehouseManager;

import java.util.List;

public class Employee extends User {

    private String password;

    public String getPassword() {
        return password;
    }

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
        System.out.println("Thank you. Have a great day!");
        TheWarehouseManager action = new TheWarehouseManager();
        System.out.println(action.SESSION_ACTIONS);



    }
}
