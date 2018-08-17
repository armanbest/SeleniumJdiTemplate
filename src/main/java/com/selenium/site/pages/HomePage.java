package com.selenium.site.pages;

import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.CheckPageTypes;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;

@JPage(url = "/cabinet/profile", urlCheckType = CheckPageTypes.CONTAINS)
public class HomePage extends WebPage {
}
