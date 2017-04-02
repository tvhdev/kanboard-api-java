package de.livingfire.kanboard.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class KanboardProject implements KanboardObject {

    private String id;

    private String name;

    private String description;

    private String identifier;

    private String isActive;

    private String ownerId;

    private ArrayList<KanboardSwimlane> swimlanes;

    private ArrayList<KanboardColumn> columns;

    private List<KanboardTask> tasks;

    private List<KanboardAction> actions;

    public KanboardProject() {
        super();
        this.swimlanes = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.actions = new ArrayList<>();
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
        return "KanboardProject [id=" + this.id + ", name=" + this.name + ", description=" + this.description
                + ", identifier=" + this.identifier + ", isActive=" + this.isActive + ", ownerId=" + this.ownerId
                + ", swimlanes=" + this.swimlanes + ", columns=" + this.columns + ", tasks=" + this.tasks + ", actions="
                + this.actions + "]";
    }

    public List<KanboardAction> getActions() {
        return this.actions;
    }

    public void setActions(List<KanboardAction> actions) {
        this.actions = actions;
    }

    public List<KanboardTask> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<KanboardTask> tasks) {
        this.tasks = tasks;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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

    public ArrayList<KanboardSwimlane> getSwimlanes() {
        return this.swimlanes;
    }

    public void setSwimlanes(ArrayList<KanboardSwimlane> swimlanes) {
        this.swimlanes = swimlanes;
    }

    public ArrayList<KanboardColumn> getColumns() {
        return this.columns;
    }

    public void setColumns(ArrayList<KanboardColumn> columns) {
        this.columns = columns;
    }

}
