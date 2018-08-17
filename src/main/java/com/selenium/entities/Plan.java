package com.selenium.entities;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Plan {
    public Plan(Plan plan) {
        this.name = plan.name;
        this.financialYear = plan.financialYear;
        this.actNumber = plan.actNumber;
        this.actApproveDate = plan.actApproveDate;
        this.preliminaryPlan = plan.preliminaryPlan;
        this.actFiles = plan.actFiles;
        this.planApproveDate = plan.planApproveDate;
        this.planType = plan.planType;
        this.planItemsCount = plan.planItemsCount;
        this.itemsApprovedAmount = plan.itemsApprovedAmount;
        this.status = plan.status;
        this.menu = plan.menu;
    }

    public Plan newInstance() {
        return new Plan(this);
    }

    public String name;
    public String financialYear;
    public String actNumber;
    public String actApproveDate;
    public String preliminaryPlan = "false";
    public ArrayList<String> actFiles = new ArrayList<>();
    public String planApproveDate;
    public String planType;
    public String planItemsCount;
    public String itemsApprovedAmount;
    public String status;
    public String menu;
}
