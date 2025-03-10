import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        String sourceFile = "DocGhiTuFile_b1\\src\\InputFile.txt";
        String destinationFile = "DocGhiTuFile_b1\\src\\OutPutFile.txt";

        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("File cóp xong rồi!!!!!!!");
        } catch (IOException e) {
            System.err.println("Lỗi mịa rồi: " + e.getMessage());
        }
    }
}
