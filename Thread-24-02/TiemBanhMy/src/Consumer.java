package lab3;

public class Consumer extends Thread {
    private int id;
    private Buffer buffer;
    private static final int MAX_ITERATIONS = 20; // Giới hạn số lần chạy

    public Consumer(int id, Buffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    public void run() {
        for (int count = 0; count < MAX_ITERATIONS; count++) {
            try {
                buffer.removeProduct(this.id);
                Thread.sleep((long) (Math.random() * 100)); // Giảm tải CPU
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println("Khách hàng " + id + " đã hoàn thành mua hàng!");
    }
}
