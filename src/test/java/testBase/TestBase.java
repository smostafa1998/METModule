package testBase;

import baseAPI.BasePage;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import pom.Homepage;
import pom.visit.PlanYourVisit;

public class TestBase extends BasePage {
    public Homepage getHomepage() {
        return new Homepage();
    }

    public PlanYourVisit getPlanYourVisit() {
        return new PlanYourVisit();
    }

    public boolean isElementPresent(WebElement element) {
        boolean flag = false;

        try {
            if (element.isDisplayed()) {
                flag = true;
            }
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
