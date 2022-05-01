package pom.learnWithUs;

import baseAPI.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TimelineArtHistoryHomepage extends BasePage {
    public TimelineArtHistoryHomepage(){
        PageFactory.initElements(driver,this);
    }

    /**
     * Put all the timeline art history stuff here
     */

    @FindBy(xpath="//*[@href=\"/toah/essays\"]")
    public WebElement essaysTab;

    @FindBy(xpath="//*[@href=\"/toah/works\"]")
    public WebElement worksOfArtTab;

    @FindBy(xpath="//*[@href=\"/toah/chronology\"]")
    public WebElement chronologyTab;

    @FindBy(xpath="//*[@class=\"essay-list\"]/li//a")
    public List<WebElement> essayList;





}
