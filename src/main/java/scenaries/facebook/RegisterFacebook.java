package scenaries.facebook;

import entities.facebook.ValidationSignIn;
import org.openqa.selenium.WebDriver;
import resources.utils.GlobalResources;

public class RegisterFacebook {

    public static WebDriver driver;

    public RegisterFacebook(WebDriver _driver) {
        driver = _driver;
    }

    public void GenderSelection(String gender){

        GlobalResources globalResources = new GlobalResources(driver);

        String inputFemale = "//*[@id='sex' and @value='1']";
        String inputMale = "//*[@id='sex' and @value='2']";
        String inputOther = "//*[@id='sex' and @value='-1']";

        if (gender == "Male"){
            globalResources.Click(inputMale);
        }
        else if (gender == "Female"){
            globalResources.Click(inputFemale);
        }
        else {
            globalResources.Click(inputOther); // Se podría en cualquier término que no corresponda a "Male" o "Female"
        }
    }

    public void GenderSelectionSwitchCase(String gender){

        GlobalResources globalResources = new GlobalResources(driver);

        String inputFemale = "//*[@id='sex' and @value='1']";
        String inputMale = "//*[@id='sex' and @value='2']";
        String inputOther = "//*[@id='sex' and @value='-1']";

        switch (gender) {
            case "Male":
                globalResources.Click(inputMale);

            case "Female":
                globalResources.Click(inputFemale);

            case "Other":
                globalResources.Click(inputOther);
        }
    }

    public void SignInFacebookConfirm(ValidationSignIn validationSignIn) { //Al hacer lo de public static WebDriver ya no sería necesario pedir el driver

        GlobalResources globalResources = new GlobalResources(driver);

        String inputName = "//*[@name='firstname']";
        String inputLastName = "//*[@name='lastname']";
        String selectMonth = "//*[@id='month' and @name='birthday_month']";
        String selectDay = "//*[@id='day' and @name='birthday_day']";
        String selectYear = "//*[@id='year' and @name='birthday_year']";
        String inputEmail = "//*[@name='reg_email__']";
        String inputPassword = "//*[@name='reg_passwd__']";

        globalResources.Write(inputName,validationSignIn.firstName);
        globalResources.Write(inputLastName, validationSignIn.lastName);
        globalResources.SelectListValue(selectDay, validationSignIn.dayBirthday);
        globalResources.SelectListText(selectMonth, validationSignIn.monthBirthday);
        globalResources.SelectListValue(selectYear, validationSignIn.yearBirthday);
        GenderSelectionSwitchCase(validationSignIn.gender);
        globalResources.Write(inputEmail, validationSignIn.email);
        globalResources.Write(inputPassword, validationSignIn.password);
    }
}
