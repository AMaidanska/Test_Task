package com.maidanska.pages;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

public class MortgagesPage extends BasePage {

    private By calculateYourPaymentsBtn = By.cssSelector(".btn-full[href='/mortgage-payment-calculator']");

    @Step("Click on 'calculate your payments' button")
    public MortgagePaymentCalculatorPage clickCalculateYourPaymentsBtn() {
        findExplicitly(calculateYourPaymentsBtn).click();
        return new MortgagePaymentCalculatorPage();
    }
}
