package pom;

import baseAPI.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlanYourVisit extends BasePage {
    public PlanYourVisit(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//*[@id=\"jump-link-banner\"]/div[1]/h1")
    public WebElement planYourVisitText;

    @FindBy(xpath="//*[@id=\"jump-link-banner\"]/div[1]/div/a[1]")
    public WebElement newYorkResident;

    @FindBy(xpath="//*[@id=\"jump-link-banner\"]/div[1]/div/a[2]")
    public WebElement student;

    @FindBy(xpath="//*[@id=\"jump-link-banner\"]/div[1]/div/a[3]")
    public WebElement newYorkVisitor;

    @FindBy(xpath="//*[@id=\"jump-link-banner\"]/div[1]/div/a[4]")
    public WebElement member;

    @FindBy(xpath="//*[@id=\"nys-residents\"]/div[1]/h2")
    public WebElement residentsAndStudentsText;

    @FindBy(xpath="//*[@id=\"gen-admin\"]/div[1]/h2")
    public WebElement generalAdmissionText;

    @FindBy(xpath="//*[@id=\"members\"]/div[1]/h2")
    public WebElement membersText;



}
