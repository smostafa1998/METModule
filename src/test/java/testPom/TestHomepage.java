package testPom;

import org.testng.annotations.Test;
import pom.Homepage;
import testBase.TestBase;

import java.io.IOException;
import java.util.List;

public class TestHomepage extends TestBase {

    @Test
    public void testTabs() {
        Homepage homepage = getHomepage();
        clickOnElement(homepage.visitTab);
        waitForElementsToBeVisible(homepage.visitSubTabs);
        homepage.assertManualList(oneDList(homepage.visitSubTabs), homepage.getTabsTitles());
    }

    @Test()
    public void testExhibitionCarousal() throws IOException {
        Homepage homepage = getHomepage();
        scrollJS(200);
        List<String> exhibitionsName = homepage.getCarousalInfo(homepage.carousalChosen, homepage.nextSlideButton);
        homepage.assertOneDList(exhibitionsName, "Exhibitions");
    }

    @Test
    public void testHyperLinkBottom() {
        Homepage homepage = new Homepage();
        scrollJS(6000);
        waitForElementsToBeVisible(homepage.hyperlinksOnBottom);
        homepage.assertManualList(oneDList(homepage.hyperlinksOnBottom), homepage.getHyperlinkList());
    }

    @Test
    public void testMoreToExploreOnline() throws IOException {
        Homepage homepage = new Homepage();
        scrollJS(5100);
        List<String> moreToExplore = homepage.getCarousalInfo(homepage.moreToExploreNames, homepage.nextButtonMoreToExplore);
        List<String> moreToExploreHREF = homepage.getHyperLinkCarousal(homepage.moreToExploreNames);
        homepage.assertOneDList(moreToExploreHREF, "Explore");
    }

    @Test
    public void testVisitingTheMet() throws IOException {
        Homepage homepage = new Homepage();
        clickOnElement(homepage.readVisitorGuidelinesLink);
        homepage.assertOneDList(oneDList(homepage.rulesText),"Rules");
    }

    @Test
    public void testVisitingGuidelinesText(){
        Homepage homepage = new Homepage();
        clickOnElement(homepage.readVisitorGuidelinesLink);
        homepage.assertManualList(oneDList(homepage.vistiorGuidelinesNames), homepage.getVisitorGuidelinesTab());
    }

}
