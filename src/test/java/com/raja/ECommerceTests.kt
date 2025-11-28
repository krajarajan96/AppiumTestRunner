package com.raja

import io.appium.java_client.AppiumBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.Test
import java.time.Duration


class ECommerceTests: BaseTest() {

    @Test
    fun testPersonalInfoScreen() {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).click()
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"))
            .sendKeys("Rajarajan K")
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale"))
            .click()
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry"))
            .click()

        scrollToElement("Argentina")
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Argentina\"]"))
            .click()
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"))
            .click()
    }

    @Test
    fun testEnterNameToast() {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"))
            .click()

        val toastLocator = AppiumBy.xpath("//android.widget.Toast[@text='Please enter your name']")
        val waitForToast = WebDriverWait(driver, Duration.ofMillis(10))
        waitForToast.until(ExpectedConditions.presenceOfElementLocated(toastLocator))

        val toast = driver.findElement(toastLocator)
        Assert.assertTrue(toast.isDisplayed)
        val toastMsg = toast.getAttribute("name")
        Assert.assertEquals(toastMsg, "Please enter your name")
    }

    @Test
    fun testAddToCart() {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).click()
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"))
            .sendKeys("Rajarajan K")
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale"))
            .click()
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry"))
            .click()

        scrollToElement("Argentina")
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Argentina\"]"))
            .click()
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"))
            .click()

        scrollToElement("Jordan 6 Rings")
        val elements = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName"))
        for (i in 0 until elements.size) {
            if (elements[i].getAttribute("text") == "Jordan 6 Rings") {
                val addToCart = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productAddCart"))
                addToCart.click()
                Assert.assertEquals(
                    addToCart.text,
                    "ADDED TO CART"
                )
                break
            }
        }

        val cartBtn = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart"))
        cartBtn.click()
        val wait = WebDriverWait(driver, Duration.ofSeconds(2))
        wait.until(
            ExpectedConditions.attributeContains(
                driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),
                "text",
                "Cart"
            )
        )

        val element = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName"))
        Assert.assertEquals(element.text, "Jordan 6 Rings")
    }

    @Test
    fun testCartTotal() {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).click()
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"))
            .sendKeys("Rajarajan K")
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale"))
            .click()
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry"))
            .click()

        scrollToElement("Argentina")
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Argentina\"]"))
            .click()
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"))
            .click()


        val addToCarts = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart"))
        addToCarts[0].click()
        addToCarts[1].click()

        val cartBtn = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart"))
        cartBtn.click()

        val wait = WebDriverWait(driver, Duration.ofSeconds(2))
        wait.until(
            ExpectedConditions.attributeContains(
                driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),
                "text",
                "Cart"
            )
        )

        val amount = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl"))
        Assert.assertEquals(amount.text, "\$ 280.97")
    }
}