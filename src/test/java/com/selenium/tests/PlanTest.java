package com.selenium.tests;

import com.epam.web.matcher.testng.Assert;

import com.selenium.TestsInit;
import com.selenium.entities.Plan;
import com.selenium.site.actions.PlanActions;
import org.testng.annotations.Test;

import static com.selenium.app.TestData.plans;
import static com.selenium.app.TestData.users;
import static com.selenium.app.TestManager.siteActions;

public class PlanTest extends TestsInit {

    @Test(description = "Создание проекта годового плана")
    public void createPlan() {
        siteActions.loginAs(users.getUser("customer1"));

        PlanActions planActions = new PlanActions(plans.getAnnualPlan("thisYear"));
        planActions.open();
        //planActions.createOrReplace();
        planActions.save();
        //Assert.isTrue(planActions.checkCreated());
        planActions.remove();
    }
}
