package com.selenium.site.sections;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.CheckBox;
import com.epam.jdi.uitests.web.selenium.elements.common.DatePicker;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import com.selenium.entities.Plan;
import com.selenium.site.custom.UploadFileDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static java.lang.String.format;

public class PlanForm extends Form<Plan> {
    @FindBy(name="act_number")
    private TextField actNumber;

    @FindBy(name="act_date")
    private DatePicker actApproveDate;

    @FindBy(name="preliminary_plan")
    private CheckBox preliminaryPlan;

    @FindBy(name = "sign_upload")
    private Button uploadFile;

    @FindBy(className = "add-file-block")
    private Button addFile;

    private UploadFileDialog uploadFileDialog;

    @FindBy(id = "save_button")
    private Button save;

    @FindBy(xpath = "//button[contains(., 'Назад')]")
    private Button back;

    private Function<? super WebDriver, Object> isFilesUploaded = (ExpectedCondition<Object>) webDriver -> {
            assert webDriver != null;
            boolean result = false;
            try {
                result = (boolean) ((JavascriptExecutor) webDriver).executeScript("return form_sign_helper.check_statuses()");
            } catch (NullPointerException ex) {}
            return result;
    };

    @Override
    @Step("Fill Annual Plan")
    public void fill(Plan plan) {
        WebElement financialYear = getDriver().findElement(By.id("year"));
        if (financialYear.isEnabled()) {
            new Select(financialYear).selectByVisibleText(plan.financialYear);
        }
        actNumber.newInput(plan.actNumber);
        if (! plan.actApproveDate.equals("")) {
            actApproveDate.newInput(plan.actApproveDate);
        }
        preliminaryPlan.setValue(plan.preliminaryPlan);

        List<WebElement> files = getDriver().findElements(By.className("remove-uploaded-file"));
        files.stream()
                .filter(WebElement::isDisplayed)
                .forEach(WebElement::click);

        Iterator<String> file = plan.getActFiles().iterator();
        if (file.hasNext()) {
            uploadFile.click();
            UploadFileDialog.upload(file.next());

            while (file.hasNext()) {
                addFile.click();
                UploadFileDialog.upload(file.next());
            }
        }
    }

    @Step("Checking Files uploaded state")
    public void checkUploadFiles() {
        logger.step(format("I check '%s' files is uploaded", getName()));
        (new WebDriverWait(getDriver(), 30)).until(isFilesUploaded);
    }

    @Step("Save Annual Plan")
    public void save() {
        save.click();
    }

    @Step("Go to Annual Plans (back button)")
    public void goToBack() {
        back.click();
    }
}
