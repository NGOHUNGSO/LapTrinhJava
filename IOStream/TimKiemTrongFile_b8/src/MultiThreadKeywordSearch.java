import java.io.*;
import java.util.*;
import java.util.concurrent.*;

class KeywordSearchTask implements Callable<Integer> {
    private String fileName;
    private String keyword;

    public KeywordSearchTask(String fileName, String keyword) {
        this.fileName = fileName;
        this.keyword = keyword;
    }

    @Override
    public Integer call() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                count += countOccurrences(line, keyword);
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + fileName + " - " + e.getMessage());
        }
        return count;
    }

    private int countOccurrences(String line, String keyword) {
        int count = 0;
        int index = 0;
        while ((index = line.indexOf(keyword, index)) != -1) {
            count++;
            index += keyword.length();
        }
        return count;
    }
}

public class MultiThreadKeywordSearch {
    public static void main(String[] args) {
        String[] inputFiles = {"TimKiemTrongFile_b8\\src\\ChienTranhTG1.txt", "TimKiemTrongFile_b8\\src\\ChienTranhTG2.txt"};
        String keyword = "Nga"; // Thay bằng từ khóa cần tìm
        ExecutorService executor = Executors.newFixedThreadPool(inputFiles.length);
        List<Future<Integer>> results = new ArrayList<>();

        for (String file : inputFiles) {
            results.add(executor.submit(new KeywordSearchTask(file, keyword)));
        }

        executor.shutdown();
        int totalOccurrences = 0;

        for (int i = 0; i < inputFiles.length; i++) {
            try {
                int occurrences = results.get(i).get();
                System.out.println("Thứ You tìm là \"" + keyword + "\"Ta thấy nó ở trong " + inputFiles[i] + ": " + occurrences + " lần lặp");
                totalOccurrences += occurrences;
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Mợt khi thấy kết quả từ luồng: " + e.getMessage());
            }
        }

        System.out.println("Ta thấy \"" + keyword + "\" lặp nhiều vl trong tất cả các file cỡ " + totalOccurrences+" lần");
    }
}
