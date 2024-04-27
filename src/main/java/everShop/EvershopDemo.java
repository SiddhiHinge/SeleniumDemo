package everShop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EvershopDemo {
    public static void main(String[] args) {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://demo.evershop.io/account/login");
        webDriver.findElement(By.name("email")).sendKeys("siddhihinge@mailinator.com");
        webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
    }
}
