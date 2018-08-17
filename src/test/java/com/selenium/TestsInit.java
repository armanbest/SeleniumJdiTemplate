package com.selenium;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.selenium.app.TestManager;
import com.selenium.site.Settings;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.*;

import static br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus.BLOCKED;
import static br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus.FAILED;
import static br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus.PASSED;
import static java.lang.String.format;
import static com.selenium.site.Site.testLinkHelper;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;

@Listeners(TestListener.class)
public class TestsInit extends TestNGBase {
    private TestCasesMap testCases = new TestCasesMap();

    @BeforeSuite(alwaysRun = true, description = "Init tests")
    public void setUp(ITestContext context) throws IOException {
        TestManager.init();
        context.setAttribute("app", TestManager.class);
    }

    @AfterSuite(alwaysRun = true, description = "Free resources")
    public void quit() {
        Settings.getDriver().quit();
    }

    @AfterMethod(alwaysRun = true, description = "Save report to TestLink")
    public void tearDown(ITestResult result) {
        if (! Settings.isTestLinkReport()) {
            return;
        }

        String notes = Settings.getAllureReportUrl();

        if (result.getThrowable() != null) {
            notes += "\n" + ExceptionUtils.getStackTrace(result.getThrowable());
            result.setThrowable(null);
        }

        ExecutionStatus status;
        switch (result.getStatus()) {
            case ITestResult.FAILURE: status = FAILED;
                break;
            case ITestResult.SKIP: status = BLOCKED;
                break;
            case ITestResult.SUCCESS: status = PASSED;
                break;
            default:
                throw new RuntimeException("Invalid status");
        }

        logger.info(format("Set test case '%s' status", testCases.get(result.getName())));

        testLinkHelper.setStatus(testCases.get(result.getName()), status, notes);
        if (! status.equals(PASSED)) {
            testLinkHelper.uploadAttachment(((TakesScreenshot) Settings.getDriver()).getScreenshotAs(OutputType.BASE64));
        }
    }
}
