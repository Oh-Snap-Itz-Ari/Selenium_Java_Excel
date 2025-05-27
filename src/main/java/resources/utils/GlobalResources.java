package resources.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import resources.evidences.RecordPhotos;

public class GlobalResources {

    WebDriver driver;

    public GlobalResources(WebDriver _driver) {
        driver = _driver;
    }

    public void Click (String xpath){

        RecordPhotos recordPhotos = new RecordPhotos(driver);

        try{
            recordPhotos.HighlightElements(xpath, "r"); // Resalta con un borde rojo antes de dar click
            driver.findElement(By.xpath(xpath)).click();
            recordPhotos.HighlightElements(xpath, "b"); // Resalta con un borde negro después de dar click
       }
       catch (Exception fail){
            recordPhotos.HighlightElements(xpath, "r"); // Resalta con un borde rojo antes de dar click
            WebElement element = driver.findElement(By.xpath(xpath));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click()", element);
            recordPhotos.HighlightElements(xpath, "b"); // Resalta con un borde negro después de dar click
       }
    }

    public void Write (String xpath, String text){
        driver.findElement(By.xpath(xpath)).clear();
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    public void SelectListValue (String xpath, String value){
        Select list = new Select(driver.findElement(By.xpath(xpath)));
        list.selectByValue(value);
    }

    public void SelectListText (String xpath, String text){
        Select list = new Select(driver.findElement(By.xpath(xpath)));
        list.selectByVisibleText(text);
    }

    public void JSModifyAttribute(String xpath, String attribute, String variable){
        WebElement element = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('"+attribute+"','"+variable+"')",element); // Permite modificar un atributo del elemento Web
    }

    public void JSRemoveAttribute(String xpath, String attribute){
        WebElement element = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('"+attribute+"')",element); // Permite eliminar un atributo del elemento Web
    }
}
