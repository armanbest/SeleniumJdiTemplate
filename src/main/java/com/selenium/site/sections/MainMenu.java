package com.selenium.site.sections;

import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.openqa.selenium.support.FindBy;

public class MainMenu extends WebPage {
    @FindBy(xpath = "//span[@id='online-status']/parent::a")
    private Text username;

    public boolean isLogged(String username) {
        if (this.username.isDisplayed()) {
            return this.username.getText().equals(username);
        }
        return false;
    }
}
