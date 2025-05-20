import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestDockerSelenium {
    public static ChromeOptions browserOptions;
    public static ThreadLocal<RemoteWebDriver> remoteDriver = new ThreadLocal<>();
    public static String hubURL = "http://172.18.0.2:4444/";

    @BeforeTest
    public static void Setup() throws MalformedURLException{
        browserOptions = new ChromeOptions();
        remoteDriver.set(new RemoteWebDriver(new URL(hubURL), browserOptions));
    }

    @Test
    void testSteps() throws InterruptedException{
        WebDriver browserDriver = getDriver();
        //browserDriver.navigate().to("https://www.google.com");
        //Thread.sleep(2000);
        browserDriver.get("https://www.google.com");
        Thread.sleep(2000);

        List<WebElement> buttons = browserDriver.findElements(By.name("btnK"));
  
        //System.out.println(buttons.get(0).getAttribute("value"));
        //System.out.println(buttons.get(0).getDomAttribute("value"));
        System.out.println(buttons.get(0).getDomProperty("value"));
        String buttonName = buttons.get(0).getDomProperty("value");
       
        Assert.assertEquals(buttonName, "Google Search", "Found the Google Search button.");

        browserDriver.findElement(By.id("gbqfbb")).click(); //Feeling lucky!
        Thread.sleep(2000);

        browserDriver.close();
               
    }

    private WebDriver getDriver() {
        return remoteDriver.get();
    }

    @AfterTest
    public static void TearDown(){
        //
    }
    

}
