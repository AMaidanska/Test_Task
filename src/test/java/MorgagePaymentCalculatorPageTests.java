
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MortgagePaymentCalculatorPage;
import pages.MortgagesPage;
import utils.DriverHolder;

public class MorgagePaymentCalculatorPageTests{

    @BeforeMethod
    public void setUp(){
        WebDriver driver = DriverHolder.getDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void canCalculatePayment() throws InterruptedException {
        HomePage page = new HomePage("https://ia.ca");
        MortgagesPage mortgagesPage = page.goToMortgagesPage();
        MortgagePaymentCalculatorPage mortgagePaymentCalculator = mortgagesPage.clickCalculateYourPaynentsBtn();
        mortgagePaymentCalculator.addPurchasePrice(500000);
        mortgagePaymentCalculator.addDownPayment(100000);
        mortgagePaymentCalculator.selectArmotization(0);
        mortgagePaymentCalculator.selectPaymentFrequency(3);
        mortgagePaymentCalculator.setInterestRate(5);
        mortgagePaymentCalculator.calculate();
        Assert.assertEquals(mortgagePaymentCalculator.getPaymentsValue(), 726.35, "Payments value is not correct");
    }

    @Test
    public void canMoveSliders() throws InterruptedException {
        HomePage page = new HomePage("https://ia.ca");
        MortgagesPage mortgagesPage = page.goToMortgagesPage();
        MortgagePaymentCalculatorPage mortgagePaymentCalculator = mortgagesPage.clickCalculateYourPaynentsBtn();
        mortgagePaymentCalculator.movePurchasePriceSlider();
        Assert.assertTrue(mortgagePaymentCalculator.getPurchasepriseSliderStyle().contains("left: 100"), " Purchase Price Slider movement works does not work");
    }

    @AfterMethod
    public  void tearDown(){
        DriverHolder.removeDriver();

    }
}
