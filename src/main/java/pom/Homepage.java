package pom;

import baseAPI.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import pom.exhibitionsAndEvents.EventsHomepage;
import pom.exhibitionsAndEvents.ExhibitionsHomepage;
import pom.shop.ShopHomepage;

import java.io.IOException;
import java.util.List;

public class Homepage extends BasePage {

    public Homepage(){
        PageFactory.initElements(driver,this);
    }

    /**
     * Put all the MET elements found in homepage
     */
    @FindBy(xpath = "//*[@id=\"homepage-video-banner\"]/div[2]/a")
    public WebElement planYourVisitButton;

    @FindBy(xpath = "//header/div/div/div[2]//*[@title=\"Search Button\"]")
    public WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"SearchText\"]")
    public WebElement searchBox;

    @FindBy(xpath = "//div[2]//li[7]/div/div/form/button")
    public WebElement goButton;

    @FindBy(xpath = "//*[@title=\"Toggle Menu\"]")
    public WebElement NavButton;

    @FindBy(xpath = "//*[@data-nav-id=\"visit\"]")
    public WebElement visitTab;

    @FindBy(xpath = "//*[@data-nav-id=\"exhibitions\"]")
    public WebElement exhibitionsAndEventsTab;

    @FindBy(xpath = "//*[@href=\"/exhibitions\"]")
    public WebElement exhibitionsTab;

    @FindBy(xpath = "//*[@href=\"/events/whats-on\"]")
    public WebElement eventsTab;

    @FindBy(xpath = "//*[@data-nav-id=\"art\"]")
    public WebElement artTab;

    @FindBy(xpath = "//*[@href=\"/art/collection\"]")
    public WebElement artCollectionTab;

    @FindBy(xpath="//*[@href=\"/art/art-at-home\"]")
    public WebElement artAtHomeTab;

    @FindBy(xpath="//*[@data-subnav-for=\"art\"]/ul/li[3]/a")
    public WebElement perspectivesTab;

    @FindBy(xpath = "//*[@data-nav-id=\"learn\"]")
    public WebElement learnTab;

    @FindBy(xpath = "//*[@href=\"/learn/learning-resources\"]")
    public WebElement learningResourcesTab;

    @FindBy(xpath = "//*[@href=\"/art/online-features/metkids\"]")
    public WebElement metKidsTab;

    @FindBy(xpath = "//*[@href=\"https://www.metmuseum.org/toah/\"]")
    public WebElement timelineOfArtHistoryTab;

    @FindBy(xpath = "//*[@href=\"/learn/workshops-and-activities\"]")
    public WebElement workshopsAndActivities;

    @FindBy(xpath="//*[@href=\"/art/libraries-and-research-centers\"]")
    public WebElement librariesAndResearchCenters;

    @FindBy(xpath = "//*[@data-nav-id=\"shop\"]")
    public WebElement shopTab;

    public PlanYourVisit navigateToVisit(){
        waitForElementToBeVisible(planYourVisitButton);
        clickOnElement(planYourVisitButton);
        return new PlanYourVisit();
    }

    public PlanYourVisit navigateToVisitTab(){
        //clickOnElement(NavButton);
        waitForElementToBeVisible(visitTab);
        clickOnElement(visitTab);
        clickOnElement(planYourVisitButton);
        return new PlanYourVisit();
    }

    public SearchResults navigateToSearchResults(){
        waitForElementToBeVisible(searchButton);
        clickOnElement(searchButton);
        addingKeyboardInput(searchBox,"sunflowers");
        clickOnElement(goButton);
        return new SearchResults();
    }

    public EventsHomepage navigateToEventsHomepage(){
        //waitForElementToBeVisible(NavButton);
        //clickOnElement(NavButton);
        clickOnElement(exhibitionsAndEventsTab);
        clickOnElement(eventsTab);
        return new EventsHomepage();
    }

    public ExhibitionsHomepage navigateToExhibitionsHomepage(){
        //waitForElementToBeVisible(NavButton);
        //clickOnElement(NavButton);
        clickOnElement(exhibitionsAndEventsTab);
        clickOnElement(exhibitionsTab);
        return new ExhibitionsHomepage();
    }

    public ShopHomepage navigateToShopHomepage(){
        //waitForElementToBeVisible(NavButton);
        //waitForElementToBeVisible(NavButton);
        clickOnElement(shopTab);
        return new ShopHomepage();
    }


    /**
     * When using this method know that before hand u must create the xlsx file and
     * add the sheet name u are referring to
     * also manually add in the data u are comparing with the List<WebElement>
     * @param tested
     * @param sheetname
     * @throws IOException
     */
    public void assertOneDList(List<String> tested, String sheetname) throws IOException {
        String relPath = ABSOLUTE_PATH + "/src/test/resources/MetTestCases.xlsx";
        SoftAssert softAssert = new SoftAssert();
        String[] expectedText = dataReader.fileReaderStringXSSF(relPath, sheetname);
        int length = tested.size();
        for (int i = 0; i < length; i++) {
            System.out.println("EXPECTED: " + expectedText[i] + "\nACTUAL: " + tested.get(i));
            softAssert.assertEquals(tested.get(i), expectedText[i]);
        }
        softAssert.assertAll();
    }


}
