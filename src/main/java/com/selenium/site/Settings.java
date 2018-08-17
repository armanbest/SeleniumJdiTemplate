package com.selenium.site;

import com.epam.jdi.uitests.web.settings.WebSettings;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;

import static com.epam.commons.PropertyReader.fillAction;
import static com.epam.jdi.uitests.core.settings.JDIPropertiesReader.getProperties;

public class Settings extends WebSettings {
    public static String runType;
    public static String buildNumber;
    public static String keyName;
    public static String keyPassword;
    public static String apiUrl;
    public static String apiKey;
    public static String projectName;
    public static String planName;
    public static String versionName;
    public static String allureReportUrl;
    public static boolean testLinkReport;

    @Step("Load settings")
    public static synchronized void init() throws IOException {
        getProperties(jdiSettingsPath);
        fillAction(p -> runType = p, "run.type");
        fillAction(p -> keyName = p, "key.name");
        fillAction(p -> keyPassword = p, "key.password");
        fillAction(p -> apiUrl = p, "testlink.api.url");
        fillAction(p -> apiKey = p, "testlink.api.key");
        fillAction(p -> projectName = p, "testlink.projectName");
        fillAction(p -> planName = p, "testlink.planName");
        fillAction(p -> versionName = p, "testlink.versionName");
        fillAction(p -> allureReportUrl = p, "allure.report.url");
    }

    public static void setBuildNumber(String number) {
        buildNumber = number;
    }

    public static String getAllureReportUrl() {
        return String.format(allureReportUrl, buildNumber);
    }

    public static void setTestLinkReport(String report) {
        testLinkReport = report.equals("true");
    }

    public static boolean isTestLinkReport() {
        return testLinkReport;
    }
}
