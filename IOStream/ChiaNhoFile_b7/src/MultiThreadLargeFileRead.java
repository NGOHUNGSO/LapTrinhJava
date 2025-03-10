import java.io.*;
import java.util.*;

class FileReadTask implements Runnable {
    private String fileName;
    private long start;
    private long end;
    private int index;
    private List<String> results;

    public FileReadTask(String fileName, long start, long end, int index, List<String> results) {
        this.fileName = fileName;
        this.start = start;
        this.end = end;
        this.index = index;
        this.results = results;
    }

    @Override
    public void run() {
        try (RandomAccessFile file = new RandomAccessFile(fileName, "r")) {
            file.seek(start);
            byte[] buffer = new byte[(int) (end - start)];
            file.readFully(buffer);

            synchronized (results) {
                results.set(index, new String(buffer));
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file từ " + start + " đến " + end + " - " + e.getMessage());
        }
    }
}

public class MultiThreadLargeFileRead {
    public static void main(String[] args) {
        String inputFile = "ChiaNhoFile_b7/src/NangVL.pdf";
        String outputFolder = "ChiaNhoFile_b7/src/Chunks/";
        int numThreads = 4;
        long chunkSize = 1024 * 1024; // 1MB mỗi đoạn

        File file = new File(inputFile);
        long fileSize = file.length();
        new File(outputFolder).mkdirs(); // Tạo thư mục chứa file chia nhỏ

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            long start = i * chunkSize;
            long end = Math.min(start + chunkSize, fileSize);
            if (start >= fileSize) break;

            final int chunkIndex = i;
            Thread thread = new Thread(() -> {
                try (RandomAccessFile fileReader = new RandomAccessFile(inputFile, "r");
                     FileOutputStream fos = new FileOutputStream(outputFolder + "chunk_" + chunkIndex + ".dat")) {

                    fileReader.seek(start);
                    byte[] buffer = new byte[(int) (end - start)];
                    fileReader.readFully(buffer);
                    fos.write(buffer);

                    System.out.println("Đã tạo file: " + outputFolder + "chunk_" + chunkIndex + ".dat");
                } catch (IOException e) {
                    System.err.println("Lỗi khi tạo file chia nhỏ: " + e.getMessage());
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Lỗi khi chờ luồng kết thúc: " + e.getMessage());
            }
        }

        System.out.println("Hoàn thành việc chia nhỏ file!");
    }
}
