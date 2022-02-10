package pom.exhibitionsAndEvents;

import baseAPI.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ExhibitionsHomepage extends BasePage {

    public ExhibitionsHomepage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id=\"exhibitions-app\"]/h2")
    public WebElement exhibitionsTitle;

    @FindBy(xpath = "//*[@id=\"exhibitions-app\"]/div/section[1]//div/h4/a")
    public List<WebElement> exhibitionsName;

}
