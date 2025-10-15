

interface Student {

    void display(String S);
}

public class Lam {

    public static void main(String[] args) {
        Student s = (name) -> {
            System.out.println("hello\t" + name);
        };
        s.display("harsha");
    
    Runnable r = () -> {
        System.out.println("Inside Runnable");
    };

    new Thread(r).start();
    }
}