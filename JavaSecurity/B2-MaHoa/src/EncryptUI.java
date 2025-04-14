import javax.swing.*;
import java.awt.event.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

interface Encryptable {
    String encrypt(String plainText);
    String decrypt(String cipherText);
}

// AES implementation
class AESEncryption implements Encryptable {
    private static final String SECRET_KEY = "1234567890123456"; // 16 byte key

    public String encrypt(String plainText) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            return "Error";
        }
    }

    public String decrypt(String cipherText) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(cipherText);
            return new String(cipher.doFinal(decoded));
        } catch (Exception e) {
            return "Error";
        }
    }
}

// RSA implementation
class RSAEncryption implements Encryptable {
    private KeyPair keyPair;

    public RSAEncryption() {
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(2048);
            keyPair = gen.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            return "Error";
        }
    }

    public String decrypt(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            byte[] decoded = Base64.getDecoder().decode(cipherText);
            return new String(cipher.doFinal(decoded));
        } catch (Exception e) {
            return "Error";
        }
    }
}

public class EncryptUI extends JFrame {
    private JTextField inputField;
    private JComboBox<String> algoBox;
    private JTextArea resultArea;
    private Encryptable encryptor;

    public EncryptUI() {
        setTitle("Mã hóa xâu ký tự");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("Nhập xâu:");
        label.setBounds(20, 20, 100, 25);
        add(label);

        inputField = new JTextField();
        inputField.setBounds(100, 20, 250, 25);
        add(inputField);

        algoBox = new JComboBox<>(new String[]{"AES", "RSA"});
        algoBox.setBounds(100, 60, 100, 25);
        add(algoBox);

        JButton encryptBtn = new JButton("Encrypt");
        encryptBtn.setBounds(220, 60, 100, 25);
        add(encryptBtn);

        resultArea = new JTextArea();
        resultArea.setBounds(20, 100, 350, 120);
        add(resultArea);

        encryptBtn.addActionListener(e -> {
            String text = inputField.getText();
            String algo = algoBox.getSelectedItem().toString();

            if (algo.equals("AES"))
                encryptor = new AESEncryption();
            else
                encryptor = new RSAEncryption();

            String encrypted = encryptor.encrypt(text);
            String decrypted = encryptor.decrypt(encrypted);

            resultArea.setText("Mã hóa: " + encrypted + "\nGiải mã: " + decrypted);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EncryptUI().setVisible(true));
    }
}
