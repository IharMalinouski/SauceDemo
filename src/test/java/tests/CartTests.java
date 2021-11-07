package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @Test
    public void addProductToCartTest() {
        loginPage.openPage()
                .login("standard_user", "secret_sauce")
                .addProductToCart("Sauce Labs Fleece Jacket");
        cartPage.openPage();
        Assert.assertEquals(cartPage.getProductPrice("Sauce Labs Fleece Jacket"), "$49.99");
    }

    @Test
    public void priceComparisonInDetailTest() {
        loginPage.openPage()
                .login("standard_user", "secret_sauce")
                .addProductToCart("Sauce Labs Onesie");
        cartPage.openPage();
        Assert.assertEquals(cartPage.getProductPrice("Sauce Labs Onesie"), "$7.99");
    }

    @Test
    public void addingTwoBooksTest() {
        loginPage.openPage()
                .login("standard_user", "secret_sauce")
                .addProductToCart("Sauce Labs Onesie")
                .addProductToCart("Sauce Labs Fleece Jacket");
        cartPage.openPage()
                .deleteProductFromCart("Sauce Labs Fleece Jacket");
        Assert.assertEquals(cartPage.getQuantityIconCart(), "1");
    }

    @Test
    public void productComparisonOnTheDetailsPageTest() {
        loginPage.openPage()
                .login("standard_user", "secret_sauce")
                .clickOnProductImage("Sauce Labs Fleece Jacket")
                .addProductToCartFromDetails("Sauce Labs Fleece Jacket");
        cartPage.openPage();
        Assert.assertEquals(cartPage.getQuantityLabel("Sauce Labs Fleece Jacket"), cartPage.getQuantityIconCart());
    }

    @Test
    public void unsuccessfulAuthorizationTest() {
        loginPage.openPage()
                .waitForElementLocate()
                .errorFieldClickButton();
        Assert.assertEquals("Epic sadface: Username is required", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
        loginPage.getEmptyPasswordField("Ihar")
                .errorFieldClickButton();
        Assert.assertEquals("Epic sadface: Password is required", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
        loginPage.getEmptyloginField("Ihar2")
                .errorFieldClickButton();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
    }

    @Test
    public void addProductToCartThisPageFactoryTest() {
        loginPageFactory.openPage()
                .login("standard_user", "secret_sauce")
                .addProductToCart("Sauce Labs Fleece Jacket");
        cartPage.openPage();
        Assert.assertEquals(cartPage.getProductPrice("Sauce Labs Fleece Jacket"), "$49.99");
    }

    @Test
    public void addInvalidCheckoutStepOneTest() {
        loginPageFactory.openPage()
                .login("standard_user", "secret_sauce")
                .addProductToCart("Sauce Labs Onesie");
        cartPage.openPage()
                .checkoutClickButton()
                .fillInDataEntryForPayment("Ihar", "Malinouski", "123")
                .clickContinueButton();
        Assert.assertEquals("Error: Postal Code is required", checkoutPage.getErrorTextMessage("ERROR_TEXT_EMPTY_FIELD"));
    }
}

