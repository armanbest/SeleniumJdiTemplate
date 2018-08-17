package com.selenium.site.actions;

import com.selenium.entities.Plan;
import com.selenium.enums.PlanStatuses;

import static com.selenium.enums.PlanStatuses.APPROVED;
import static com.selenium.enums.PlanStatuses.PROJECT;
import static com.selenium.enums.PlanStatuses.UPDATED;
import static com.selenium.site.Site.plansPage;
import static com.selenium.site.Site.createPlanPage;
import static com.selenium.site.Site.personalMenu;

public class PlanActions {

    private Plan plan;

    public PlanActions(Plan plan) {
        this.plan = plan;
    }

    public void open() {
    	/*
    	1) проверить открыта ли страница со списком планов
    	2) если не открыта, то открыть страницу
    	*/
        if (! isOpened()) {
            personalMenu.goToAnnualPlansPage();
        }
    }

    public void create() {
    	this.open();
        /*
        Создание плана
        1) открыть страницу со списком планов
        2) нажать кнопку создать план
        3) заполнить форму createPlanPage.create(plan)
        */
    }

    public void create(Plan plan) {
        this.plan = plan;
        create();
    }

    /*public void createOrReplace() {
        if (checkCreated()) {
            remove();
        }
        create();
    }*/

    public void createIfNotExist() { }

    public void edit() { }

    public void remove() { }

    public void removeCancel() { }

    public void approve() { }

    public void save() { }

    public void cancel() { }

    public void showItems() { }

    public boolean isOpened() {
        return plansPage.verifyOpened();
    }

    //public boolean isPlansEmpty() { }

    public boolean isCanNotSeveralMessagePresent() {
        return createPlanPage.isCanNotSeveralDangerAlertIsPresent();
    }

    public boolean isCanNotActNumberEmptyMessagePresent() {
        return createPlanPage.isCanNotActNumberEmptyDangerAlertIsPresent();
    }

    public boolean isCanNotActDateEmptyMessagePresent() {
        return createPlanPage.isCanNotActDateEmptyDangerAlertIsPresent();
    }

    public boolean isCanNotApprovedPlanMessagePresent() {
        return plansPage.isCanNotApprovePlanDangerAlertIsPresent();
    }

    //public boolean checkCreated() { }

    //public Boolean checkEdited() { }

    //public boolean checkNotCreated() { }

    //public boolean checkApproved() { }

    // public boolean checkRemoved() { }

    //public boolean checkIsCanRemove(PlanStatuses status) { }

    // public boolean checkIsCanRemoveApproved() { }

    // public boolean checkIsCanRemoveUpdated() { }
}
