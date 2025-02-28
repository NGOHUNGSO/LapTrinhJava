package lab3;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(8); // Kho chứa tối đa 8 bánh mì

        Producer producer = new Producer(100, buffer); // Nhân viên làm bánh
        Consumer consumer = new Consumer(123, buffer); // Khách hàng mua bánh

        producer.start();
        consumer.start();
    }
}
