import java.io.*;
import java.util.*;

public class MultiThreadFileRead {
    public static void main(String[] args) {
        String[] inputFiles = {"DocNhieuFile_b6\\src\\SiuNhanGao.txt", "DocNhieuFile_b6\\src\\SAnhBay.txt"};
        String outputFile = "DocNhieuFile_b6\\src\\a.txt";
        List<String> contentList = Collections.synchronizedList(new ArrayList<>());

        List<Thread> threads = new ArrayList<>();
        for (String file : inputFiles) {
            Thread thread = new FileReaderThread(file, contentList);
            threads.add(thread);
            thread.start();
        }

        // Chờ tất cả các luồng kết thúc
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Dỗi khi đợi luồng kết thúc(Cọc vl): " + e.getMessage());
            }
        }

        // Ghi dữ liệu vào file tổng hợp
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String line : contentList) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Đã hợp thể nội dung vào file " + outputFile);
        } catch (IOException e) {
            System.err.println("Méo hợp thể đc file tổng hợp: " + e.getMessage());
        }
    }
}