import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GridDemo {
    public static void main(String[] args) {
        WebDriver webDriver; //Opens browser with blank page
        String browserName = "Chrome";
        if(browserName.equalsIgnoreCase("chrome")){
            webDriver = new ChromeDriver();
            webDriver.get("https://podtest.in/");
        } else if (browserName.equalsIgnoreCase("firefox")) {
            webDriver = new FirefoxDriver();
            webDriver.get("https://podtest.in/");
        }
    }
}
