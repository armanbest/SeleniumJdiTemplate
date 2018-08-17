package com.selenium.utils.testlink;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.ReportTCResultResponse;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static com.selenium.site.Settings.*;


public class TestLinkHelper {
    private TestLinkAPI api;
    private ReportTCResultResponse result;

    public TestLinkHelper() throws MalformedURLException {
        api = new TestLinkAPI(new URL(apiUrl), apiKey);
    }

    public void setStatus(String testCaseName, ExecutionStatus status, String notes) {
        Integer testPlanId = api.getTestPlanByName(planName, projectName).getId();
        Integer buildId = Arrays.stream(api.getBuildsForTestPlan(testPlanId))
                .filter(build -> build.getName().equals(versionName))
                .findFirst()
                .get()
                .getId();
        Integer testCaseId = api.getTestCaseIDByName(testCaseName, null,
                projectName, null);

        result = api.reportTCResult(testCaseId, null, testPlanId, status, buildId,
                versionName, notes, null, null, null,
                "win7/Chrome", null, null);
    }

    public void uploadAttachment(String fileContent) {
        api.uploadExecutionAttachment(
                result.getExecutionId(), //executionId
                "Screenshot", //title
                "", //description
                "screenshot_" + System.currentTimeMillis() + ".jpg", //fileName
                "image/jpeg", //fileType
                fileContent); //content
    }
}
