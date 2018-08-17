package com.selenium.site.pages;

import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.CheckPageTypes;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.Alert;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.selenium.site.Settings;
import com.selenium.site.custom.Table;
import com.selenium.site.sections.PlansSearch;
import com.selenium.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@JPage(url = "/plans/index_acts", urlCheckType = CheckPageTypes.CONTAINS)
public class PlansPage extends WebPage {
    // форма создания годового плана
    private CreatePlanPage createPlanPage;

    // таблица планов
    @FindBy(className = "table-gp-items")
    private WebElement plans;

    // кнопка создания плана
    @FindBy(id="btn-create-act")
    private Button createPlanBtn;

    // public PlansSearch search;

    private Alert alert;

    @FindBy(id = "forced_approve_act_btn")
    private Button approveActNotificationBtn;

    // TODO: unstable locator
    @FindBy(xpath = "//div[contains(@class, 'alert-danger')]/a/strong")
    private Text dangerAlert;

    public void showPlanCreationPage() {
        createPlanBtn.click();
    }

    public Table getPlans() {
        setWaitTimeout(0);
        Table table = new Table(plans);
        restoreWaitTimeout();
        return table;
    }

    public void removeFirstProjectPlan() {
        Table plans = getPlans();
        if (plans.getRows().size() == 0) {
            return;
        }
        plans.getFirsRow()
                .findElement(By.xpath(".//button[@type='button']"))
                .click();
        plans.getFirsRow()
                .findElement(By.xpath(".//button[@type='button']/following::ul/li/a[text()='Удалить черновик']"))
                .click();
        alert.ok();
    }

    public void removeCancelFirstProjectPlan() {
        Table plans = getPlans();
        if (plans.getRows().size() == 0) {
            return;
        }
        plans.getFirsRow()
                .findElement(By.xpath(".//button[@type='button']"))
                .click();
        plans.getFirsRow()
                .findElement(By.xpath(".//button[@type='button']/following::ul/li/a[text()='Удалить черновик']"))
                .click();
        alert.cancel();
    }

    public void approveFirstProjectPlan() {
        Table plans = getPlans();
        plans.getFirsRow()
                .findElement(By.xpath(".//button[@type='button']"))
                .click();
        plans.getFirsRow()
                .findElement(By.xpath(".//button[@type='button']/following::ul/li/a[text()='Утвердить']"))
                .click();
        alert.ok();
    }

    public List<WebElement> getFirstProjectPlanActions() {
        Table plans = getPlans();
        plans.getFirsRow()
                .findElement(By.xpath(".//button[@type='button']"))
                .click();
        return plans.getFirsRow()
                .findElements(By.xpath(".//button[@type='button']/following::ul/li/a"));
    }

    public String firstProjectPlanStatus() {
        Table plans = getPlans();
        List<WebElement> cells = plans.getFirsRow().findElements(By.xpath(".//td"));
        return cells.size() > 0 ? cells.get(cells.size() - 2).getText() : "";
    }

    public String firstProjectPlanActNumber() {
        Table plans = getPlans();
        List<WebElement> cells = plans.getFirsRow().findElements(By.xpath(".//td"));
        return cells.size() > 0 ? cells.get(2).getText() : "";
    }

    public void showFirstProjectPlanItemsPage() {
        Table plans = getPlans();
        if (plans.getRows().size() == 0) {
            return;
        }
        plans.getFirsRow()
                .findElement(By.xpath(".//button[@type='button']"))
                .click();
        plans.getFirsRow()
                .findElement(By.xpath(".//button[@type='button']/following::ul/li/a[text()='Просмотреть пункты плана']"))
                .click();
    }

    public void approveActNotification() {
        Utils.waitAjax(getDriver(), Settings.timeouts.getDefaultTimeoutSec(), () -> {
            if (approveActNotificationBtn.isDisplayed()) approveActNotificationBtn.click();
        });
    }

    public boolean isCanNotApprovePlanDangerAlertIsPresent() {
        return dangerAlert.getText().equals("Невозможно утвердить план");
    }

    public void editFirstProjectPlan() {
        Table plans = getPlans();
        if (plans.getRows().size() == 0) {
            return;
        }
        plans.getFirsRow()
                .findElement(By.xpath(".//button[@type='button']"))
                .click();
        plans.getFirsRow()
                .findElement(By.xpath(".//button[@type='button']/following::ul/li/a[text()='Редактировать черновик']"))
                .click();
    }
}
