import base.TestBase;
import org.junit.Test;
import pages.BistroPage;

public class E2eTest extends TestBase {


    @Test
    public void testE2E() {
        String location = "Bratislava";
        String foodGroup = "Drinks";     // All, Daily menu, Pizza, Asia, Hamburger, Kebab, Sushi, Traditional, Salads, ...
        String sorting = "Relevance";   // Delivery time, Min. order, Price of delivery, Rating, A-Z
        String address = "Bratislava";

        BistroPage bistroPage = new BistroPage();

        bistroPage.openPage();
        bistroPage.enterLocation(location);
        bistroPage.validateLocationPage(location);

        bistroPage.pickFoodGroup(foodGroup);
        bistroPage.validateFoodGroupPage(foodGroup, location);
        bistroPage.setFilter(sorting);
        bistroPage.pickRestaurant(1);
        bistroPage.validateRestaurantPage();

        bistroPage.pickFood(1,1);
        System.out.println(address);
        bistroPage.enterTheAddress(address);
        bistroPage.checkModalWindow();

        System.out.println("1.jedlo");

        bistroPage.pickFood(1,2);
        bistroPage.checkModalWindow();

        System.out.println("2.jedlo");

        bistroPage.pickFood(1,3);
        bistroPage.checkModalWindow();

        System.out.println("3.jedlo");

        bistroPage.validateItemsInCart(3);

    }
}
