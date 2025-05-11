package com.clarkewerton.page.ecommerce;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.clarkewerton.driver.DriverManager;
import com.clarkewerton.page.ecommerce.common.CommonPage;

public class CartPage extends CommonPage {
	
    private static final Logger log = LogManager.getLogger(CartPage.class);

	public void deveFinalizarCompra() throws InterruptedException {
        log.info("Iniciando o processo de finalização de compra.");
		DriverManager.getDriver().findElement(By.xpath("//a[@title='View my shopping cart']")).click();
		DriverManager.getDriver().findElement(By.xpath("//p/a[@title='Proceed to checkout']")).click();
		try {
			if (new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(2, 1))
					.until(ExpectedConditions.visibilityOf(DriverManager.getDriver().findElement(By.id("email")))).isDisplayed()) {
						DriverManager.getDriver().findElement(By.id("email")).sendKeys("clarkewerton@hotmail.com");
						DriverManager.getDriver().findElement(By.id("passwd")).sendKeys( "teste123");
						DriverManager.getDriver().findElement(By.id("SubmitLogin")).click();
						DriverManager.getDriver().findElement(By.name("processAddress")).click();
						DriverManager.getDriver().findElement(By.id("cgv")).click();
				DriverManager.getDriver().findElement(By.name("processCarrier")).click();
				DriverManager.getDriver().findElement(By.className("bankwire")).click();
				DriverManager.getDriver().findElement(By.xpath("//p/button")).click();
			}
		} catch (Exception e) {
            log.warn("Falha ao realizar o login. Continuando o processo de finalização de compra.", e);
			DriverManager.getDriver().findElement(By.name("processAddress")).click();
			DriverManager.getDriver().findElement(By.id("cgv")).click();
			DriverManager.getDriver().findElement(By.name("processCarrier")).click();
			DriverManager.getDriver().findElement(By.className("bankwire")).click();
			DriverManager.getDriver().findElement(By.xpath("//p/button")).click();
		}
        log.info("Finalizando o processo de finalização de compra.");
	}

}
