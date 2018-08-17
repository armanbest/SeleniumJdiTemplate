package com.selenium.enums;

public enum PersonalMenuItems {
    WORK_ROOM("Рабочий кабинет"),
      ANNUAL_PLANS("Годовые планы"),
    PERSONAL_DATA("Личные данные"),
    USER_PROFILE("Профиль участника"),
    EXTERNAL_SERVICES("Внешние сервисы");

    private String value;
    PersonalMenuItems(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
