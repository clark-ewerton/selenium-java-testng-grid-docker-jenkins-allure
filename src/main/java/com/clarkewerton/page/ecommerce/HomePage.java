package com.clarkewerton.page.ecommerce;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.clarkewerton.page.ecommerce.common.CommonPage;

import io.qameta.allure.Step;

public class HomePage extends CommonPage {

    private static final Logger log = LogManager.getLogger(HomePage.class);
	static List<WebElement> webEleList;

	@FindBy(xpath = "//div[@class='discount']/p[starts-with(text(),'-')]")
    private List<WebElement> productsWithoutDiscount;

    private By product = By.xpath("./../../../..//a");

    @FindBy(xpath = "//div[@class='discount']/p[not(starts-with(text(),'-'))]")
    private List<WebElement> productsWithDiscount;

    @FindBy(xpath = "//div//ul[@id='homefeatured']//div[@class='right-block']")
    private WebElement elementToInteract;

	@FindBy(xpath = "../..//a[@title='Add to cart']")
    private WebElement addToCartLabel;

	@FindBy(xpath = "//span[@title='Close window']")
    private WebElement closeWindowLabel;

	@FindBy(id = "header_logo")
    private WebElement initialPage;
	
	@Step
	public List<WebElement> verifyProducts(String opcao) {
		switch (opcao) {
		case "withoutDiscount":
			webEleList = this.productsWithoutDiscount;
			for (WebElement element : webEleList) {
				 log.info("Product without discount: " + element.findElement(product).getText());
                 log.info("Discount: " + element.getText());
			}
			break;

		case "discount":
			webEleList = this.productsWithDiscount;
			for (WebElement element : webEleList) {
				 log.info("Product with discount: " + element.findElement(product).getText());
                 log.info("Discount: " + element.getText());
			}
			break;

		default:
			 log.error("Option not available!");
			break;
		}

		return webEleList;

	}

	@Step
	public void addProductToCart() {
		for (WebElement item : webEleList) {
			try {
				if (waitElementToInteract(elementToInteract)) {
					addToCartLabel.click();
					this.closeWindowLabel.click();
				}
			} catch (Exception e) {
				e.printStackTrace();
                log.error("It wasn't possible to locate element", e);
				Assert.fail("It wasn't possible to locate element");
			}

		}

	}

	@Step
	public void deveClicarPaginaInicial() {
		this.initialPage.click();
	}

}
