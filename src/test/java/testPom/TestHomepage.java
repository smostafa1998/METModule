package testPom;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pom.Homepage;
import testBase.TestBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestHomepage extends TestBase {

    @Test
    public void testTabs(){
        Homepage homepage = getHomepage();
        clickOnElement(homepage.visitTab);
        waitForElementsToBeVisible(homepage.visitSubTabs);
        List<String> actualText=oneDList(homepage.visitSubTabs);
        List<String> expectedText = homepage.getTabsTitles();
        SoftAssert softAssert = new SoftAssert();
        for(int i = 0; i < actualText.size(); i++){
            System.out.println("EXPECTED: " + expectedText.get(i)+ "\nACTUAL: " + actualText.get(i));
            softAssert.assertEquals(actualText.get(i),expectedText.get(i));
        }
        softAssert.assertAll();
    }


    @Test ()
    public void testCarousal() throws IOException {
        Homepage homepage = getHomepage();
        scrollJS(200);
        List<String> exhibitionsName = new ArrayList<String>();
        List<String> exhibitionsHREF = new ArrayList<String>();
        for(int i =0;i<homepage.exhibitionsCarousal.size();i++){
            exhibitionsHREF.add(homepage.exhibitionsCarousal.get(i).getAttribute("href"));
        }
        for(int ii=0;ii<homepage.exhibitionsCarousal.size();ii++){
            for (int i = 0; i < homepage.exhibitionsCarousal.size(); i++) {
                if (homepage.exhibitionsCarousal.get(i).getAttribute("tabindex").equals("0")) {
                    waitForElementToBeVisible(homepage.exhibitionsCarousal.get(i));
                    System.out.println(homepage.exhibitionsCarousal.get(i).getText());
                    exhibitionsName.add(homepage.exhibitionsCarousal.get(i).getText());
                }
            }
            clickJScript(homepage.nextSlideButton);
        }
        homepage.assertOneDList(exhibitionsName,"Exhibitions");
    }


}
