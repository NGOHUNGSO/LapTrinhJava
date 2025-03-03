public class Chopstick {
    private boolean isTaken = false;

    public synchronized void pickUp() throws InterruptedException {
        while (isTaken) {
            wait();
        }
        isTaken = true;
    }

    public synchronized void putDown() {
        isTaken = false;
        notifyAll();
    }
}
