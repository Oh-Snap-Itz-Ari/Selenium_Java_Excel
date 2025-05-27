package resources.navigation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class NavigatorChrome {

    WebDriver driver;

    public NavigatorChrome(WebDriver _driver){
        driver = _driver; // Lo que se le envia la clase nunca lo olvidará, en este caso el driver
    }

    public WebDriver openGoogleNavigator(String url) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public WebDriver tearDown(){
        if (driver != null){
            driver.close();
        }
        return driver;
    }

}
