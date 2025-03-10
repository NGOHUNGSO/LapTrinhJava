import java.io.*;

public class IntegerListFile {
    public static void main(String[] args) {
        String fileName = "GhiSoNguyenVaoFile_b4\\src\\SoDe.txt";
        int[] numbers = {10, 20, 30, 40, 50};

        // Ghi danh sách số nguyên vào file
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            for (int num : numbers) {
                dos.writeInt(num);
            }
            System.out.println("Đã ghi danh sách số đề vào bí kíp " + fileName);
        } catch (IOException e) {
            System.err.println("Thấy mẹ rồi!!!! " + e.getMessage());
        }

        // Đọc lại danh sách số nguyên từ file
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            System.out.println("Danh sách số đề đọc từ sách cấm:");
            while (dis.available() > 0) {
                System.out.println(dis.readInt());
            }
        } catch (IOException e) {
            System.err.println("Hết cứu: " + e.getMessage());
        }
    }
}