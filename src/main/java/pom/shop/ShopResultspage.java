package pom.shop;

import baseAPI.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShopResultspage extends BasePage {

    public ShopResultspage (){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id=\"ajax-product-grid\"]/ol/li//strong/a")
    public List<WebElement> listOfGreekProducts;


}
