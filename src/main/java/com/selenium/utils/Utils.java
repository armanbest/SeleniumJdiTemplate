package com.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class Utils {
    @FunctionalInterface
    public interface RunnableException {
        void run() throws Throwable;
    }

    public interface AfterAjax {
        void run();
    }

    public static void ignoreException(RunnableException r) {
        try { r.run(); } catch (Throwable t) { }
    }

    public static void waitAjax(WebDriver webdriver, int timeout, AfterAjax after) {
        WebDriverWait wait = new WebDriverWait(webdriver, timeout);
        wait.until(driver -> (boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active == 0"));
        after.run();
    }
}
