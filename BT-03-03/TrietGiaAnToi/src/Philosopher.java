import java.util.List;
import java.util.ArrayList;

class Philosopher extends Thread {
    private final int id;
    private final Chopstick leftChopstick, rightChopstick;
    private int eatCount = 0;
    private static final int MAX_EAT = 20;
    private static final List<Integer> eatingPhilosophers = new ArrayList<>();

    public Philosopher(int id, Chopstick left, Chopstick right) {
        this.id = id + 1; // Đánh số từ 1 đến 5
        this.leftChopstick = left;
        this.rightChopstick = right;
    }

    private void think() throws InterruptedException {
        synchronized (eatingPhilosophers) {
            if (!eatingPhilosophers.contains(id)) {
                System.out.println("Philosopher " + id + " is thinking.");
                printStatus();
            }
        }
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        synchronized (eatingPhilosophers) {
            eatingPhilosophers.add(id);
            System.out.println("Philosopher " + id + " is eating.");
            printStatus();
        }
        Thread.sleep((long) (Math.random() * 1000));
        synchronized (eatingPhilosophers) {
            eatingPhilosophers.remove(Integer.valueOf(id));
        }
    }

    private void printStatus() {
        List<Integer> thinkingPhilosophers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            if (!eatingPhilosophers.contains(i)) {
                thinkingPhilosophers.add(i);
            }
        }
        System.out.println("Eating Philosophers: " + eatingPhilosophers);
        System.out.println("Thinking Philosophers: " + thinkingPhilosophers);
    }

    @Override
    public void run() {
        try {
            while (eatCount < MAX_E
            AT) {
                think();
                synchronized (leftChopstick) {
                    leftChopstick.pickUp();
                    synchronized (rightChopstick) {
                        rightChopstick.pickUp();
                        eat();
                        rightChopstick.putDown();
                    }
                    leftChopstick.putDown();
                }
                eatCount++;
            }
            System.out.println("Philosopher " + id + " has finished eating.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}