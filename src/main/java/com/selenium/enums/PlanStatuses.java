package com.selenium.enums;

public enum PlanStatuses {
    PROJECT("Проект"),
    APPROVED("Утвержден"),
    UPDATED("Изменен"),
    CHECK_FLK("Проверка ФЛК");

    private String status;

    PlanStatuses(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
