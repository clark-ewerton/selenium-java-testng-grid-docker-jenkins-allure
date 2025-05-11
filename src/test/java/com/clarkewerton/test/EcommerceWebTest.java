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
package com.clarkewerton.test;

import com.clarkewerton.BaseWeb;
import com.clarkewerton.page.ecommerce.HomePage;
import com.clarkewerton.page.ecommerce.AllProductsPage;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EcommerceWebTest extends BaseWeb {

    @Test(description = "Buy products that have discount")
    public void buyProductsThatHaveDiscount() throws InterruptedException {
        var homePage = new HomePage();
        homePage.verifyProducts("discount");

        var allProductsPage = new AllProductsPage();
        allProductsPage.mustFinalizeCheckout();

        //assertThat(detailPage.getAlertMessage())
        //        .isEqualTo("Your reservation has been made and we will contact you shortly");
    }

    @Test(description = "Buy products that don't have discount")
    public void buyProductsThatDontHaveDiscount() throws InterruptedException {
        var homePage = new HomePage();
        homePage.verifyProducts("withoutDiscount");

        var allProductsPage = new AllProductsPage();
        allProductsPage.mustFinalizeCheckout();

        //assertThat(detailPage.getAlertMessage())
        //        .isEqualTo("Your reservation has been made and we will contact you shortly");
    }
}
