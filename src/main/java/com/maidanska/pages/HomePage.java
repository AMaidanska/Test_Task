package com.maidanska.pages;

import com.maidanska.utils.DriverHolder;

public class HomePage extends BasePage {
    private static final String HOME_URL = "https://ia.ca";

    public HomePage() {
        DriverHolder.getDriver().get(HOME_URL);
    }
}
