package Test;

import entities.facebook.ValidationSignIn;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.data.excel.DataExcel;
import resources.navigation.NavigatorChrome;
import scenaries.facebook.RegisterFacebook;

import javax.swing.table.DefaultTableModel;

public class ExcelFuncionality {

    public static WebDriver driver; // Al hacerlo de esta manera el valor queda guardado en memoria (No toca instanciarlo cada que se necesite)
    private DefaultTableModel dataTable;
    DataExcel dataExcel;
    ValidationSignIn validationSignIn;
    RegisterFacebook registerFacebook;

    int numberFile = 0;

    @BeforeMethod // Indica que se ejecutará previo a las pruebas
    public void beforeTest(){
        NavigatorChrome navigatorChrome = new NavigatorChrome(driver); // Se crea una instancia para que el metodo pueda acceder a la clase navigationChrome
        driver = navigatorChrome.openGoogleNavigator("https://es-la.facebook.com/r.php?entry_point=login");
    }

    public void ReadingData(){

        dataExcel.excelConnection(false);
        dataTable = dataExcel.tableExcel(); // Obtiene la información del Excel// Es importante retornarlo como un String

        validationSignIn = new ValidationSignIn(); // Crea una instancia para utilizarla después
        validationSignIn.firstName = dataExcel.obtainColumnValue(numberFile, "FirstName").toString();
        validationSignIn.lastName = dataExcel.obtainColumnValue(numberFile, "LastName").toString();
        validationSignIn.dayBirthday = dataExcel.obtainColumnValue(numberFile, "DayBirthday").toString();
        validationSignIn.monthBirthday = dataExcel.obtainColumnValue(numberFile, "MonthBirthday").toString();
        validationSignIn.yearBirthday = dataExcel.obtainColumnValue(numberFile, "YearBirthday").toString();
        validationSignIn.gender = dataExcel.obtainColumnValue(numberFile, "Gender").toString();
        validationSignIn.email = dataExcel.obtainColumnValue(numberFile, "Email").toString();
        validationSignIn.password = dataExcel.obtainColumnValue(numberFile, "Password").toString();

        dataExcel.excelCloseConnection();
    }

    public void BasicInstances(){
        registerFacebook = new RegisterFacebook (driver);
        dataExcel = new DataExcel();
        ReadingData(); // Se llama Reading Data para que busque la información
    }

    @Test // Indica el test que se va a realizar
    public void tryFacebookSignIn(){
        BasicInstances(); // Se llaman las Instancias Básicas para Buscar Inicializar y buscar la data
        registerFacebook.SignInFacebookConfirm(validationSignIn); // Para no tener que poner toda la data solicitada se hace de esta manera// Se llaman las Instancias Básicas para Buscar Inicializar y buscar la data
    }

    @AfterMethod // Indica que se ejecutará después de las pruebas
    public void afterTest(){
        NavigatorChrome navigatorChrome = new NavigatorChrome(driver); // Se crea una instancia para que el metodo pueda acceder a la clase navigationChrome
        driver = navigatorChrome.tearDown();
    }

}
