package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Random;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt( 1000)+1000;
        System.out.println(i);

        System.out.println(System.currentTimeMillis());
        int z =(int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .setFirstName("Kirill")
                .setLastName("Selickii")
                .setEmail("pochtadl9testov"+i+"@gmail.com")
                .setPassword("12345$Yesss");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submitButtonYalla();
        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
    }
    @Test
    public void registrationWrongEmail() {
        User user = new User()
                .setFirstName("Kirill")
                .setLastName("Selickii")
                .setEmail("pochtadl9testov@gmail.com")
                .setPassword("12345yes");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationWrongPWD() {
        User user = new User().setFirstName("Kirill")
                .setLastName("Selickii")
                .setEmail("pochtadl9testovgmail.com")
                .setPassword("12345&Yes");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationEmptyField() {
        User user = new User().setFirstName("")
                .setLastName("Selickii")
                .setEmail("pochtadl9testov@gmail.com")
                .setPassword("12345&Yes");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationRegisteredUser() {
        User user = new User().setFirstName("Kirill")
                .setLastName("Selickii")
                .setEmail("pochtadl9testov@gmail.com")
                .setPassword("12345&Yes");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submitButtonYalla();
        Assert.assertTrue(app.getHelperUser().isRegistered());

    }

    @AfterMethod
    public void postCondition () {
        app.getHelperUser().closeWindow();
    }
}

