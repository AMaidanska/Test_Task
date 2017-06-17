package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MortgagesPage extends BasePage{

    private By calculateYourPaymentsBtn = By.cssSelector(".btn-full[href='/mortgage-payment-calculator']");

    public MortgagePaymentCalculatorPage clickCalculateYourPaynentsBtn(){
        findExplicitly(calculateYourPaymentsBtn).click();
        return  new MortgagePaymentCalculatorPage();
    }
}
