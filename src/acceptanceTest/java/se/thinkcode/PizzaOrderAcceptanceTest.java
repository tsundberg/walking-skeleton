package se.thinkcode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PizzaOrderAcceptanceTest {
    private WebDriver browser;
    private WebDriverWait wait;

    @Before
    public void setUp() throws Exception {
        // ApplicationRunner.runApplication();
        browser = new FirefoxDriver();
        wait = new WebDriverWait(browser, 20);
    }

    @After
    public void tearDown() {
        browser.quit();
    }

    @Test
    public void order_a_pizza() throws Exception {
        String phoneNumber = "+46 70 767 33 25";
        String pizzaDescription = "A nice pizza with lots of cheese";

        goToOrderForm();
        fillOutOrderForm(phoneNumber, pizzaDescription);
        submitOrder();
        String actualPizzaDescription = getOrderConfirmation();

        assertThat(actualPizzaDescription, is(pizzaDescription));
    }

    private void goToOrderForm() {
        browser.get("http://localhost:8080");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneNumber")));
    }

    private void fillOutOrderForm(String phoneNumber, String pizzaDescription) {
        browser.findElement(By.name("phoneNumber")).sendKeys(phoneNumber);
        browser.findElement(By.name("pizzaDescription")).sendKeys(pizzaDescription);
    }

    private void submitOrder() {
        browser.findElement(By.name("pizzaDescription")).submit();
    }

    private String getOrderConfirmation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderItem")));
        return browser.findElement(By.id("orderItem")).getText();
    }
}
