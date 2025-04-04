import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.List;

public class OrderDAO {
    private final MongoCollection<Document> orderCollection;
    private final MongoCollection<Document> productCollection;

    public OrderDAO() {
        MongoDatabase db = MongoUtil.getDatabase();
        orderCollection = db.getCollection("orders");
        productCollection = db.getCollection("products");
    }

    public double getOrderTotal(String orderId) {
        Document order = orderCollection.find(new Document("_id", new ObjectId(orderId))).first();
        if (order == null) return 0;

        List<Document> items = (List<Document>) order.get("items");
        double total = 0;

        for (Document item : items) {
            String productId = item.getString("productId");
            int quantity = item.getInteger("quantity");

            Document product = productCollection.find(new Document("productId", productId)).first();
            if (product != null) {
                double price = product.getDouble("price");
                total += price * quantity;
            }
        }

        return total;
    }

    public void addOrder(String customerId, List<Document> items) {
        Document order = new Document("customerId", customerId)
                .append("orderDate", new java.util.Date())
                .append("items", items);
        orderCollection.insertOne(order);
        System.out.println("Order added successfully: " + order.getObjectId("_id"));
    }

    public List<Document> getOrdersByCustomer(String customerId) {
        return orderCollection.find(new Document("customerId", customerId)).into(new java.util.ArrayList<>());
    }
}
