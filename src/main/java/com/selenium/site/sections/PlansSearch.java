package com.selenium.site.sections;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.selenium.enums.PlanStatuses;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PlansSearch extends WebPage {
    @FindBy(xpath = "//form[@id='form-filter']//button[@type='findBtn']")
    private Button findBtn;

    public void findAction(String year, PlanStatuses status) {
        Select financialYear = new Select(getDriver().findElement(By.name("filterp[year]")));
        Select statuss = new Select(getDriver().findElement(By.name("filterp[status]")));
        if (! financialYear.getFirstSelectedOption().getText().equals(year)
                || ! statuss.getFirstSelectedOption().getText().equals(status.toString())) {
            financialYear.selectByVisibleText(year);
            statuss.selectByVisibleText(status.toString());
            findBtn.click();
        }
    }

    public void findAction(String year) {
        Select financialYear = new Select(getDriver().findElement(By.name("filterp[year]")));
        if (! financialYear.getFirstSelectedOption().getText().equals(year)) {
            financialYear.selectByVisibleText(year);
            findBtn.click();
        }
    }

    public void findAction(PlanStatuses status) {
        Select statuss = new Select(getDriver().findElement(By.name("filterp[status]")));
        if (! statuss.getFirstSelectedOption().getText().equals(status.toString())) {
            statuss.selectByVisibleText(status.toString());
            findBtn.click();
        }
    }
}
