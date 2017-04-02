package de.livingfire.kanboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class KanboardSwimlane implements KanboardObjectPosition {

    private String id;

    private String projectId;

    private String name;

    private String description;

    private String position;

    private String isActive;

    public KanboardSwimlane() {
        super();
    }

    @JsonIgnore
    @Override
    public String getObjectName() {
        return getName();
    }

    @Override
    public void setObjectName(String objectName) {
        setName(objectName);
    }

    @Override
    public String toString() {
        return "KanboardSwimlane [id=" + this.id + ", projectId=" + this.projectId + ", name=" + this.name
                + ", description=" + this.description + ", position=" + this.position + ", isActive=" + this.isActive
                + "]";
    }

    @Override
    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
