package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;


public class ApplicationManager {

    WebDriver wd;
    HelperUser helperUser;
    HelperCar helperCar;

    public void init(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        wd = new ChromeDriver(options);
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.navigate().to("https://ilcarro.web.app/");
        //wd.navigate().back();
        // wd.navigate().refresh();
        helperUser =new HelperUser(wd);
        helperCar = new HelperCar(wd);
    }
    public void stop(){
        wd.quit();
    }
    public HelperUser getHelperUser() {
        return helperUser;
    }
    public HelperCar getHelperCar() {
        return helperCar;
    }
}
