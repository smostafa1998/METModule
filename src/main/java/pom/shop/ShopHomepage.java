package pom.shop;

import baseAPI.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShopHomepage extends BasePage {

    public ShopHomepage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//*[@id=\"jq-global-nav\"]/ul/li[1]/div[1]/div/div/a")
    public List<WebElement> categories;

    @FindBy(xpath="//*[@id=\"search\"]")
    public WebElement searchBar;

    @FindBy(xpath="//*[@id=\"jq-global-nav\"]/ul/li[1]/div[1]/div/div/a/span")
    public WebElement jewelryText;



    public ShopResultspage navigateToShopResultsPage(){
        usingReturnButtonForSearch(searchBar,"greek");
        return new ShopResultspage();
    }
}
