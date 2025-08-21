package SeleniumAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Application {
    public static void main(String[] args) {
        // ------------------ Setup Driver ------------------
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(375, 812)); // iPhone X size

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ------------------ Inputs ------------------
        String email = "John@gmail.com";
        String otp = "123456";
        String password = "Test@123";

        try {
            // ------------------ Open main page ------------------
            driver.get("https://dev.visacentre.com.au/");

            // Select country
            WebElement country = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@data-value='nepal']")));
            Thread.sleep(1000);
            country.click();

            // Click Apply button
            WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[text()='Apply']")));
            applyButton.click();
            Thread.sleep(1000);


            // Go directly to visa page
            driver.get("https://dev.visacentre.com.au/visa/nepal-to-united-kingdom");

            // Click Apply for Visa
            WebDriverWait waite = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement applyButtons = waite.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[text()='Apply for Visa']")));
            applyButtons.click();
            Thread.sleep(1000);

            // Click Yes

            WebDriverWait waitee = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement yesBtn = waitee.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Yes']"))
            );
            yesBtn.click();

            Thread.sleep(1000);

            // Search input
            WebElement applySearch = driver.findElement(By.xpath("//input[@placeholder='Search']"));
            applySearch.sendKeys("nepal");
            applySearch.sendKeys(Keys.RETURN);
            Thread.sleep(1000);

            // Submit search
            WebElement applySearchSubmit = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='submit']")));
            applySearchSubmit.click();
            Thread.sleep(1000);

            // ------------------ Enter Email ------------------
            WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='example@gmail.com']"));
            emailField.sendKeys(email);
            Thread.sleep(1000);
            emailField.sendKeys(Keys.RETURN);
            Thread.sleep(2000);

            // ------------------ Login Password ------------------
            if (driver.getCurrentUrl().equals("https://dev.visacentre.com.au/auth/check-password")) {
                WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@placeholder='.................']")));

                passwordField.click();
                passwordField.sendKeys(password);
                Thread.sleep(1000);
                passwordField.sendKeys(Keys.RETURN);
                Thread.sleep(2000);
            }

            // ------------------ Login OTP ------------------
            if (driver.getCurrentUrl().equals("https://dev.visacentre.com.au/auth/login-verify-otp")) {
                WebElement loginOtp = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@data-input-otp='true']")));

                loginOtp.sendKeys(otp);
                loginOtp.sendKeys(Keys.RETURN);
                Thread.sleep(5000);
            }

            System.out.println("----------------Test Performed Successfully--------------------");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
