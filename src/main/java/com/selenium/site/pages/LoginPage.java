package com.selenium.site.pages;

import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.CheckPageTypes;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.selenium.entities.User;
//import com.selenium.site.sections.LoginForm;

@JPage(url = "/devcon", urlCheckType = CheckPageTypes.CONTAINS)
public class LoginPage extends WebPage {
    //private LoginForm loginForm;

    public void loginAs(User user) {
        //loginForm.loginAs(user);
    }
}
