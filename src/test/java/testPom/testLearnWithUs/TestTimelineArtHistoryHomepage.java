package testPom.testLearnWithUs;

import org.testng.annotations.Test;
import pom.Homepage;
import pom.learnWithUs.TimelineArtHistoryHomepage;
import testBase.TestBase;

public class TestTimelineArtHistoryHomepage extends TestBase {

    @Test
    public void testingEssayTitles(){
        Homepage homepage = getHomepage();
        TimelineArtHistoryHomepage artHistry = homepage.navigateToTimeLine();
        clickOnElement(artHistry.essaysTab);
        printOutListOfElements(artHistry.essayList);
    }

    @Test
    public void testingDropdowns(){
        Homepage homepage = getHomepage();
        TimelineArtHistoryHomepage artHistry = homepage.navigateToTimeLine();
        clickOnElement(artHistry.essaysTab) ;

    }
}
