package com.clarkewerton.page.ecommerce;

import com.clarkewerton.driver.DriverManager;
import io.qameta.allure.Step;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class AllProductsPage extends HomePage {

    private static final Logger log = LogManager.getLogger(AllProductsPage.class);

		@FindBy(css = "span[data-buy-now-text]")
    private WebElement buyNowText;

	private By product = By.xpath("./../../../..//a");

	@Step
	public void mustAccessAllProductsPage(){
        log.info("Acessing ecommerce page: https://adasdsacarrinhomagico.mycartpanda.com/collections/all");
		DriverManager.getDriver().get("https://adasdsacarrinhomagico.mycartpanda.com/collections/all");
	}

	@Step
	public void mustFinalizeCheckout() throws InterruptedException {
		Random rand = new Random();
		int index = rand.nextInt(this.webEleList.size());
		WebElement randomElement = this.webEleList.get(index);
        log.info("Clicking on a random product: " + randomElement.findElement(product).getText());
		randomElement.findElement(product).click();
		waitElementToInteract(this.buyNowText);
		clickElementViaJavascript(this.buyNowText);
		Thread.sleep(10000);
	}
}
