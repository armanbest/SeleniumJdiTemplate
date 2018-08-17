package com.selenium.data;

import org.testng.annotations.DataProvider;

import java.util.Iterator;

import static com.selenium.app.TestData.plans;


public class ItemData {

    @DataProvider(name = "This and next years Annual Plans")
    public static Iterator<Object[]> getPlans() {
        return plans.get()
                .stream()
                .filter(p -> p.name.equals("thisYear") || p.name.equals("nextYear"))
                .map((g) -> new Object[] {g})
                .iterator();
    }
}
