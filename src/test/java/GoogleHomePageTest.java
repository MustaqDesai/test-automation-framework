import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GoogleHomePageTest{
    WebDriver browserDriver;

    @BeforeTest
    @Parameters("browserName")
    public void setUp(String browser) throws MalformedURLException{
        //System.out.println("");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browser.equalsIgnoreCase("chrome")){
            capabilities.setBrowserName("chrome");
        }

        if (browser.equalsIgnoreCase("edge")){
            capabilities.setBrowserName("MicrosoftEdge");
        }

        if (browser.equalsIgnoreCase("firefox")){
            capabilities.setBrowserName("firefox");
        }

        browserDriver = new RemoteWebDriver(new URL("http://172.18.0.2:4444"), capabilities);    
    }

    @Test
    public void confirmPageTitle() throws InterruptedException{
        browserDriver.get("https://www.google.com");
        Thread.sleep(2000);
        String pageTitle = browserDriver.getTitle();
        Assert.assertEquals(pageTitle, "Google", "Found the page title.");
        browserDriver.quit();


    }
        
}
