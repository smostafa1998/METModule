package testPom;

import org.testng.Assert;
import org.testng.annotations.Test;
import pom.Homepage;
import pom.SearchResults;
import testBase.TestBase;

import java.io.IOException;
import java.util.List;

public class TestSearchResults extends TestBase {
    @Test
    public void testHomepageSearchBar(){
        Homepage homepage = getHomepage();
        SearchResults result = homepage.navigateToSearchResults();
        waitForElementToContainText(result.allResultsText,"95 results for \"sunflowers\"");
        String actualText = result.allResultsText.getText();
        String expectedText = "95 results for \"sunflowers\"";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void testHomepageArtHREFResults() throws IOException {
        Homepage homepage = getHomepage();
        SearchResults result = homepage.navigateToSearchResults();
        printOutHrefListOfElements(result.artworksResultsHREF);
        printOutListOfElements(result.artworksResultsTITLE);
        List<String> test = oneDList(result.artworksResultsTITLE);
        database.insertDataFrom2ListsToSqlTable(oneDList(result.artworksResultsTITLE),oneDhref(result.artworksResultsHREF),"MetSearch","Titles","Links");
        //database.insertDataFromListToSqlTable(test,"MetSearch","Results");
        //database.insertDataFromListToSqlTable(oneDhref(result.artworksResultsHREF),"MetSearchHREF","Links");
        homepage.assertOneDList(oneDhref(result.artworksResultsHREF),"MetSRT1");
        //database.insertDataFromListToSqlTable(test,"Met Search Bar #1","Sunflower Results");
    }

}
