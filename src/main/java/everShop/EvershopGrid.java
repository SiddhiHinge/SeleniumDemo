package everShop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EvershopGrid {
    WebDriver webDriver;
    WebDriverWait wait;
    public void basicSetup() throws MalformedURLException {
        ChromeOptions chrome = new ChromeOptions();
        webDriver = new RemoteWebDriver(new URL("http://localhost:4444"),chrome); //Start the selenium grid & see a session added for opened
        // chrome browser
        //wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        EvershopGrid test = new EvershopGrid();
        List<Product> productList;
        test.basicSetup();
        test.loginAction();
        //test.login();
        //test.waitDemo();
        //test.addToCart();
        /*test.countryDropDownOnCheckout();
        productList = test.cartWebTables();
        test.printCartDetails(productList);
        test.removeItem();
        productList = test.cartWebTables();*/
    }

    public void login(){
        webDriver.get("https://demo.evershop.io/account/login");
        webDriver.findElement(By.name("email")).sendKeys("siddhihinge@mailinator.com");
        webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
        String actualPasswordText = webDriver.findElement(By.name("password")).getAttribute("placeholder");
        //System.out.println("Password value = "+actualPasswordText);
        String expectedSignInButton = "SIGN IN";
        String actualSignInButton = webDriver.findElement(By.xpath("//button[@type='submit']/span")).getText();
        if(expectedSignInButton.equals(actualSignInButton)){
            webDriver.findElement(By.xpath("//button[@type='submit']")).click();
        }
        else{
            System.out.println("Button not correct !");
        }
    }

    public void addToCart() throws InterruptedException {
        webDriver.findElement(By.xpath("//span[text()='Nike air zoom pegasus 35']")).click(); //just this line of code will not work bcoz
        // of synchronization issue
        webDriver.findElement(By.xpath("//input[@name='qty']")).clear();
        webDriver.findElement(By.xpath("//input[@name='qty']")).sendKeys("3");
        //If we do not use clear on Qty before sendkeys then it will append the value in Qty box eg 13 & not 3 hence use clear first
        webDriver.findElement(By.xpath("(//ul[contains(@class,'variant-option-list')])[1]//a[text()='M']")).click();
        webDriver.findElement(By.xpath("(//ul[contains(@class,'variant-option-list')])[2]//a[text()='Black']")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//button[@type='button']/span")).click();
    }

    public void countryDropDownOnCheckout(){
        //Select Country from dropdown in Checkout page
        //To get the dropdown
        webDriver.navigate().to("https://demo.evershop.io/checkout");
        /*WebElement selectCountry = webDriver.findElement(By.xpath("//select[contains(@id,'country')]"));
        Select testCountry = new Select(selectCountry);
        selectCountry.click(); //This will open up the dropdown
        testCountry.selectByValue("IN");*/

        //New way to get dropdown
        List<WebElement> countryOptions = webDriver.findElements(By.xpath("//select[contains(@id,'address')]/option"));
        countryOptions.get(1).click();
        Iterator<WebElement> itr = countryOptions.iterator();
        while(itr.hasNext()){
            WebElement option = itr.next();
            if(option.getText().equalsIgnoreCase("China")){
                option.click();
            }
        }
    }

    public List<Product> cartWebTables() throws InterruptedException {
        Thread.sleep(5000);
        webDriver.navigate().to("https://demo.evershop.io/cart");
        List<WebElement> rows = webDriver.findElements(By.xpath("//table[contains(@class,'items-table')]/tbody/tr"));
        Iterator<WebElement> itr = rows.iterator();
        List<Product> productList = new ArrayList<>();
        while(itr.hasNext()){
            WebElement e = itr.next();
            String name = e.findElement(By.xpath("td/div/div[contains(@class,'cart-tem-info')]/a")).getText(); //Why no slash at start ??
            String colour = e.findElements(By.xpath("td/div/div/div[contains(@class,'cart-item-variant')]/ul/li/span[2]")).get(0).getText();
            String size = e.findElements(By.xpath("td/div/div/div[contains(@class,'cart-item-variant')]/ul/li/span[2]")).get(1).getText();
            String price = e.findElement(By.xpath("td/div/span[contains(@class,'sale-price')]")).getText();
            int qty = Integer.parseInt(e.findElements(By.xpath("td[contains(@class,'hidden md:table-cell')]/span")).get(0).getText());
            String value = e.findElements(By.xpath("td[contains(@class,'hidden md:table-cell')]/span")).get(1).getText();
            Product p = new Product(name,colour,size,price,qty,value);
            productList.add(p);
        }
        System.out.println("Items in Cart : "+getListCount(rows));
        return productList;
    }

    public int getListCount(List<WebElement> list) {
        int count = list.size();
        return count;
    }

    public void removeItem() throws InterruptedException {
        String productName = "Alphaboost shoes";
        List<WebElement> rows = webDriver.findElements(By.xpath("//table[contains(@class,'items-table')]/tbody/tr/td/div/div[contains(@class,'cart-tem-info')]"));
        Iterator<WebElement> itr = rows.iterator();
        while(itr.hasNext()){
            WebElement e = itr.next();
            String name = e.findElement(By.xpath("a")).getText();
            //System.out.println(name);
            //System.out.println(name.equalsIgnoreCase(productName));
            if(name.equalsIgnoreCase(productName)){
                e.findElement(By.xpath("div[@class='mt-05']/a/span")).click();
            }
        }
    }

    public void printCartDetails(List<Product> productList){
        for(Product p : productList){
            p.printProduct();
        }
    }

    public void waitDemo(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Nike air zoom pegasus 35']")));
        webDriver.findElement(By.xpath("//span[text()='Nike air zoom pegasus 35']")).click();
        /*Wait fluentWait = new FluentWait(webDriver);
        fluentWait.withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Nike air zoom pegasus 35']")));*/
    }

    public void loginAction(){
        webDriver.get("https://demo.evershop.io/account/login");
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(By.name("email"))).click().sendKeys("siddhihinge@mailinator.com").perform();
    }
}
