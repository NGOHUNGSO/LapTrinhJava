public class ThreadB extends Thread {
    public void run() {
        synchronized (SharedResource.resource1) {  // Lấy resource1 trước
            System.out.println("Thread B: Đã giữ resource1.");
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            synchronized (SharedResource.resource2) {
                System.out.println("Thread B: Đã giữ cả hai resource.");
            }
        }
    }
}
