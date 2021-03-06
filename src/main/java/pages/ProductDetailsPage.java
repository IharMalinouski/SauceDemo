package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

     private static final String ADD_PRODUCT_TO_CART_FROM_DETAILS_BUTTON = "//*[text()='%s']/ancestor::*[@class='inventory_details_desc_container']/*[contains(@class, 'btn_inventory')]";

    @Step("Добавление продукта '{productName}' на странице ProductDetailsPage")
    public ProductDetailsPage addProductToCartFromDetails(String productName) {
        driver.findElement(By.xpath(String.format(ADD_PRODUCT_TO_CART_FROM_DETAILS_BUTTON, productName))).click();
        return this;
    }
}

