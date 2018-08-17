package com.selenium.app;

import com.google.gson.reflect.TypeToken;
import com.selenium.entities.Plans;
import com.selenium.entities.Users;
import com.selenium.helpers.LoadData;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;

public class TestData {
    public static Users users;
    public static Plans plans;

    @Step("Load test data")
    static void init() throws IOException {
        loadUsers();
        loadAnnualPlans();
    }

    private static void loadUsers() throws IOException {
        users = new Users(
                LoadData.ofJson("src/test/resources/users.json", new TypeToken<Users>(){}.getType()));
    }

    private static void loadAnnualPlans() throws IOException {
        plans = new Plans(
                LoadData.ofJson("src/test/resources/plans.json", new TypeToken<Plans>(){}.getType()));
    }
}
