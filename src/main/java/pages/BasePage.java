package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverHolder;

import java.util.NoSuchElementException;

public abstract class BasePage {

    private By loans = By.cssSelector("[data-utag-name='loans']");
    private By mortgages = By.cssSelector("[data-utag-name='mortgage_loan']");

    public MortgagesPage goToMortgagesPage(){
        findExplicitly(loans).click();
        findExplicitly(mortgages).click();
        return new MortgagesPage();
    }

    protected WebElement findExplicitly(By selector) {
        WebDriverWait wait = (WebDriverWait)new WebDriverWait(DriverHolder.getDriver(), 20).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }
}
