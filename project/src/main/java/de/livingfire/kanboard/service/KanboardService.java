package de.livingfire.kanboard.service;

import de.livingfire.kanboard.configuration.KanboardApi;
import de.livingfire.kanboard.service.impl.KanboardServiceActionDefault;
import de.livingfire.kanboard.service.impl.KanboardServiceApplicationDefault;
import de.livingfire.kanboard.service.impl.KanboardServiceColumnDefault;
import de.livingfire.kanboard.service.impl.KanboardServiceProjectDefault;
import de.livingfire.kanboard.service.impl.KanboardServiceSwimlaneDefault;
import de.livingfire.kanboard.service.impl.KanboardServiceTaskDefault;
import de.livingfire.kanboard.service.impl.KanboardServiceUserDefault;
import de.livingfire.kanboard.util.KanboardUtilDate;

public class KanboardService {

    private KanboardServiceAction serviceAction;
    private KanboardServiceColumn serviceColumn;
    private KanboardServiceProject serviceProject;
    private KanboardServiceSwimlane serviceSwimlane;
    private KanboardServiceTask serviceTask;
    private KanboardServiceUser serviceUser;
    private KanboardServiceApplication serviceApplication;
    private KanboardUtilDate utilDate;

    public KanboardService() {
        super();
        this.serviceApplication = new KanboardServiceApplicationDefault();
        this.serviceAction = new KanboardServiceActionDefault();
        this.serviceColumn = new KanboardServiceColumnDefault();
        this.serviceProject = new KanboardServiceProjectDefault();
        this.serviceSwimlane = new KanboardServiceSwimlaneDefault();
        this.serviceTask = new KanboardServiceTaskDefault();
        this.serviceUser = new KanboardServiceUserDefault();
    }

    public void setApi(KanboardApi kanboardApi) {
        this.serviceAction.setKanboardApi(kanboardApi);
        this.serviceColumn.setKanboardApi(kanboardApi);
        this.serviceProject.setKanboardApi(kanboardApi);
        this.serviceSwimlane.setKanboardApi(kanboardApi);
        this.serviceTask.setKanboardApi(kanboardApi);
        this.serviceUser.setKanboardApi(kanboardApi);
        this.serviceApplication.setKanboardApi(kanboardApi);

        // hand down for lasy loading KanboardUtilDate
        this.serviceTask.setServiceApplication(this.serviceApplication);
    }

    public void setVerbose(boolean verbose) {
        this.serviceAction.setVerbose(verbose);
        this.serviceColumn.setVerbose(verbose);
        this.serviceProject.setVerbose(verbose);
        this.serviceSwimlane.setVerbose(verbose);
        this.serviceTask.setVerbose(verbose);
        this.serviceUser.setVerbose(verbose);
        this.serviceApplication.setVerbose(verbose);
    }

    public KanboardServiceAction action() {
        return getServiceAction();
    }

    public KanboardServiceColumn column() {
        return getServiceColumn();
    }

    public KanboardServiceProject project() {
        return getServiceProject();
    }

    public KanboardServiceSwimlane swimlane() {
        return getServiceSwimlane();
    }

    public KanboardServiceTask task() {
        return getServiceTask();
    }

    public KanboardServiceUser user() {
        return getServiceUser();
    }

    public KanboardUtilDate date() {
        if (this.utilDate == null) {
            this.utilDate = this.serviceApplication.getUtilDate();
        }
        return this.utilDate;
    }

    public KanboardServiceApplication application() {
        return getServiceApplication();
    }

    public KanboardServiceApplication getServiceApplication() {
        return this.serviceApplication;
    }

    public void setServiceApplication(KanboardServiceApplication serviceApplication) {
        this.serviceApplication = serviceApplication;
    }

    public KanboardServiceUser getServiceUser() {
        return this.serviceUser;
    }

    public void setServiceUser(KanboardServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    public KanboardServiceTask getServiceTask() {
        return this.serviceTask;
    }

    public void setServiceTask(KanboardServiceTask serviceTask) {
        this.serviceTask = serviceTask;
    }

    public KanboardServiceProject getServiceProject() {
        return this.serviceProject;
    }

    public void setServiceProject(KanboardServiceProject serviceProject) {
        this.serviceProject = serviceProject;
    }

    public KanboardServiceAction getServiceAction() {
        return this.serviceAction;
    }

    public void setServiceAction(KanboardServiceAction serviceAction) {
        this.serviceAction = serviceAction;
    }

    public KanboardServiceColumn getServiceColumn() {
        return this.serviceColumn;
    }

    public void setServiceColumn(KanboardServiceColumn serviceColumn) {
        this.serviceColumn = serviceColumn;
    }

    public KanboardServiceSwimlane getServiceSwimlane() {
        return this.serviceSwimlane;
    }

    public void setServiceSwimlane(KanboardServiceSwimlane serviceSwimlane) {
        this.serviceSwimlane = serviceSwimlane;
    }

}
