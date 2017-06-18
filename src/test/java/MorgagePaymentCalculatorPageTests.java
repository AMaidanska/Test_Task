import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MortgagePaymentCalculatorPage;
import pages.MortgagesPage;
import utils.DriverHolder;

public class MorgagePaymentCalculatorPageTests {

    @DataProvider
    public Object[][] canCalculatePaymentCase() {
        return new Object[][]{{500000, 100000, 0, 3, 5, 726.35}};
    }

    @DataProvider
    public Object[][] canMoveSlidersCases() {
        return new Object[][]{{"left: 0", "left: 100"}};
    }

    @Test(dataProvider = "canCalculatePaymentCase")
    public void canCalculatePayment(int purchasePrice, int downPayment, int amortizationIndex,
                                    int paymentFrequencyIndex, double interest, double paymentsValue) {
        HomePage page = new HomePage();
        MortgagesPage mortgagesPage = page.goToMortgagesPage();
        MortgagePaymentCalculatorPage mortgagePaymentCalculator = mortgagesPage.clickCalculateYourPaynentsBtn();

        mortgagePaymentCalculator.calculatePayments(purchasePrice, downPayment, amortizationIndex,
                paymentFrequencyIndex, interest);

        Assert.assertEquals(mortgagePaymentCalculator
                .getPaymentsValue(), paymentsValue, "Payments value is not correct");
    }

    @Test(dataProvider = "canMoveSlidersCases")
    public void canMoveSliders(String sliderPositionBefore, String sliderPositionAfter) {
        HomePage page = new HomePage();
        MortgagesPage mortgagesPage = page.goToMortgagesPage();
        MortgagePaymentCalculatorPage mortgagePaymentCalculator = mortgagesPage.clickCalculateYourPaynentsBtn();

        Assert.assertTrue(mortgagePaymentCalculator.getPurchasepriseSliderStyle()
                .contains(sliderPositionBefore), "Purchase Slider is not on start position(0)");

        mortgagePaymentCalculator.movePurchasePriceSlider();

        Assert.assertTrue(mortgagePaymentCalculator.getPurchasepriseSliderStyle()
                .contains(sliderPositionAfter), " Purchase Price Slider movement works does not work");
    }

    @AfterMethod
    public void tearDown() {
        DriverHolder.removeDriver();
    }
}
