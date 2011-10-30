package turkhouse;

public class Turkhouse extends java.awt.Robot implements Runnable {

    public static void main(String[] args) {
        try {
            new Thread(new Turkhouse()).start();
        } catch (java.awt.AWTException e) {
            System.out.println("error: failed to start robot");
        }
    }

    public Turkhouse() throws java.awt.AWTException {
        super();
    }

    @Override
    public void run() {

    }
}
