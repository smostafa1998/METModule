package testPom.testExhibitionsAndEvents;

import org.testng.Assert;
import org.testng.annotations.Test;
import pom.Homepage;
import pom.exhibitionsAndEvents.ExhibitionsHomepage;
import testBase.TestBase;

public class TestExhibitionsHomepage extends TestBase {

    @Test
    public void testListofExhibitions(){
        Homepage homepage = getHomepage();
        ExhibitionsHomepage exhibitions = homepage.navigateToExhibitionsHomepage();
        waitForElementToBeVisible(exhibitions.exhibitionsTitle);
        String actualText = exhibitions.exhibitionsTitle.getText();
        String expectedText = "Exhibitions";
        Assert.assertEquals(actualText, expectedText);
        waitForElementsToBeVisible(exhibitions.exhibitionsName);
        database.insertDataFromListToSqlTable(oneDList(exhibitions.exhibitionsName),"Exhibitions","Title");
    }
}
