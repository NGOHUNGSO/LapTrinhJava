import java.io.File;

public class ListFilesInDirectory {
    public static void main(String[] args) {
        // Thư mục cần liệt kê
        String directoryPath = "D:\\Downloads"; // Thay đổi đường dẫn nếu cần

        File directory = new File(directoryPath);

        // Kiểm tra xem đường dẫn có phải là thư mục không
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null && files.length > 0) {
                System.out.println("Đếm file đi nè: " + directoryPath);
                for (File file : files) {
                    System.out.println(file.getName());
                }
            } else {
                System.out.println("Thư mục trống như ví của bạn cuối tháng.");
            }
        } else {
            System.err.println("Nhầm đường rồi, gõ lại đi.");
        }
    }
}