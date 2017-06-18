package pages;

import utils.DriverHolder;

public class HomePage extends BasePage {
    private static final String HOME_URL = "https://ia.ca";

    public HomePage() {
        DriverHolder.getDriver().get(HOME_URL);
    }
}
