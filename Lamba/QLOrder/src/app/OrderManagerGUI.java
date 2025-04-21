package app;

import model.*;
import service.OrderProcessor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderManagerGUI extends JFrame {
    private final JTextField orderDateField = new JTextField(10);
    private final JTextField deliveryDateField = new JTextField(10);
    private final JTextField statusField = new JTextField(10);
    private final JTextField tierField = new JTextField(3);

    private final JTextField productNameField = new JTextField(10);
    private final JTextField productCategoryField = new JTextField(10);
    private final JTextField productPriceField = new JTextField(5);

    private final DefaultTableModel productTableModel = new DefaultTableModel(new String[]{"Tên", "Loại", "Giá"}, 0);
    private final JTable productTable = new JTable(productTableModel);

    private final JTextArea resultArea = new JTextArea(12, 70);

    private final List<Order> orders = new ArrayList<>();
    private final List<Product> currentProducts = new ArrayList<>();

    public OrderManagerGUI() {
        setTitle("Quản lý đơn hàng & sản phẩm");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ========== Panel Đơn hàng ==========
        JPanel orderPanel = new JPanel(new GridLayout(2, 5, 10, 5));
        orderPanel.setBorder(BorderFactory.createTitledBorder("Thông tin đơn hàng"));

        orderPanel.add(new JLabel("Ngày đặt (yyyy-MM-dd):"));
        orderPanel.add(orderDateField);
        orderPanel.add(new JLabel("Ngày giao:"));
        orderPanel.add(deliveryDateField);
        orderPanel.add(new JLabel("Trạng thái:"));
        orderPanel.add(statusField);
        orderPanel.add(new JLabel("Tier (1–3):"));
        orderPanel.add(tierField);

        // ========== Panel Sản phẩm ==========
        JPanel productPanel = new JPanel(new GridLayout(2, 4, 10, 5));
        productPanel.setBorder(BorderFactory.createTitledBorder("Thêm sản phẩm"));

        productPanel.add(new JLabel("Tên sản phẩm:"));
        productPanel.add(productNameField);
        productPanel.add(new JLabel("Loại:"));
        productPanel.add(productCategoryField);
        productPanel.add(new JLabel("Giá:"));
        productPanel.add(productPriceField);

        JButton addProductBtn = new JButton("Thêm sản phẩm");
        addProductBtn.addActionListener(e -> addProduct());
        productPanel.add(addProductBtn);

        // ========== Bảng sản phẩm ==========
        JScrollPane productScroll = new JScrollPane(productTable);
        productScroll.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm đã thêm"));

        // ========== Nút thêm đơn hàng ==========
        JButton addOrderBtn = new JButton("Thêm đơn hàng");
        addOrderBtn.addActionListener(e -> addOrder());

        // ========== Nút xử lý ==========
        JButton processBtn = new JButton("Xử lý đơn hàng");
        processBtn.addActionListener(e -> processOrders());

        // ========== Kết quả ==========
        resultArea.setEditable(false);
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.setBorder(BorderFactory.createTitledBorder("Kết quả & Log"));

        // ========== Layout ==========
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(orderPanel, BorderLayout.NORTH);
        topPanel.add(productPanel, BorderLayout.CENTER);
        topPanel.add(productScroll, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addOrderBtn);
        buttonPanel.add(processBtn);

        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(resultScroll, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addProduct() {
        try {
            String name = productNameField.getText().trim();
            String category = productCategoryField.getText().trim();
            double price = Double.parseDouble(productPriceField.getText().trim());

            Product product = new Product(name, category, price);
            currentProducts.add(product);

            productTableModel.addRow(new Object[]{name, category, price});

            productNameField.setText("");
            productCategoryField.setText("");
            productPriceField.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập sản phẩm: " + ex.getMessage());
        }
    }

    private void addOrder() {
        try {
            LocalDate orderDate = LocalDate.parse(orderDateField.getText().trim());
            LocalDate deliveryDate = LocalDate.parse(deliveryDateField.getText().trim());
            String status = statusField.getText().trim();
            int tier = Integer.parseInt(tierField.getText().trim());

            Customer customer = new Customer(tier);
            List<Product> products = new ArrayList<>(currentProducts);

            Order order = new Order(orderDate, deliveryDate, status, customer, products);
            orders.add(order);

            resultArea.append("✔ Thêm đơn hàng thành công: " + order + "\n");

            // reset
            currentProducts.clear();
            productTableModel.setRowCount(0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi đơn hàng: " + ex.getMessage());
        }
    }

    private void processOrders() {
        StringBuilder log = new StringBuilder();
        List<Product> results = OrderProcessor.filterAndDiscount(orders, log);

        resultArea.append("\n=== LOG ===\n");
        resultArea.append(log.toString());

        resultArea.append("\n=== Sản phẩm sau giảm giá ===\n");
        results.forEach(p -> resultArea.append(p + "\n"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OrderManagerGUI::new);
    }
}
