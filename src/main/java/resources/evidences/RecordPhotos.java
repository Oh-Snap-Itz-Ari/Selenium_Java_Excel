package resources.evidences;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RecordPhotos {

    WebDriver driver;

    public RecordPhotos (WebDriver _driver){
        driver = _driver;
    }

    public void HighlightElements(String xpath, String color){

        WebElement element = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        if (color == "r"){
            js.executeScript("arguments[0].style.border = '3px solid red'", element);
        }else if (color == "b"){
            js.executeScript("arguments[0].style.border = '3px solid black'", element);
        }

    }

}
