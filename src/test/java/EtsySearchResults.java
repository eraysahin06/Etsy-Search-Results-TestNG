import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class EtsySearchResults {

    WebDriver driver;

    @BeforeMethod
    public void navigateToTheDollsPage(){
        System.setProperty("webdriver.chrome.driver", "/Library/Selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.etsy.com/");

        WebElement toysTab = driver.findElement(By.partialLinkText("Toys & Entertainment"));
        Actions actions = new Actions(driver);
        actions.moveToElement(toysTab).build();
        WebElement dollsText = driver.findElement(By.partialLinkText("Dolls & Action Figures"));
        dollsText.click();
        WebElement dollsButton = driver.findElement(By.xpath("//p[text()='Dolls']"));
        dollsButton.click();
        System.out.println("Your URL contains \"Dolls\": " + driver.getCurrentUrl().contains("Dolls"));
    }

    @Test
    public void validateTextDisplayed(){
        WebElement searchBox = driver.findElement(By.id("global-enhancements-search-query"));
        String dollText = searchBox.getAttribute("value");
        Assert.assertTrue(dollText.contains("Dolls"));
    }

    @AfterMethod
    public void quitBrowser(){
        driver.quit();
    }
}
