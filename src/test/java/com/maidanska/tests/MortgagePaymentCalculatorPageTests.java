package com.maidanska.tests;

import com.maidanska.pages.HomePage;
import com.maidanska.pages.MortgagePaymentCalculatorPage;
import com.maidanska.pages.MortgagesPage;
import com.maidanska.utils.DriverHolder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Features("Mortgage payment calculator page tests ")
public class MortgagePaymentCalculatorPageTests {

    @DataProvider
    public Object[][] canCalculatePaymentCase() {
        return new Object[][]{{500000, 100000, 0, 3, 5, 726.35}};
    }

    @DataProvider
    public Object[][] canMoveSlidersCases() {
        return new Object[][]{{"left: 0", "left: 100"}};
    }

    @Title("Payment calculation test")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies payment calculation")
    @Test(dataProvider = "canCalculatePaymentCase")
    public void canCalculatePayment(int purchasePrice, int downPayment, int amortizationIndex,
                                    int paymentFrequencyIndex, double interest, double paymentsValue) {
        HomePage page = new HomePage();
        MortgagesPage mortgagesPage = page.goToMortgagesPage();
        MortgagePaymentCalculatorPage mortgagePaymentCalculator = mortgagesPage.clickCalculateYourPaymentsBtn();

        mortgagePaymentCalculator.calculatePayments(purchasePrice, downPayment, amortizationIndex,
                paymentFrequencyIndex, interest);

        Assert.assertEquals(mortgagePaymentCalculator
                .getPaymentsValue(), paymentsValue, "Payments value is not correct");
    }

    @Title("Sliders test")
    @Severity(SeverityLevel.MINOR)
    @Description("Verifies 'purchase price' and 'down payment' sliders movement")
    @Test(dataProvider = "canMoveSlidersCases")
    public void canMoveSliders(String sliderPositionBefore, String sliderPositionAfter) {
        HomePage page = new HomePage();
        MortgagesPage mortgagesPage = page.goToMortgagesPage();
        MortgagePaymentCalculatorPage mortgagePaymentCalculator = mortgagesPage.clickCalculateYourPaymentsBtn();

        Assert.assertTrue(mortgagePaymentCalculator.getPurchasePriceSliderStyle()
                .contains(sliderPositionBefore), "Purchase Price slider is not on start position(0)");
        Assert.assertTrue(mortgagePaymentCalculator.getDownPaymentSliderStyle()
                .contains(sliderPositionBefore), "Down Payment slider is not on start position(0)");

        mortgagePaymentCalculator.movePurchasePriceSlider();

        Assert.assertTrue(mortgagePaymentCalculator.getPurchasePriceSliderStyle()
                .contains(sliderPositionAfter), "Purchase Price slider movement works does not work");

        mortgagePaymentCalculator.moveDownPaymentSlider();

        Assert.assertTrue(mortgagePaymentCalculator.getDownPaymentSliderStyle()
                .contains(sliderPositionAfter), "Down Payment slider movement works does not work");
    }

    @AfterMethod
    public void tearDown() {
        DriverHolder.removeDriver();
    }
}
