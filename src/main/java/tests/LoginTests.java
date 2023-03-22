package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBese{
    @BeforeMethod
    public void preCondition(){

        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void test(){
        app.getHelperUser().openLog_inForm();
        app.getHelperUser().fillLoginRegistrationForm("pochtadl9testov@gmail.com", "12345&Yes");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        app.getHelperUser().closeWindow();
    }
    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLog_inForm();
        app.getHelperUser().fillLoginRegistrationForm("pochtadl9testov@gmailcom","12345&Yes");
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().isLogged(), "Login or Password incorrect");
        app.getHelperUser().closeWindow();
    }
    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLog_inForm();
        app.getHelperUser().fillLoginRegistrationForm("pochtadl9testov@gmail.com","12345Yes");
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().isLogged(), "Login or Password incorrect");
        app.getHelperUser().closeWindow();
    }
    @Test
    public void loginUnregisteredUser(){
        app.getHelperUser().openLog_inForm();
        app.getHelperUser().fillLoginRegistrationForm("pochtadl9testov.pdt@gmail.com","12345&YesYes");
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().isLogged(), "Login or Password incorrect");
        app.getHelperUser().closeWindow();
    }
}
