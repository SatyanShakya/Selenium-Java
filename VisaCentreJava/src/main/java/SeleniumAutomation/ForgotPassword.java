package SeleniumAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import java.time.Duration;

public class ForgotPassword {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(375, 812));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Faker faker = new Faker();

        // -------------------- Inputs --------------------
        String email = "satyantest@gmail.com";
        String otp = "123456";
        String newPassword = "Test@1234";
        String confirmPassword = "Test@123";
        int sendOtpAfter = 2; // seconds

        try {
            driver.get("https://dev.visacentre.com.au/auth");

            // -------------------- Select Country --------------------
            WebElement country = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@data-value='nepal']")));
            Thread.sleep(1000);
            country.click();

            WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[text()='Apply']")));
            applyButton.click();
            Thread.sleep(1000);

            // -------------------- Enter Email --------------------
            WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='example@gmail.com']"));
            emailField.sendKeys(email);
            Thread.sleep(1000);
            emailField.sendKeys(Keys.RETURN);
            Thread.sleep(1000);

            // -------------------- Forgot Password Link --------------------
            if (driver.getCurrentUrl().equals("https://dev.visacentre.com.au/auth/check-password")) {
                WebElement forgotPasswordLink = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[text()='Forgot Password']")));
                forgotPasswordLink.click();
                Thread.sleep(2000);
            }

            // -------------------- Enter Email for Forgot Password --------------------
            if (driver.getCurrentUrl().equals("https://dev.visacentre.com.au/auth/forgot-password")) {
                WebElement forgotEmailField = driver.findElement(By.xpath("//input[@placeholder='example@gmail.com']"));
                forgotEmailField.sendKeys(email);
                Thread.sleep(1000);
                forgotEmailField.sendKeys(Keys.RETURN);
                Thread.sleep(2000);
            }

            // -------------------- Forgot Password OTP --------------------
            if (driver.getCurrentUrl().equals("https://dev.visacentre.com.au/auth/forgot-password/verify")) {
                WebElement loginOtp = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@data-input-otp='true']")));
                loginOtp.sendKeys(otp);
                Thread.sleep(sendOtpAfter * 1000);
                loginOtp.sendKeys(Keys.RETURN);
                Thread.sleep(2000);
            }

            // -------------------- Reset Password --------------------
            if (driver.getCurrentUrl().equals("https://dev.visacentre.com.au/auth/reset-password")) {
                WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
                WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("confirmPassword")));

                passwordField.sendKeys(newPassword);
                Thread.sleep(1000);
                confirmPasswordField.sendKeys(confirmPassword);
                Thread.sleep(1000);

                WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Save']")));
                saveButton.click();
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

