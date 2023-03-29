package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import javax.jws.soap.SOAPBinding;


public class LoginTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }
    @Test
    public void loginSuccess1(){
        User user = new User().setEmail("pochtadl9testov@gmail.com").setPassword("12345&Yes");
        app.getHelperUser().openLogInForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitButtonYalla();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
    }
    @Test
    public void loginSuccess() {
        app.getHelperUser().openLogInForm();
        app.getHelperUser().fillinLogInForm("pochtadl9testov@gmail.com", "12345&Yes");
        app.getHelperUser().submitButtonYalla();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    }

    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLogInForm();
        app.getHelperUser().fillinLogInForm("pochtadl9testov@gmail.com", "12345&Yes");
        app.getHelperUser().submitButtonYalla();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Logged in success\"");
    }
        @Test
        public void loginWrongEmail () {
            app.getHelperUser().openLogInForm();
            app.getHelperUser().fillinLogInForm("fgtrdfgtry ", "12345&Yes");
            app.getHelperUser().submitButtonYalla();
            Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
            Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        }
        @Test
        public void loginWrongPassword () {
            app.getHelperUser().openLogInForm();
            app.getHelperUser().fillinLogInForm("pochtadl9testov@gmail.com", "12345");
            app.getHelperUser().submitButtonYalla();
            Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        }
        @Test
        public void loginUnregisteredUser () {
            app.getHelperUser().openLogInForm();
            app.getHelperUser().fillinLogInForm("pochtadl9testov.pdt@gmail.com", "12345&YesYes");
            app.getHelperUser().submitButtonYalla();
            Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        }
        @AfterMethod
        public void postCondition () {
            app.getHelperUser().closeWindow();
        }
    }

