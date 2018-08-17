package com.selenium.site.custom;

import com.selenium.site.Settings;
import com.selenium.utils.sikulix.ElementNotFoundException;
import com.selenium.utils.sikulix.TextBox;
import ru.yandex.qatools.allure.annotations.Step;

import static com.selenium.site.Site.sikuliHelper;

// TODO данный класс не должен быть статическим, нужно переписать. или вообше перенести в jdi
public class UploadFileDialog {
    private static TextBox filePathTextBox;

    public static void upload(String filePath) {
        focusToFilePath();
        submitFile(filePath);
        approveFile();
    }

    private static void focusToFilePath() {
        filePathTextBox = sikuliHelper.findTextBox("uploadFileName.png");
        filePathTextBox.click();
    }

    @Step("Upload file")
    private static void submitFile(String fileName) {
        filePathTextBox.write(sikuliHelper.getRemoteFilePath() + fileName);
        sikuliHelper.findTextBox("submitFileButton.png").click();
    }

    @Step("Approve file")
    private static void approveFile() {
        try {
            sikuliHelper.find("selectKey.png").click();
        } catch (ElementNotFoundException e) {
            sikuliHelper.find("selectNewKey.png").click();
            focusToFilePath();
            submitFile(Settings.keyName);
        }

        TextBox passwordInput = sikuliHelper.findTextBox("keyPassword.png");
        passwordInput.click();
        passwordInput.write(Settings.keyPassword);
        sikuliHelper.find("submitKey.png").click();
    }
}
