package com.selenium.site.sections;

import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.selenium.enums.PersonalMenuItems;
import org.openqa.selenium.support.FindBy;

public class PersonalMenu extends WebPage {
    @FindBy(css = "div#cabinet_nav>ul>li a")
    private Menu<PersonalMenuItems> personalMenu;

    public void goToAnnualPlansPage() {
        personalMenu.clickOn(PersonalMenuItems.WORK_ROOM);
        personalMenu.clickOn(PersonalMenuItems.ANNUAL_PLANS);
    }
}
