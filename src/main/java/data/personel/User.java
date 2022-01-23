package data.personel;

//import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;

public class User {

    protected String name = "Anonymous";
    private boolean isAuthenticated = false;

    public String getName() {
        return name;
    }

    public User(String userName) {
        this.name = userName;
    }

    public boolean authenticate(String password) {
        return false;
    }

    public boolean isNamed(String name) {
        if (name == this.name) {
            return true;
        }else{
            return false;
        }
    }


        public void greet() {
            System.out.println("Hello, " + name + "!\n" +
                    "Welcome to our Warehouse Database.\n" +
                    "If you don't find what you are looking for,\n" +
                    "please ask one of our staff members to assist you.");

        }

        public void bye() {
            System.out.println("Thank you. Have a great day!");
        }

    }


