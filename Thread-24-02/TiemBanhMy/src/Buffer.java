package lab3;

import java.util.ArrayList;

public class Buffer {
    private int capacity;
    private ArrayList<Integer> products = new ArrayList<>();

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void addProduct(int product, int producerId) {
        while (products.size() >= capacity) {
            try {
                System.out.println("Kho đầy, nhân viên " + producerId + " đang chờ...");
                wait(100); // Chờ 100ms rồi kiểm tra lại
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Producer bị gián đoạn!");
                return;
            }
        }
        products.add(product);
        System.out.println("Nhân viên " + producerId + " đã thêm bánh mì: " + product);
        System.out.println("Tồn kho hiện tại: " + products.size());
        notifyAll();
    }

    public synchronized void removeProduct(int consumerId) {
        while (products.isEmpty()) {
            try {
                System.out.println("Kho rỗng, khách hàng " + consumerId + " đang chờ...");
                wait(100); // Chờ 100ms rồi kiểm tra lại
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Consumer bị gián đoạn!");
                return;
            }
        }
        System.out.println("Khách hàng " + consumerId + " đã mua bánh mì " + products.get(0));
        products.remove(0);
        System.out.println("Tồn kho sau bán: " + products.size());
        notifyAll();
    }
}
