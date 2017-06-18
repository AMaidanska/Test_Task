package com.maidanska.pages;

import com.maidanska.utils.DriverHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

public abstract class BasePage {

    private By loans = By.cssSelector("[data-utag-name='loans']");
    private By mortgages = By.cssSelector("[data-utag-name='mortgage_loan']");

    @Step("Go to mortgages page")
    public MortgagesPage goToMortgagesPage() {
        findExplicitly(loans).click();
        findExplicitly(mortgages).click();
        return new MortgagesPage();
    }

    protected WebElement findExplicitly(By selector) {
        WebDriverWait wait = new WebDriverWait(DriverHolder.getDriver(), 20);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }
}
