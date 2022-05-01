package pom.learnWithUs;

import baseAPI.BasePage;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LearnWithUsHomepage extends BasePage {
    public LearnWithUsHomepage(){
        PageFactory.initElements(driver,this);
    }

    /**
     * Put all Learn with us locators on this page
     */

}
