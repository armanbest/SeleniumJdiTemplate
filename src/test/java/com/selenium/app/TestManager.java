package com.selenium.app;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.selenium.site.Site;
import com.selenium.site.Settings;
import com.selenium.site.actions.SiteActions;
import com.selenium.utils.sikulix.SikuliHelper;
import com.selenium.utils.testlink.TestLinkHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

import static com.selenium.site.Site.sikuliHelper;
import static com.selenium.site.Site.testLinkHelper;

// TODO: основательно продумать инициализацию тестов
public class TestManager {

    public static SiteActions siteActions = new SiteActions();

    public static synchronized void init() throws IOException {

        WebSite.init(Site.class);

        Settings.init();
        Settings.setBuildNumber(System.getProperty("buildNumber", ""));
        Settings.setTestLinkReport(System.getProperty("testLinkReport", "false"));

        // В зависимости от того где выполняется инициализируем sikuli
        if (Settings.runType.equals("local")) {
            sikuliHelper = new SikuliHelper();
        } else {
            sikuliHelper = new SikuliHelper((RemoteWebDriver) Settings.getDriverFactory().getDriver());
        }

        testLinkHelper = new TestLinkHelper();
        TestData.init();
    }

    public static synchronized byte[] takeScreenshot() {
        return ((TakesScreenshot) Settings.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
