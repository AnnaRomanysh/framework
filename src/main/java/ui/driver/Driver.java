package ui.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Driver {
 WebDriver driver;


 public WebDriver getDriver (){
//     System.setProperty("")
     driver = new ChromeDriver();
     return driver;
 }


}
