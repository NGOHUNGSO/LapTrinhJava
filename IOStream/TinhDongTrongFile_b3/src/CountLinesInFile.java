import java.io.*;

public class CountLinesInFile {
    public static void main(String[] args) {
        // Định nghĩa tên file cần đọc
        String fileName = "TinhDongTrongFile_b3\\src\\APT.txt";
        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) {
                lineCount++; // Tăng biến đếm khi đọc từng dòng
            }

            System.out.println("File " + fileName + " Dài Hẳn " + lineCount+" dòng");
        } catch (IOException e) {
            System.err.println("Fix đi cưng: " + e.getMessage());
        }
    }
}