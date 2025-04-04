import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

public class XmlEditor extends JFrame {
    private JTextField tagNameField;
    private JTextArea contentArea;
    private DefaultListModel<String> elementListModel;
    private JList<String> elementList;
    private XmlManager xmlManager = new XmlManager();

    public XmlEditor() {
        setTitle("XML Editor");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel nhập
        JPanel inputPanel = new JPanel(new BorderLayout());
        tagNameField = new JTextField();
        contentArea = new JTextArea(5, 20);
        inputPanel.add(new JLabel("Tag name:"), BorderLayout.NORTH);
        inputPanel.add(tagNameField, BorderLayout.CENTER);
        inputPanel.add(new JScrollPane(contentArea), BorderLayout.SOUTH);

        // Danh sách thẻ XML
        elementListModel = new DefaultListModel<>();
        elementList = new JList<>(elementListModel);
        JScrollPane listScrollPane = new JScrollPane(elementList);

        // Nút
        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Create");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton mergeBtn = new JButton("Merge & Save");
        JButton readBtn = new JButton("Read XML");

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(mergeBtn);
        buttonPanel.add(readBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        elementList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedTag = elementList.getSelectedValue();
                if (selectedTag != null) {
                    tagNameField.setText(selectedTag);
                    String content = xmlManager.getContentByTag(selectedTag);
                    contentArea.setText(content);
                }
            }
        });

        // Sự kiện nút
        addBtn.addActionListener(e -> {
            String tag = tagNameField.getText();
            String content = contentArea.getText();
            xmlManager.createElement(tag, content);
            elementListModel.addElement(tag);
            tagNameField.setText("");
            contentArea.setText("");
        });

        updateBtn.addActionListener(e -> {
            String selectedTag = elementList.getSelectedValue();
            if (selectedTag != null) {
                String newContent = contentArea.getText();
                xmlManager.updateElement(selectedTag, newContent);
                JOptionPane.showMessageDialog(this, "Đã cập nhật nội dung cho thẻ: " + selectedTag);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một thẻ để cập nhật");
            }
        });

        deleteBtn.addActionListener(e -> {
            int index = elementList.getSelectedIndex();
            if (index >= 0) {
                String tag = elementListModel.get(index);
                xmlManager.deleteElement(tag);
                elementListModel.remove(index);
            }
        });

        mergeBtn.addActionListener(e -> {
            XmlMerger.mergeAndSave(xmlManager.getElements(), "KoBeOi.xml");
            JOptionPane.showMessageDialog(this, "Đã lưu KoBeOi.xml");
        });

        readBtn.addActionListener(e -> {
            List<String> content = XmlMerger.readMergedXml("KoBeOi.xml");
            JOptionPane.showMessageDialog(this, String.join("\n", content));
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(XmlEditor::new);
    }
}
