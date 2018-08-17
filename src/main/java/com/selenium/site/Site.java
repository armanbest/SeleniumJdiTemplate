package com.selenium.site;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import com.selenium.site.pages.*;
import com.selenium.site.sections.MainMenu;
import com.selenium.site.sections.PersonalMenu;
import com.selenium.utils.sikulix.SikuliHelper;
import com.selenium.utils.testlink.TestLinkHelper;

@JSite("http://example-site.com/")
public class Site extends WebSite {
    public static Settings settings;
    public static SikuliHelper sikuliHelper;
    public static TestLinkHelper testLinkHelper;

    public static PersonalMenu personalMenu;
    public static MainMenu mainMenu;

    public static HomePage homePage;
    public static LoginPage loginPage;

    public static PlansPage plansPage;
    public static CreatePlanPage createPlanPage;
}
