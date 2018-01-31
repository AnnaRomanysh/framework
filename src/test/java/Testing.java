import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;



public class Testing {


    public static void main(String [] args){

    System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
    WebDriver driver = new ChromeDriver();
        driver.get("https://ia.ca/individuals");

        WebElement element = driver.findElement(By.xpath("//div[@class='navbar-sub-main']//ul[@class='nav navbar-nav sub']//li[contains(@class, 'dropdown Pret')]"));
    element.click();
        WebElement element2 = driver.findElement(By.xpath("//div[@class='navbar-sub-main']//ul[@class='nav navbar-nav sub']//li[contains(@class, 'dropdown Pret')]//a"));
        Assert.assertTrue(element2.isDisplayed());
    }






}
