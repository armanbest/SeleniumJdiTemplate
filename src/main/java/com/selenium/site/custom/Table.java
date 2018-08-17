package com.selenium.site.custom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Table {
    private List<WebElement> rows;

    public Table(WebElement table) {
        this.rows = table.findElements(By.xpath(".//tbody/tr"));
    }

    public List<WebElement> getRows() {
        return rows;
    }

    public WebElement getRow(int rowNum) {
        return rows.get(rowNum);
    }

    public WebElement getFirsRow() {
        return rows.size() > 0 ? rows.get(0) : null;
    }
}
