package com.selenium.utils.testlink;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static java.lang.String.format;
import static com.selenium.site.Settings.*;


public class TestLinkHelper {
    private TestLinkAPI api;
    private ReportTCResultResponse result;
    private TestPlan plan;
    private Build build;

    public TestLinkHelper() throws MalformedURLException {
        api = new TestLinkAPI(new URL(apiUrl), apiKey);
        plan = getPlan();
        build = getBuild();
    }

    public TestPlan getPlan() {
        return api.getTestPlanByName(planName, projectName);
    }

    public Build getBuild() {
        return Arrays.stream(api.getBuildsForTestPlan(plan.getId()))
                .filter(build -> build.getName().equals(versionName))
                .findFirst()
                .get();
    }

    public TestCase getTestCase(String methodName) {
        TestCase[] testCases = api.getTestCasesForTestPlan(plan.getId(), null, build.getId(), null, null, null, null, null, null, null, null);
        TestProject testProject = api.getTestProjectByName(projectName);
        return  Arrays.stream(testCases)
                .filter(testCase ->  api.getTestCaseCustomFieldDesignValue(testCase.getId(), null, testCase.getVersion(), testProject.getId(), "java_class", null).getValue().equals(methodName))
                .findFirst()
                .get();
    }

    public void setStatus(String methodFullName, ExecutionStatus status, String notes) {
        TestCase testCase = getTestCase(methodFullName);
        logger.info(format("Set test case '%s' status", testCase.getName()));

        result = api.reportTCResult(testCase.getId(), null, plan.getId(), status, build.getId(),
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
