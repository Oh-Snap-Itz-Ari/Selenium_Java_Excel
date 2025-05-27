package Test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.data.excel.DataExcel;
import resources.navigation.NavigatorChrome;

public class ExcelFuncionality {

    public static WebDriver driver; // Al hacerlo de esta manera el valor queda guardado en memoria (No toca instanciarlo cada que se necesite)
    DataExcel dataExcel;

    @BeforeMethod // Indica que se ejecutará previo a las pruebas
    public void beforeTest(){
        NavigatorChrome navigatorChrome = new NavigatorChrome(driver); // Se crea una instancia para que el metodo pueda acceder a la clase navigationChrome
        driver = navigatorChrome.openGoogleNavigator("https://demoqa.com/elements");
    }

    public void ReadingData(){
        // En este caso no es necesario utilizar la función de ReadingData ya que no se envia información
    }

    public void BasicInstances(){
        ReadingData(); // Se llama Reading Data para que busque la información
        dataExcel = new DataExcel();
    }

    @Test // Indica el test que se va a realizar
    public void modifyElements(){
        BasicInstances(); // Se llaman las Instancias Básicas para Buscar Inicializar y buscar la data
        dataExcel.excelConnection();
    }

    @AfterMethod // Indica que se ejecutará después de las pruebas
    public void afterTest(){
        NavigatorChrome navigatorChrome = new NavigatorChrome(driver); // Se crea una instancia para que el metodo pueda acceder a la clase navigationChrome
        driver = navigatorChrome.tearDown();
    }

}
