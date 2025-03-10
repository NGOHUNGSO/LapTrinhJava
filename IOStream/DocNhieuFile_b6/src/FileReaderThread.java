import java.io.*;
import java.util.*;

class FileReaderThread extends Thread {
    private String fileName;
    private List<String> contentList;

    public FileReaderThread(String fileName, List<String> contentList) {
        this.fileName = fileName;
        this.contentList = contentList;
    }

    @Override
    public void run() {
        synchronized (contentList) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    contentList.add(line);
                }
            } catch (IOException e) {
                System.err.println("Tới lúc tìm Bug rồi: " + fileName + " - " + e.getMessage());
            }
        }
    }
}
