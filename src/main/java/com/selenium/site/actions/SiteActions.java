package com.selenium.site.actions;

import com.selenium.entities.User;

import static com.selenium.site.Site.loginPage;
import static com.selenium.site.Site.mainMenu;

public class SiteActions {
    public void loginAs(User user) {
        if (! mainMenu.isLogged(user.getFullName())) {
            loginPage.open();
            loginPage.checkOpened();
            loginPage.loginAs(user);
        }
    }
}
