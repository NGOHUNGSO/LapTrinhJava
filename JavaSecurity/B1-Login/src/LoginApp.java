import javax.swing.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginApp extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, logoutButton;

    public LoginApp() {
        setTitle("Login");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 10, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 10, 160, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(10, 40, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 40, 160, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(30, 80, 100, 25);
        add(loginButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 80, 100, 25);
        add(logoutButton);
        logoutButton.setEnabled(false);

        loginButton.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            String hashed = hashPassword(pass);
            System.out.println("Login attempt: " + user + " - Hashed: " + hashed);
            JOptionPane.showMessageDialog(this, "Logged in with hashed password!");
            loginButton.setEnabled(false);
            logoutButton.setEnabled(true);
        });

        logoutButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            loginButton.setEnabled(true);
            logoutButton.setEnabled(false);
        });
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // hoặc "MD5"
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginApp().setVisible(true));
    }
}
