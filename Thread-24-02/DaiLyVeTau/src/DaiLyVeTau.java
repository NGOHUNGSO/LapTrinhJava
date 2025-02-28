package lab2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketSellerApp {
    public static void main(String[] args) {
        TicketSeller seller = new TicketSeller();

        // Tạo và khởi chạy 3 luồng đại diện cho 3 nhân viên bán vé
        Thread t1 = new Thread(seller, "Nhân viên 1");
        Thread t2 = new Thread(seller, "Nhân viên 2");
        Thread t3 = new Thread(seller, "Nhân viên 3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketSeller implements Runnable {
    private static int tickets = 10; // Số lượng vé ban đầu
    private static final Lock lock = new ReentrantLock(); // Khóa đồng bộ hóa

    @Override
    public void run() {
        while (true) {
            if (sellTicket()) {
                try {
                    Thread.sleep(100); // Giả lập thời gian xử lý bán vé
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Đảm bảo không làm gián đoạn luồng
                    System.err.println(Thread.currentThread().getName() + " bị gián đoạn!");
                }
            } else {
                break; // Nếu không còn vé, kết thúc vòng lặp
            }
        }
    }

    /**
     * Phương thức bán vé với kiểm soát đồng bộ hóa.
     * @return true nếu bán được vé, false nếu hết vé.
     */
    private boolean sellTicket() {
        if (lock.tryLock()) { // Kiểm tra xem có thể lấy khóa không
            try {
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() + " bán vé: " + tickets--);
                    return true;
                } else {
                    System.out.println(Thread.currentThread().getName() + " thấy vé đã hết.");
                    return false;
                }
            } finally {
                lock.unlock(); // Giải phóng khóa
            }
        } else {
            return true; // Nếu không lấy được khóa, tiếp tục thử
        }
    }
}
