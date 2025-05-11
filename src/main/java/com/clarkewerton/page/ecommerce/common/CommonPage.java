/*
 * MIT License
 *
 * Copyright (c) 2018 Elias Nogueira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.clarkewerton.page.ecommerce.common;

import com.clarkewerton.driver.DriverManager;
import com.clarkewerton.page.AbstractPageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonPage extends AbstractPageObject {

	private static final Logger log = LogManager.getLogger(CommonPage.class);
	protected static WebDriverWait wait;
	
	public Boolean waitElementToInteract(WebElement locator) {
        log.info("Waiting for element: " + locator);
		Boolean elementExists = false;
		Duration tempo = Duration.ofSeconds(10, 10);
		wait = new WebDriverWait(DriverManager.getDriver(), tempo);
		// wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if (wait.until(ExpectedConditions.elementToBeClickable(locator)).isDisplayed()) {
			elementExists = true;
		}

		return elementExists;

	}
	
	public void clickElementViaJavascript(WebElement element){
        log.info("Clicking on element via JavaScript: " + element);
		JavascriptExecutor executor = (JavascriptExecutor)DriverManager.getDriver();
		executor.executeScript("arguments[0].click();", element);
	}
}
