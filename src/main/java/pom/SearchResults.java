package pom;

import baseAPI.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResults extends BasePage {

    public SearchResults (){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "/html/body/header/h1/span[3]/span")
    public WebElement allResultsText;

    @FindBy(xpath="//*[@id=\"main-content\"]/div/div/div[2]/div[1]/div/a")
    public List<WebElement> artworksResultsHREF;

    @FindBy(xpath = "//*[@id=\"main-content\"]//a/div/div[2]/div/h2")
    public List<WebElement> artworksResultsTITLE;





}
