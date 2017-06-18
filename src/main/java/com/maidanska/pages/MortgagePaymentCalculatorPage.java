package com.maidanska.pages;

import com.maidanska.utils.DriverHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.allure.annotations.Step;

public class MortgagePaymentCalculatorPage extends BasePage {
    private By purchasePrisePlusBtn = By.id("PrixProprietePlus");
    private By downPaymentPlusBtn = By.id("MiseDeFondPlus");
    private By amortizationDropDown = By.xpath("//*[contains(text(),'Amortization')]/following-sibling::div");
    private By paymentFrequencyDropDown = By.xpath("//*[contains(text(),'frequency')]/following-sibling::div[contains(@class,'wrapper')]");
    private By interestRateInput = By.id("TauxInteret");
    private By calculateBtn = By.id("btn_calculer");
    private By paymentsValue = By.id("paiement-resultats");
    private By purchasePriceSliderPointer = By.xpath("(//div[@class='slider-handle min-slider-handle custom'])[1]");
    private By purchasePriceSlider = By.xpath("(//*[@class='slider slider-horizontal'])[1]");
    private By downPaymentSliderPointer = By.xpath("(//div[@class='slider-handle min-slider-handle custom'])[2]");
    private By downPaymentSlider = By.xpath("(//*[@class='slider slider-horizontal'])[2]");

    @Step("Add purchase price using the + button")
    public void addPurchasePrice(int price) {
        int priceStep = 250_000;
        if (price % priceStep == 0) {
            int timesToClick = price / priceStep;
            for (int i = 0; i < timesToClick; i++) {
                DriverHolder.getDriver().findElement(purchasePrisePlusBtn).click();
            }
        } else {
            throw new IllegalArgumentException(String.format("Price should multiple of %d, but was %s", priceStep, price));
        }
    }

    @Step("Add down payment using + button")
    public void addDownPayment(int payment) {
        int paymentStep = 100_000;
        if (payment % paymentStep == 0) {
            int timesToClick = payment / paymentStep;
            for (int i = 0; i < timesToClick; i++) {
                DriverHolder.getDriver().findElement(downPaymentPlusBtn).click();
            }
        } else {
            throw new IllegalArgumentException(String.format("Price should multiple of %d, but was %s", paymentStep, payment));
        }
    }

    @Step("Select amortization years")
    public void selectAmortization(int index) {
        WebElement dropDown = DriverHolder.getDriver().findElement(amortizationDropDown);
        dropDown.click();
        dropDown.findElement(By.cssSelector(String.format("[data-index='%d']", index))).click();
    }

    @Step("Select payment frequency")
    public void selectPaymentFrequency(int index) {
        WebElement dropDown = DriverHolder.getDriver().findElement(paymentFrequencyDropDown);
        dropDown.click();
        dropDown.findElement(By.cssSelector(String.format("[data-index='%d']", index))).click();
    }

    @Step("Set interest rate")
    public void setInterestRate(double interestRate) {
        WebElement interestInput = DriverHolder.getDriver().findElement(interestRateInput);
        interestInput.clear();
        interestInput.sendKeys(String.valueOf(interestRate));
    }

    public double getPaymentsValue() {
        String text = findExplicitly(paymentsValue).getText();
        return Double.valueOf(text.substring(2, text.length()));
    }


    private void moveSlider(By sliderPointerLocator, By sliderLocator) {
        WebElement sliderPointer = DriverHolder.getDriver().findElement(sliderPointerLocator);
        WebElement slider = DriverHolder.getDriver().findElement(sliderLocator);
        Dimension sliderSize = slider.getSize();
        int sliderWidth = sliderSize.getWidth();
        int xCoordinate = sliderPointer.getLocation().getX();

        Actions builder = new Actions(DriverHolder.getDriver());
        builder.moveToElement(sliderPointer)
                .click()
                .dragAndDropBy(sliderPointer, xCoordinate + sliderWidth, 0)
                .build()
                .perform();
    }

    @Step("Move Purchase Price slider")
    public void movePurchasePriceSlider() {
        moveSlider(purchasePriceSliderPointer, purchasePriceSlider);
    }

    @Step("Move Down Payment slider")
    public void moveDownPaymentSlider() {
        moveSlider(downPaymentSliderPointer, downPaymentSlider);
    }

    public String getPurchasePriceSliderStyle() {
        return DriverHolder.getDriver().findElement(purchasePriceSliderPointer).getAttribute("style");
    }

    public String getDownPaymentSliderStyle() {
        return DriverHolder.getDriver().findElement(downPaymentSliderPointer).getAttribute("style");
    }

    public void calculatePayments(int purchasePrice, int downPayment, int amortizationIndex,
                                  int paymentFrequencyIndex, double interest) {
        addPurchasePrice(purchasePrice);
        addDownPayment(downPayment);
        selectAmortization(amortizationIndex);
        selectPaymentFrequency(paymentFrequencyIndex);
        setInterestRate(interest);
        calculate();
    }

    @Step("Click on calculate button")
    public void calculate() {
        findExplicitly(calculateBtn).click();
    }
}




