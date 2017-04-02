package de.livingfire.kanboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class KanboardColumn implements KanboardObjectPosition, KanboardObject {

    private String id;

    private String title;

    private String position;

    private String projectId;

    private String taskLimit;

    private String description;

    private String hideInDashboard;

    public KanboardColumn() {
        super();
    }

    @JsonIgnore
    @Override
    public String getObjectName() {
        return getTitle();
    }

    @Override
    public void setObjectName(String objectName) {
        setTitle(objectName);
    }

    @Override
    public String toString() {
        return "Column [id=" + this.id + ", title=" + this.title + ", position=" + this.position + ", projectId="
                + this.projectId + ", taskLimit=" + this.taskLimit + ", description=" + this.description
                + ", hideInDashboard=" + this.hideInDashboard + "]";
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHideInDashboard() {
        return this.hideInDashboard;
    }

    public void setHideInDashboard(String hideInDashboard) {
        this.hideInDashboard = hideInDashboard;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTaskLimit() {
        return this.taskLimit;
    }

    public void setTaskLimit(String taskLimit) {
        this.taskLimit = taskLimit;
    }

}
