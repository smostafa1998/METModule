package testPom.testVisit;

import org.testng.Assert;
import org.testng.annotations.Test;
import pom.Homepage;
import pom.visit.PlanYourVisit;
import testBase.TestBase;

public class TestPlanYourVisit extends TestBase {

    @Test(enabled = false)
    public void testPlanYourVisitText() {
        Homepage homepage = getHomepage();
        PlanYourVisit plan = homepage.navigateToVisit();
        waitForElementToBeVisible(plan.planYourVisitText);
        String actualText = plan.planYourVisitText.getText();
        String expectedText = "Plan your visit";
        Assert.assertEquals(actualText, expectedText);
        System.out.println("FOUND VISIT SITE");
    }

    @Test
    public void testLiveInNewYork() {
        Homepage homepage = getHomepage();
        PlanYourVisit plan = homepage.navigateToVisit();
        clickOnElement(plan.newYorkResident);
        waitForElementToBeVisible(plan.residentsAndStudentsText);
        String actualText = plan.residentsAndStudentsText.getText();
        String expectedText = "New York State Residents and NY, NJ, and CT Students ";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void testStudent() {
        Homepage homepage = getHomepage();
        PlanYourVisit plan = homepage.navigateToVisit();
        clickOnElement(plan.student);
        waitForElementToBeVisible(plan.residentsAndStudentsText);
        String actualText = plan.residentsAndStudentsText.getText();
        String expectedText = "New York State Residents and NY, NJ, and CT Students ";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void testNewYorkVisitor() {
        Homepage homepage = getHomepage();
        PlanYourVisit plan = homepage.navigateToVisit();
        clickOnElement(plan.newYorkVisitor);
        waitForElementToBeVisible(plan.generalAdmissionText);
        String actualText = plan.generalAdmissionText.getText();
        String expectedText = "General Admission ";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void testMember() {
        Homepage homepage = getHomepage();
        PlanYourVisit plan = homepage.navigateToVisit();
        clickOnElement(plan.member);
        waitForElementToBeVisible(plan.membersText);
        String actualText = plan.membersText.getText();
        String expectedText = "Members and Patrons ";
        Assert.assertEquals(actualText, expectedText);
    }


}
