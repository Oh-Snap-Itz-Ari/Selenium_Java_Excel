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

    int numberFiles = 0;

    @BeforeMethod // Indica que se ejecutará previo a las pruebas
    public void beforeTest(){
        BasicInstances();
        numberFiles = dataExcel.obtainFiles(); // Retorna el valor de las filas que tiene el excel y actualiza el excel
    }

    public void BasicInstances(){
        registerFacebook = new RegisterFacebook (driver);
        dataExcel = new DataExcel();
    }

    public void openNavigator(){
        NavigatorChrome navigatorChrome = new NavigatorChrome(driver); // Se crea una instancia para que el metodo pueda acceder a la clase navigationChrome
        driver = navigatorChrome.openGoogleNavigator("https://es-la.facebook.com/r.php?entry_point=login");
    }

    public void ReadingData(int fileNumber){

        dataExcel.excelConnection(false);
        dataTable = dataExcel.tableExcel(); // Obtiene la información del Excel// Es importante retornarlo como un String

        validationSignIn = new ValidationSignIn(); // Crea una instancia para utilizarla después
        validationSignIn.firstName = dataExcel.obtainColumnValue(fileNumber, "FirstName").toString();
        validationSignIn.lastName = dataExcel.obtainColumnValue(fileNumber, "LastName").toString();
        validationSignIn.dayBirthday = dataExcel.obtainColumnValue(fileNumber, "DayBirthday").toString();
        validationSignIn.monthBirthday = dataExcel.obtainColumnValue(fileNumber, "MonthBirthday").toString();
        validationSignIn.yearBirthday = dataExcel.obtainColumnValue(fileNumber, "YearBirthday").toString();
        validationSignIn.gender = dataExcel.obtainColumnValue(fileNumber, "Gender").toString();
        validationSignIn.email = dataExcel.obtainColumnValue(fileNumber, "Email").toString();
        validationSignIn.password = dataExcel.obtainColumnValue(fileNumber, "Password").toString();

        dataExcel.excelCloseConnection();
    }

    public void closeNavigator() {
        driver.close();
        driver.quit();
    }

    @Test // Indica el test que se va a realizar
    public void tryFacebookSignIn(){
        BasicInstances(); // Se llaman las Instancias Básicas para Buscar Inicializar y buscar la data

        for (int fileNumber = 0; fileNumber < numberFiles; fileNumber++){
            openNavigator();
            BasicInstances();
            ReadingData(fileNumber);
            registerFacebook.SignInFacebookConfirm(validationSignIn); // Para no tener que poner toda la data solicitada se hace de esta manera
            closeNavigator();
        }


    }

    // @AfterMethod // Indica que se ejecutará después de las pruebas
    public void afterTest(){
        NavigatorChrome navigatorChrome = new NavigatorChrome(driver); // Se crea una instancia para que el metodo pueda acceder a la clase navigationChrome
        driver = navigatorChrome.tearDown();
    }

}
