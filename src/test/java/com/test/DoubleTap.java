package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class DoubleTap {

    @Test
    public void ScreenTap() throws MalformedURLException, InterruptedException {

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

        WebElement openMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));
        doubleTap(driver, openMenu);

    }

    //Function for DoubleTap
    private void doubleTap (AndroidDriver driver, WebElement element){

        //Find center of the screen
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Point centerOfElements = getCenterOfElements(location,size);


        //Used our finger
        PointerInput finger1 =new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                //Moved finger to center of the screen
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElements))
                //Pressed on the center of the screen
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                //Waited for 100ms
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                //Lifted up the finger from the screen
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                //Waited for 100ms
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                //Again pressed on the center of the screen
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                //Waited for 100ms
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                //Again lifted up the finger from the screen
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //Completed our action
        driver.perform(Collections.singletonList(sequence));

    }

    private Point getCenterOfElements(Point location, Dimension size) {

        return new Point(location.getX() + size.getWidth()/2, location.getY() + size.getHeight()/2);

    }

}
