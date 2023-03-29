package manager;
import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {super(wd);}

    public void openLogInForm(){
        WebElement loginTab = wd.findElement(By.xpath("// a[text()='Log in']"));
        loginTab.click();
        //click(By.xpath("// a[text()='Log in']"));
    }

    public void fillinLogInForm(String email, String password){
        type(By.xpath("//*[@id='email']"), email);
        type(By.xpath("//*[@id='password']"), email);
    }
    public void fillLoginForm(User user) {
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }
    public void submitButtonYalla() {click(By.cssSelector("[type='submit']"));
    }
    public String getMessage(){
    //    WebElement element = wd.findElement(By.cssSelector(".dialog-container>h2"));
    //    String  text = element.getText();
    //    return text;
        // wait
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".dialog-container"))));

        // pause(8000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }public String getRegisteredUserMessage(){

        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("//*[@class='message']']"))));

        return wd.findElement(By.xpath("//*[@class='message']")).getText();
    }
    public void closeWindow() {
        if(isElementPresent(By.xpath("//button[text()='Ok']")))
        click(By.xpath("//button[text()='Ok']"));
    }
    public boolean isLogged(){
        return isElementPresent(By.cssSelector("[ng-reflect-router-link='logout']"));
    }
    public boolean isRegistered() {return isElementPresent(By.xpath("//*[@class='message']"));}

    public void logout(){
        click(By.cssSelector("[ng-reflect-router-link='logout']")); // By.xpath("//button[text()='Logout']")
    }
    public String getErrorText() {
        String text = wd.findElement(By.cssSelector("div.error")).getText();
        System.out.println(text);
        return text;
    }

    public boolean isYallaButtonNotActive() {
        boolean res =  isElementPresent(By.cssSelector("button[disabled]"));
        WebElement element = wd.findElement(By.cssSelector("button[type='submit']"));
        boolean result = element.isEnabled();
        return res && !result;
    }
    ///****************** Registration****************
    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }
    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }
    public void checkPolicy() {
        // click(By.id("terms-of-use")); 0*0
        click(By.cssSelector("label[for='terms-of-use']"));
       // document.querySelector('#terms-of-use').click();
       // JavascriptExecutor js = (JavascriptExecutor) wd;
       // js.executeScript("document.querySelector('#terms-of-use').click();");
    }
    public void checkPolicyXY(){
        Dimension size = wd.manage().window().getSize();
        System.out.println("Wight screen -->" +size.getWidth());

        WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));
        Dimension dimension = label.getSize();

        Rectangle rect = label.getRect();
        int w = rect.getWidth();

        int xOffSet=-w/2;

        Actions actions =new Actions(wd);

        actions.moveToElement(label,xOffSet,0).click().release().perform();
    }



}
