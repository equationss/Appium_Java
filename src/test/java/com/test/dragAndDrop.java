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

import javax.xml.transform.Source;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class dragAndDrop {

    @Test
    void draganddrop() throws MalformedURLException {

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
        driver.findElement(AppiumBy.xpath(".//*[@text='Drag and Drop']")).click();

   WebElement source = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
   WebElement target = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_2"));

   Point sourceElementCenter = getCenterOfElements(source.getLocation(), source.getSize());
   Point targetElementCenter = getCenterOfElements(target.getLocation(), target.getSize());


   PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
   Sequence sequence =new Sequence(finger1,1)
           .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourceElementCenter))
           .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
           .addAction(new Pause(finger1, Duration.ofMillis(500)))
           .addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), targetElementCenter))
           .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));



    }
    private Point getCenterOfElements(Point location, Dimension size) {

        return new Point(location.getX() + size.getWidth()/2,
                location.getY() + size.getHeight()/2);

    }
}
