package pom.learnWithUs;

import baseAPI.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TimelineArtHistoryHomepage extends BasePage {
    public TimelineArtHistoryHomepage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Put all the timeline art history stuff here
     */

    @FindBy(xpath = "//*[@href=\"/toah/essays\"]")
    public WebElement essaysTab;

    @FindBy(xpath = "//*[@href=\"/toah/works\"]")
    public WebElement worksOfArtTab;

    @FindBy(xpath = "//*[@href=\"/toah/chronology\"]")
    public WebElement chronologyTab;

    @FindBy(xpath = "//*[@class=\"essay-list\"]/li//a")
    public List<WebElement> essayList;

    @FindBy(xpath = "//*[@class=\"filters clearfix ng-scope\"]/div/div")
    public List<WebElement> threeDropdowns;

    @FindBy(xpath = "//*[@class=\"essay-list-heading ng-scope\"]/span[2]")
    public WebElement essaysNumericalResults;

    @FindBy(xpath = "//*[@class=\"filters clearfix ng-scope\"]/div[1]/div")
    public WebElement essaysTimePeriodDropdown;

    @FindBy(xpath = "//*[@class=\"filters clearfix ng-scope\"]/div[2]/div")
    public WebElement essaysRegionDropdown;

    @FindBy(xpath = "//*[@class=\"filters clearfix ng-scope\"]/div[3]/div")
    public WebElement essaysThemeDropDown;


    public void workingWithFilters(String timePeriod, String region, String theme) {
        for (int i = 0; i < threeDropdowns.size(); i++) {
            clickOnElement(threeDropdowns.get(i));
            if (i == 0) {
                clickOnElement(selectFilterElementByString(timePeriod));
            } else if (i == 1) {
                clickOnElement(selectFilterElementByString(region));
            } else if (i == 2) {
                clickOnElement(selectFilterElementByString(theme));
            }
        }
    }


}
