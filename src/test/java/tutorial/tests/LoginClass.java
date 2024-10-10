package tutorial.tests;

import com.tutorial.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginClass extends BasePage {
   By accountBtn = By.xpath("//h2[text()='My Account']");
   By emailBtn =By.cssSelector("#input-email");
   By loginBtn = By.linkText("Login");
   By passwordBtn = By.cssSelector("#input-password");
   By accountEnterBtn= By.cssSelector(".fa-user");
   By submitBtn = By.xpath("//input[@type ='submit']");

    @Test(priority = 2)
    public void loginSuccess() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
        driver.findElement(accountEnterBtn).click();
        WebElement login =waitUntilClickable(loginBtn);
        login.clear();
        login.click();
        WebElement email=waitUntilClickable(emailBtn);
        email.clear();
        email.sendKeys("vv@mail.ru");
        driver.findElement(passwordBtn).sendKeys("megadelta");
        driver.findElement(submitBtn).click();
        WebElement account = waitUntilVisible(accountBtn);
        Assert.assertTrue((account.isDisplayed()));
        driver.quit();

    }

@Test(priority = 1,invocationCount = 3)
  public void loginNegative(){
        WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
      driver.manage().window().maximize();
      wait = new WebDriverWait(driver, Duration.ofSeconds(10));

      driver.get(URL);
      driver.findElement(By.cssSelector(".fa-user")).click();
      WebElement login = wait.until(ExpectedConditions
              .elementToBeClickable(By.linkText("Login")));
      login.click();
      WebElement email = wait.until(ExpectedConditions
              .elementToBeClickable(By.cssSelector("#input-email")));
      email.clear();
      String invalidLogin = generateLogin();

      email.sendKeys(invalidLogin + "@gmail.com");
    System.out.println(invalidLogin +"@gmail.com");
      driver.findElement(By.cssSelector("#input-password")).sendKeys("12345");
      driver.findElement(By.xpath("//input[@type ='submit']")).click();
      WebElement alert = wait.until(ExpectedConditions
              .visibilityOfElementLocated(By.cssSelector(".alert")));
      Assert.assertTrue((alert.isDisplayed()));
      driver.quit();

  }

}
