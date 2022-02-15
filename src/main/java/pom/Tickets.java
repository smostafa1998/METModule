package pom;

import baseAPI.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Tickets extends BasePage {

    public Tickets(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//*[@id=\"location-list\"]/li[1]/input")
    public WebElement metMuseam;

    @FindBy(xpath="//*[@id=\"location-list\"]/li[2]/input")
    public WebElement theCloisters;

    @FindBy(xpath="//*[@name=\"selectDate\"]")
    public List<WebElement> dateOptions;

    @FindBy(xpath="//*[@name=\"times\"]")
    public List<WebElement> timeOptions;







}
