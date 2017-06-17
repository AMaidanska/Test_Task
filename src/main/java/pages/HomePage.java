package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utils.DriverHolder;

public class HomePage extends BasePage{

    public HomePage(final String url) {
        DriverHolder.getDriver().get(url);
    }


}
