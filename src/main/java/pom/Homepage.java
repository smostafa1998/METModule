package pom;

import baseAPI.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import pom.exhibitionsAndEvents.EventsHomepage;
import pom.exhibitionsAndEvents.ExhibitionsHomepage;
import pom.shop.ShopHomepage;
import pom.visit.PlanYourVisit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Homepage extends BasePage {

    public Homepage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Put all the MET elements found in homepage
     */
    @FindBy(xpath = "//*[@id=\"homepage-video-banner\"]/div[2]/a")
    public WebElement planYourVisitButton;

    @FindBy(xpath = "//*[@id=\"mma-ribbon-banner\"]/section/div/a")
    public WebElement readVisitorGuidelinesLink;

    @FindBy(xpath = "//*[@id=\"main-content\"]//section/div[2]/p")
    public List<WebElement> rulesText;

    @FindBy(xpath = "//*[@id=\"main-content\"]//div[3]//section//h3[2]")
    public List<WebElement> vistiorGuidelinesNames;

    @FindBy(xpath = "//*[@id=\"our-locations\"]//div[1]/div/div[2]/a")
    public WebElement fifthAvenue;

    @FindBy(xpath = "//*[@id=\"our-locations\"]//div[2]/div/div[2]/a")
    public WebElement cloisterLocation;

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

    @FindBy(xpath = "//*[@data-subnav-for=\"visit\"]//li/a")
    public List<WebElement> visitSubTabs;

    @FindBy(xpath = "//*[@data-nav-id=\"exhibitions\"]")
    public WebElement exhibitionsAndEventsTab;

    @FindBy(xpath = "//*[@href=\"/exhibitions\"]")
    public WebElement exhibitionsTab;

    @FindBy(xpath = "//*[@id=\"exhibitions\"]//div/div/h3/a")
    public List<WebElement> carousalChosen;

    @FindBy(xpath = "//*[@id=\"exhibitions\"]/div[2]/button[2]")
    public WebElement nextSlideButton;

    @FindBy(xpath = "//*[@href=\"/events/whats-on\"]")
    public WebElement eventsTab;

    @FindBy(xpath = "//*[@data-nav-id=\"art\"]")
    public WebElement artTab;

    @FindBy(xpath = "//*[@href=\"/art/collection\"]")
    public WebElement artCollectionTab;

    @FindBy(xpath = "//*[@href=\"/art/art-at-home\"]")
    public WebElement artAtHomeTab;

    @FindBy(xpath = "//*[@data-subnav-for=\"art\"]/ul/li[3]/a")
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

    @FindBy(xpath = "//*[@href=\"/art/libraries-and-research-centers\"]")
    public WebElement librariesAndResearchCenters;

    @FindBy(xpath = "//*[@data-nav-id=\"shop\"]")
    public WebElement shopTab;

    @FindBy(xpath = "//*[@id=\"nas-footer\"]/footer/section[2]/div/ul/li")
    public List<WebElement> hyperlinksOnBottom;

    @FindBy(xpath = "//*[@id=\"discover-more-at-the-met\"]/div[2]/button[2]")
    public WebElement nextButtonMoreToExplore;

    @FindBy(xpath="//*[@id=\"discover-more-at-the-met\"]/div[2]/div/div/div/a")
    public List<WebElement> moreToExploreNames;

    public PlanYourVisit navigateToVisit() {
        waitForElementToBeVisible(planYourVisitButton);
        clickOnElement(planYourVisitButton);
        return new PlanYourVisit();
    }

    public PlanYourVisit navigateToVisitTab() {
        //clickOnElement(NavButton);
        waitForElementToBeVisible(visitTab);
        clickOnElement(visitTab);
        clickOnElement(planYourVisitButton);
        return new PlanYourVisit();
    }

    public SearchResults navigateToSearchResults() {
        waitForElementToBeVisible(searchButton);
        clickOnElement(searchButton);
        addingKeyboardInput(searchBox, "sunflowers");
        clickOnElement(goButton);
        return new SearchResults();
    }

    public EventsHomepage navigateToEventsHomepage() {
        //waitForElementToBeVisible(NavButton);
        //clickOnElement(NavButton);
        clickOnElement(exhibitionsAndEventsTab);
        clickOnElement(eventsTab);
        return new EventsHomepage();
    }

    public ExhibitionsHomepage navigateToExhibitionsHomepage() {
        //waitForElementToBeVisible(NavButton);
        //clickOnElement(NavButton);
        clickOnElement(exhibitionsAndEventsTab);
        clickOnElement(exhibitionsTab);
        return new ExhibitionsHomepage();
    }

    public ShopHomepage navigateToShopHomepage() {
        //waitForElementToBeVisible(NavButton);
        //waitForElementToBeVisible(NavButton);
        clickOnElement(shopTab);
        return new ShopHomepage();
    }

    public List<String> getTabsTitles() {
        List<String> expectedText = new ArrayList<String>();
        expectedText.add("Plan Your Visit");
        expectedText.add("Buy Tickets");
        expectedText.add("Become a Member");
        expectedText.add("Museum Map");
        expectedText.add("Audio Guide");
        expectedText.add("Group Visits");
        return expectedText;
    }

    public List<String> getHyperlinkList() {
        List<String> hyperlinks = new ArrayList<String>();
        hyperlinks.add("About The Met");
        hyperlinks.add("Mission and History");
        hyperlinks.add("Collection Areas");
        hyperlinks.add("Conservation Departments");
        hyperlinks.add("Press");
        hyperlinks.add("Support");
        hyperlinks.add("Membership");
        hyperlinks.add("Host an Event");
        hyperlinks.add("Travel with The Met");
        hyperlinks.add("Corporate Support");
        return hyperlinks;
    }

    public List<String> getVisitorGuidelinesTab(){
        List<String> visitorGuidelinesTabs = new ArrayList<String>();
        visitorGuidelinesTabs.add("Important Additional Guidelines");
        visitorGuidelinesTabs.add("Museum Admission, Entry, and Hours");
        visitorGuidelinesTabs.add("Entering the Building");
        visitorGuidelinesTabs.add("Gallery Photography Policy");
        return visitorGuidelinesTabs;
    }


    public List<String> getCarousalInfo(List<WebElement> carousalChosen, WebElement button) {
        List<String> exhibitionsName = new ArrayList<String>();
        for (int ii = 0; ii < carousalChosen.size(); ii++) {
            for (WebElement webElement : carousalChosen) {
                if (webElement.getAttribute("tabindex").equals("0")) {
                    waitForElementToBeVisible(webElement);
                    System.out.println(webElement.getText());
                    exhibitionsName.add(webElement.getText());
                }
            }
            clickJScript(button);
        }
        return exhibitionsName;
    }

    public List<String> getHyperLinkCarousal(List<WebElement> carousalChosen){
        List<String> linksHREF = new ArrayList<String>();
        for (WebElement webElement : carousalChosen) {
            linksHREF.add(webElement.getAttribute("href"));
        }
        return linksHREF;
    }



    /**
     * When using this method know that before hand u must create the xlsx file and
     * add the sheet name u are referring to
     * also manually add in the data u are comparing with the List<WebElement>
     *
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

    public void assertManualList(List<String> actualText, List<String> expectedText) {
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < actualText.size(); i++) {
            System.out.println("EXPECTED: " + expectedText.get(i) + "\nACTUAL: " + actualText.get(i));
            softAssert.assertEquals(actualText.get(i), expectedText.get(i));
        }
        softAssert.assertAll();
    }


}
