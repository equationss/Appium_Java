package com.test;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class AndroidTest {

    @Test
    public void AndroidLaunch() throws MalformedURLException, InterruptedException {

        //Device Information
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //Optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2); //Optional
        options.setDeviceName("Test_Device");

        //Path of the APK file to be tested
        options.setApp(System.getProperty("user.dir") + "/Apps/Android-MyDemoAppRN.1.3.0.build-244.apk");

        //Appium Driver launch URL
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723") , options);

        //Implicit Wait
        Thread.sleep(4000);


        //Menu Button
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();

        //Explicit Wait
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(e->e.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]")));
        driver.findElement((By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]"))).click();

        //Implicit Wait
        Thread.sleep(5000);

        //Login Module
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("bob@example.com");
        driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("10203040");
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();

        //Implicit wait
        Thread.sleep(5000);

        //Validate
        WebElement result= (WebElement) driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"container header\"]/android.widget.TextView"));
        Assertions.assertEquals(result.getText(), "Products");

    }



}
