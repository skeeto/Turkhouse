package turkhouse;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.util.Random;

public class Turkhouse extends java.awt.Robot implements Runnable {

    private static final Random RNG = new Random();

    private static final Point SEARCH = new Point(800, 188);
    private static final Point BUYOUT = new Point(760, 560);
    private static final Point ACCEPT = new Point(770, 220);
    private static final Point SILVER = new Point(810, 270);
    private static final Point GOLD = new Point(772, 270);

    private static final int SECOND = 1000;

    public static void main(String[] args) {
        try {
            new Thread(new Turkhouse()).start();
        } catch (java.awt.AWTException e) {
            System.out.println("error: failed to start robot");
        }
    }

    public Turkhouse() throws java.awt.AWTException {
        super();
        setAutoDelay(250);
    }

    @Override
    public void run() {
        delay(1 * SECOND);
        while (true) {
            click(SEARCH);
            delay((int) (1.5 * SECOND));
            while (isCheap()) {
                System.out.println("looks cheap, buying");
                click(BUYOUT);
                click(ACCEPT);
                delay(1 * SECOND);
            }
            int delay = RNG.nextInt(10 * SECOND) + 10 * SECOND;
            System.out.println("Waiting " + (delay / 1000.0) + " seconds.");
            delay(delay);
        }
    }

    private void click(Point p) {
        mouseMove(p.x, p.y);
        mousePress(InputEvent.BUTTON1_MASK);
        mouseRelease(InputEvent.BUTTON1_MASK);
    }

    private Color sample(Point p) {
        Rectangle pixel = new Rectangle(p.x, p.y, 1, 1);
        return new Color(createScreenCapture(pixel).getRGB(0, 0));
    }

    private boolean isCheap() {
        Color silver = sample(SILVER);
        Color gold = sample(GOLD);
        return (average(gold) < 127) && (average(silver) < 127);
    }

    private int average(Color c) {
        return (c.getRed() + c.getGreen() + c.getBlue()) / 3;
    }
}
