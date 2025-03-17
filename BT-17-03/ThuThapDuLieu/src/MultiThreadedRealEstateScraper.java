import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class RealEstateCrawler implements Runnable {
    private final String url;
    private final List<String[]> dataList;
    private final AtomicInteger count;

    public RealEstateCrawler(String url, List<String[]> dataList, AtomicInteger count) {
        this.url = url;
        this.dataList = dataList;
        this.count = count;
    }

    @Override
    public void run() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get(url);
            List<WebElement> listings = driver.findElements(By.className("search-productItem"));

            for (WebElement listing : listings) {
                String title = listing.findElement(By.className("js__card-title-full")).getText();
                String price = listing.findElement(By.className("js__price")).getText();
                String address = listing.findElement(By.className("re__card-location")).getText();
                String area = listing.findElement(By.className("js__area")).getText();
                String description = listing.findElement(By.className("js__description")).getText();

                synchronized (dataList) {
                    dataList.add(new String[]{title, price, address, area, description});
                }
                count.incrementAndGet();
            }
        } catch (Exception e) {
            System.err.println("Error crawling: " + url + " - " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

public class MultiThreadedRealEstateScraper {
    private static final String OUTPUT_FILE = "real_estate_data.csv";

    public static void main(String[] args) {
        List<String[]> dataList = new ArrayList<>();
        AtomicInteger count = new AtomicInteger(0);

        String[] urls = {
                "https://batdongsan.com.vn/nha-dat-ban"
        };

        ExecutorService executor = Executors.newFixedThreadPool(urls.length);

        for (String url : urls) {
            executor.execute(new RealEstateCrawler(url, dataList, count));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {}

        saveDataToCSV(dataList);
        System.out.println("Total properties scraped: " + count.get());
    }

    private static void saveDataToCSV(List<String[]> dataList) {
        try (FileWriter writer = new FileWriter(OUTPUT_FILE)) {
            writer.append("Title,Price,Address,Area,Description\n");
            synchronized (dataList) {
                for (String[] data : dataList) {
                    writer.append(String.join(",", data)).append("\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
