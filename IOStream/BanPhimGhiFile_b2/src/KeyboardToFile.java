import java.io.*;

public class KeyboardToFile {
    public static void main(String[] args) {
        // Định nghĩa file để lưu dữ liệu
        String fileName = "BanPhimGhiFile_b2\\src\\OutPut.txt";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            System.out.println("Nhập dữ liệu (Nhập 'exit' rồi enter đê):");
            String line;

            while (true) {
                line = reader.readLine(); // Đọc dữ liệu từ bàn phím
                if ("exit".equalsIgnoreCase(line)) {
                    break;
                }
                writer.write(line); // Ghi vào file
                writer.newLine(); // Xuống dòng
            }

            System.out.println("Dữ liệu ghi rồi đấy, mở file và đọc đi " + fileName);
        } catch (IOException e) {
            System.err.println("Lỗi thấy mịa rồi: " + e.getMessage());
        }
    }
}
