package testPom;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pom.Homepage;
import pom.Tickets;
import testBase.TestBase;

public class TestTickets extends TestBase {


    @Test
    public void chooseMetMuseumLocation() throws InterruptedException {
        Homepage homepage = getHomepage();
        Tickets tickets = homepage.navigateToTickets();
        clickJScript(tickets.metMuseam);
        scrollJS(1000);
        homepage.getDateAndTime(tickets.dateOptions,tickets.timeOptions);
        Thread.sleep(5000);
    }

    @Test
    public void chooseCloistersLocation() throws InterruptedException {
        Homepage homepage = getHomepage();
        Tickets tickets = homepage.navigateToTickets();
        clickJScript(tickets.theCloisters);
        scrollJS(1000);
        homepage.getDateAndTime(tickets.dateOptions,tickets.timeOptions);
        Thread.sleep(5000);
    }

}
