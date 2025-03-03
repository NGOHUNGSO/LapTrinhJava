public class ThreadA extends Thread {
    public void run() {
        synchronized (SharedResource.resource1) {
            System.out.println("Thread A: Đã giữ resource1.");
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            synchronized (SharedResource.resource2) {
                System.out.println("Thread A: Đã giữ cả hai resource.");
            }
        }
    }
}
