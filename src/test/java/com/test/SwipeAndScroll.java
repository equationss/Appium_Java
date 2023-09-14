package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class SwipeAndScroll {

    @Test
    void swipeAndScroll() throws MalformedURLException {

        //Device Information
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);//optional
        options.setDeviceName("Test_Device");

        //Path of the APK file to be tested
        options.setApp(System.getProperty("user.dir") + "/Apps/APIDemos.apk");

        //Appium Driver launch URL
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        //App Interaction
        driver.findElement(AppiumBy.xpath(".//*[@text='Views']")).click();

        //Function call
        SwipeAndScroll(driver);
    }


    //Function to scroll or swipe
    private void SwipeAndScroll(AndroidDriver driver)
    {

        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = startX;
        int endY = (int) (size.getHeight() * 0.25);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));

    }

}
