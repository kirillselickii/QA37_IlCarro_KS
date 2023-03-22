package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {super(wd);}

    public void openLog_inForm(){
        WebElement loginTab = wd.findElement(By.xpath("// a[text()='Log in']"));
        loginTab.click();
    }
    public void fillLoginRegistrationForm(String email, String password){
        type(By.xpath("//*[@id='email']"), email);

        type(By.xpath("//*[@id='password']"), password);
    }
    public void submitLogin() {click(By.cssSelector("[type='submit']"));
    }
    public boolean isLogged(){
        return isElementPresent(By.cssSelector("[ng-reflect-router-link='logout']"));
    }
    public void logout(){
        click(By.xpath("//button[text()='Logout']")); // By.xpath("//button[text()='Logout']")
    }
    public String getMessage(){
    return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }

    public void closeWindow() {
        click(By.xpath("//button[text()='Ok']"));
    }
}
