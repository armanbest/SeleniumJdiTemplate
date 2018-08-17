package com.selenium.site.pages;

import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.CheckPageTypes;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.selenium.entities.Plan;
import com.selenium.site.sections.PlanForm;
import org.openqa.selenium.support.FindBy;

@JPage(url = "/plans/edit_act", urlCheckType = CheckPageTypes.CONTAINS)
public class CreatePlanPage extends WebPage {
    private PlanForm planForm;

    @FindBy(xpath = "//div[contains(@class, 'alert-danger')]")
    private Text dangerAlert;

    public void createPlan(Plan plan) {
        planForm.fill(plan);
    }

    public void checkUploadFiles() {
        planForm.checkUploadFiles();
    }

    public void save() {
        planForm.save();
    }

    @Override
    public void back() {
        planForm.goToBack();
    }

    public boolean isCanNotSeveralDangerAlertIsPresent() {
        return dangerAlert.getText().equals("Нельзя создать более одного черновика на один год");
    }

    public boolean isCanNotActNumberEmptyDangerAlertIsPresent() {
        return dangerAlert.getText().equals("Поле \"Номер акта\" обязательно для заполнения");
    }

    public boolean isCanNotActDateEmptyDangerAlertIsPresent() {
        return dangerAlert.getText().equals("Поле \"Дата утверждения акта\" обязательно для заполнения");
    }

}
