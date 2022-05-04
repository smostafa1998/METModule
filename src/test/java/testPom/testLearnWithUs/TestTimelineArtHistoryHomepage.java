package testPom.testLearnWithUs;

import baseAPI.DataProviders;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.Homepage;
import pom.learnWithUs.TimelineArtHistoryHomepage;
import testBase.TestBase;

import java.io.IOException;

public class TestTimelineArtHistoryHomepage extends TestBase {

    @Test
    public void testingEssayTitles() throws IOException {
        Homepage homepage = getHomepage();
        TimelineArtHistoryHomepage artHistry = homepage.navigateToTimeLine();
        clickOnElement(artHistry.essaysTab);
        database.insertDataFromListToSqlTable(oneDList(artHistry.essayList), "ARHEssayList", "Titles");
        homepage.assertOneDList(oneDList(artHistry.essayList), "EssayList");
    }

    @Test(dataProvider = "getEssayFilterData", dataProviderClass = DataProviders.class)
    public void testingEssaysDropdowns(String timePeriod, String region, String theme, String results) {
        Homepage homepage = getHomepage();
        TimelineArtHistoryHomepage artHistry = homepage.navigateToTimeLine();
        clickOnElement(artHistry.essaysTab);
        waitForElementsToBeVisible(artHistry.threeDropdowns);
        artHistry.workingWithFilters(timePeriod, region, theme);
        Assert.assertEquals(results, artHistry.essaysNumericalResults.getText());
    }

    @Test
    public void testWorksOfArt() {
        Homepage homepage = getHomepage();
        TimelineArtHistoryHomepage artHistory = homepage.navigateToTimeLine();
        clickOnElement(artHistory.worksOfArtTab);
        //printOutListOfElements();
    }

    @Test
    public void testWorksOfArtDropdown() {

    }

    @Test
    public void testChronologies() {
        Homepage homepage = getHomepage();
        TimelineArtHistoryHomepage artHistory = homepage.navigateToTimeLine();
        clickOnElement(artHistory.chronologyTab);
    }


}

        /*
        clickOnElement(artHistry.essaysTimePeriodDropdown);
        clickOnElement(selectFilterElementByString(timePeriod));
        clickOnElement(artHistry.essaysRegionDropdown);
        clickOnElement(selectFilterElementByString(region));
        clickOnElement(artHistry.essaysThemeDropDown);
        clickOnElement(selectFilterElementByString(theme));

         */