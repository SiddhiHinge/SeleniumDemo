import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Demo {
    public static void main(String[] args) {
        WebDriver webDriver = new ChromeDriver(); //Opens browser with blank page
        webDriver.get("https://podtest.in/"); //Opens a url in opened browser

        WebDriver ffDriver = new FirefoxDriver();
        ffDriver.get("https://podtest.in/");
    }
}
