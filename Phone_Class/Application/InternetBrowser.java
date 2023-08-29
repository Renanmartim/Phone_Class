package Application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InternetBrowser {
    private final Enum site;

    public InternetBrowser(Enum site) {
        this.site = site;
    }

    public void openWebsite() {
        // Set the path to the ChromeDriver executable
        System.out.println("Search at " + this.site);
    }
}
