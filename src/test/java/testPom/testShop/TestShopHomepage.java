package testPom.testShop;

import org.testng.Assert;
import org.testng.annotations.Test;
import pom.Homepage;
import pom.shop.ShopHomepage;
import pom.shop.ShopResultspage;
import testBase.TestBase;

import java.io.IOException;

public class TestShopHomepage extends TestBase {

    @Test
    public void testCategoriesLink(){
        Homepage homepage = getHomepage();
        ShopHomepage shop = homepage.navigateToShopHomepage();
        waitForElementsToBeVisible(shop.categories);
        database.insertDataFromListToSqlTable(oneDhref(shop.categories),"ShopCategories","Category");
        String actualText = shop.jewelryText.getText();
        String expectedText = "Jewelry";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void testSearchBar() throws IOException {
        Homepage homepage = getHomepage();
        ShopHomepage shop = homepage.navigateToShopHomepage();
        ShopResultspage shopResults = shop.navigateToShopResultsPage();
        waitForElementsToBeVisible(shopResults.listOfGreekProducts);
        homepage.assertOneDList(oneDList(shopResults.listOfGreekProducts),"MetStoreT1");
    }
}
