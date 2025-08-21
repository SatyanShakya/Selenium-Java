package SeleniumAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Search {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://dev.visacentre.com.au/");

            // Select Nepal
            WebElement nepalOption = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-value='nepal']"))
            );
            Thread.sleep(1000);
            nepalOption.click();

            // Click Apply
            WebElement applyButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Apply']"))
            );
            applyButton.click();
            Thread.sleep(1000);

            // Search input
            WebElement searchInput = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Where Do You Want to Go?']"))
            );

            String[] searchTerms = {"India", "Nepal", "Pakistan"};
            for (String term : searchTerms) {
                searchInput.clear();
                searchInput.sendKeys(term);
                searchInput.sendKeys(Keys.RETURN);
                Thread.sleep(1000);
            }

            System.out.println("----------------Test Performed Successfully--------------------");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
