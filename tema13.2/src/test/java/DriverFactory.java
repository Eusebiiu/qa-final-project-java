
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import io.github.bonigarcia.wdm.WebDriverManager; // Dacă folosiți WebDriverManager

public class DriverFactory {

    public static WebDriver getDriver() {
        // Asigurați-vă că aveți dependența WebDriverManager!
        //WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}