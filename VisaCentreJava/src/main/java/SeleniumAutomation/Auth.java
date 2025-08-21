package SeleniumAutomation;

import org.openqa.selenium.By;            //Core Selenium classes like WebDriver, WebElement, By, Keys.
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;             //Chrome-specific classes like ChromeDriver.
import org.openqa.selenium.support.ui.ExpectedConditions;   //Utilities for waiting (WebDriverWait, ExpectedConditions) and other support classes.
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import java.time.Duration;

public class Auth {

    public static void main(String[] args) {
        // -------------------- Setup ChromeDriver --------------------
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // -------------------- Faker for random names --------------------
        Faker faker = new Faker();

        String email = "satyanshakya@gmail.com";
        String otp = "123456";
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String password = "Test@123";
        String confirmPassword = "mTest@123";
        int sendOtpAfter = 2; // seconds

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // -------------------- Open URL --------------------
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
            emailField.sendKeys(Keys.RETURN);   //enter
            Thread.sleep(2000);

            // -------------------- Register OTP --------------------
            if (driver.getCurrentUrl().equals("https://dev.visacentre.com.au/auth/register-verify-otp")) {
                WebElement otpNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@data-input-otp='true']")));
                otpNumber.sendKeys(otp);
                Thread.sleep(1000);
                otpNumber.sendKeys(Keys.RETURN);
                Thread.sleep(2000);
            }

            // -------------------- Register Set Password --------------------
            if (driver.getCurrentUrl().equals("https://dev.visacentre.com.au/auth/setup-password")) {
                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
                WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastName")));
                WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
                WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("confirmPassword")));

                firstNameField.sendKeys(firstName);
                Thread.sleep(1000);
                lastNameField.sendKeys(lastName);
                Thread.sleep(1000);
                passwordField.sendKeys(password);
                Thread.sleep(1000);
                confirmPasswordField.sendKeys(confirmPassword);
                Thread.sleep(1000);

                WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Save']")));
                saveButton.click();
                Thread.sleep(5000);
            }

            // -------------------- Login Password --------------------
            if (driver.getCurrentUrl().equals("https://dev.visacentre.com.au/auth/check-password")) {
                WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@placeholder='.................']")));
                passwordField.click();
                passwordField.sendKeys(password);
                Thread.sleep(1000);
                passwordField.sendKeys(Keys.RETURN);
                Thread.sleep(2000);
            }

            // -------------------- Login OTP --------------------
            if (driver.getCurrentUrl().equals("https://dev.visacentre.com.au/auth/login-verify-otp")) {
                WebElement loginOtp = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@data-input-otp='true']")));
                loginOtp.sendKeys(otp);
                Thread.sleep(sendOtpAfter * 1000);
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
