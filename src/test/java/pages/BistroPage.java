package pages;

import base.WebDriverSingleton;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static base.TestBase.BASE_URL;

public class BistroPage {
    private final WebDriver driver;

    public BistroPage() {
        driver = WebDriverSingleton.getInstance().getDriver();
    }

    public void openPage() {
        driver.get(BASE_URL);
    }

    public void enterLocation(String location) {
        driver.findElement(By.id("hp-autocomplete")).sendKeys(location);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.attributeContains(By.id("hp-autocomplete"), "value", location));
        driver.findElement(By.className("button")).click();
        if(driver.findElement(By.className("button")).isDisplayed()) driver.findElement(By.className("button")).click();

    }

    public void validateLocationPage(String location) {
        Assert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(),"Food delivery "+location);
    }

    public void pickFoodGroup(String foodGroup) {
        driver.findElement(By.xpath("//div[@class='cuisines']/a[text()='"+foodGroup+"']")).click();
    }

    public void validateFoodGroupPage(String foodGroup, String location) {
        Assert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(), foodGroup+" "+location);
    }

    public void setFilter(String sorting) {
        driver.findElement(By.className("selectButtonTitle")).click();
        driver.findElement(By.xpath("//label[text()='"+sorting+"']")).click();
        driver.findElement(By.xpath("//div[@class='restaurantFilter']/button")).click();
        driver.findElement(By.id("filterFreeDelivery")).click();
        driver.findElement(By.id("filterOnlinePayment")).click();
        driver.findElement(By.id("filterHomeDelivery")).click();
    }

    public void pickRestaurant(int rankingOfRestaurant) {
        driver.findElement(By.xpath("//div[@class='listItems topPadded']/div[2+"+rankingOfRestaurant+"]//a")).click();
    }

    public void validateRestaurantPage() {
        Assert.assertTrue(driver.findElement(By.className("cartTitle")).isDisplayed());
    }

    public void pickFood(int section, int itemInSection) {
        driver.findElement(By.xpath("//ul["+section+"]/li["+itemInSection+"]/a")).click();
    }

    public void enterTheAddress(String address) {

        driver.findElement(By.className("bistro-input__reset")).click();
        driver.findElement(By.name("specificationInput")).sendKeys(address);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions
                        .presenceOfElementLocated((By.xpath("//div[@class='location-results__result location-result']"))));
        driver.findElement(By.xpath("//div[@class='location-results__result location-result']")).click();

        new WebDriverWait (driver, 5)
                .until(ExpectedConditions.urlContains("#location-specified"));
    }

    public void checkModalWindow() {
        List<WebElement> l= driver.findElements(By.xpath("//div[@class='modal-bottom']/a"));
        if(l.size()== 0){
            driver.findElement(By.xpath("//div[@class='modal-bottom']/a")).click();
        }
    }

    public void validateItemsInCart(int countOfItems) {
        Assert.assertEquals(countOfItems-1, driver.findElements(By.xpath("//span[@class='name left editable']")).size());
    }
}
