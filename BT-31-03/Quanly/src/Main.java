import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();

        // 1. Add new order
        List<Document> items = Arrays.asList(
                new Document("productId", "p001").append("quantity", 2),
                new Document("productId", "p002").append("quantity", 1)
        );
        dao.addOrder("cus001", items);

        // 2. Get order history for customer
        List<Document> orders = dao.getOrdersByCustomer("cus001");
        orders.forEach(System.out::println);

        // 3. Calculate total of an order (copy a valid _id from DB to test)
        if (!orders.isEmpty()) {
            String testOrderId = orders.get(0).getObjectId("_id").toString();
            double total = dao.getOrderTotal(testOrderId);
            System.out.println("Total for order: " + total);
        } else {
            System.out.println("No orders found for customer cus001.");
        }
    }
}