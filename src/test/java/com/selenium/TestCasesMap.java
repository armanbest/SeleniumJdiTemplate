package com.selenium;

import java.util.HashMap;
import java.util.Map;

class TestCasesMap {
    private Map<String, String> testCases = new HashMap<>();

    public TestCasesMap() {
        testCases.put("createPlan", "Создать проект годового плана");
    }

    public String get(String key) {
        return testCases.get(key);
    }
}
