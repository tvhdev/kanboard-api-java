package de.livingfire.kanboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class KanboardTask implements KanboardObject {

    private String active;
    private String categoryId;
    private String colorId;
    private String columnId;
    private String creatorId;
    private String dateCompleted;
    private String dateCreation;
    private String dateDue;
    private String dateModification;
    private String dateMoved;
    private String dateStarted;
    private String description;
    private String id;
    private String ownerId;
    private String position;
    private String priority;
    private String projectId;
    private String recurrenceBaseDate;
    private String recurrenceChild;
    private String recurrenceFactor;
    private String recurrenceParent;
    private String recurrenceStatus;
    private String recurrenceTimeFrame;
    private String recurrenceTrigger;
    private String reference;
    private String score;
    private String swimlaneId;
    private String timeEstimated;
    private String timeSpent;
    private String title;
    private String url;

    public KanboardTask() {
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

    public KanboardTask(String projectId,
                        String title) {
        super();
        this.projectId = projectId;
        this.title = title;
    }

    public KanboardTask(KanboardProject project,
                        String title) {
        this(project.getId(), title);
    }

    @Override
    public String toString() {
        return "KanboardTask [active=" + this.active + ", categoryId=" + this.categoryId + ", colorId=" + this.colorId
                + ", columnId=" + this.columnId + ", creatorId=" + this.creatorId + ", dateCompleted="
                + this.dateCompleted + ", dateCreation=" + this.dateCreation + ", dateDue=" + this.dateDue
                + ", dateModification=" + this.dateModification + ", dateMoved=" + this.dateMoved + ", dateStarted="
                + this.dateStarted + ", description=" + this.description + ", id=" + this.id + ", ownerId="
                + this.ownerId + ", position=" + this.position + ", priority=" + this.priority + ", projectId="
                + this.projectId + ", recurrenceBaseDate=" + this.recurrenceBaseDate + ", recurrenceChild="
                + this.recurrenceChild + ", recurrenceFactor=" + this.recurrenceFactor + ", recurrenceParent="
                + this.recurrenceParent + ", recurrenceStatus=" + this.recurrenceStatus + ", recurrenceTimeFrame="
                + this.recurrenceTimeFrame + ", recurrenceTrigger=" + this.recurrenceTrigger + ", reference="
                + this.reference + ", score=" + this.score + ", swimlaneId=" + this.swimlaneId + ", timeEstimated="
                + this.timeEstimated + ", timeSpent=" + this.timeSpent + ", title=" + this.title + ", url=" + this.url
                + "]";
    }

    public String getRecurrenceChild() {
        return this.recurrenceChild;
    }

    public void setRecurrenceChild(String recurrenceChild) {
        this.recurrenceChild = recurrenceChild;
    }

    public String getRecurrenceParent() {
        return this.recurrenceParent;
    }

    public void setRecurrenceParent(String recurrenceParent) {
        this.recurrenceParent = recurrenceParent;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDateStarted() {
        return this.dateStarted;
    }

    public void setDateStarted(String dateStarted) {
        this.dateStarted = dateStarted;
    }

    public String getDateMoved() {
        return this.dateMoved;
    }

    public void setDateMoved(String dateMoved) {
        this.dateMoved = dateMoved;
    }

    public String getTimeSpent() {
        return this.timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getTimeEstimated() {
        return this.timeEstimated;
    }

    public void setTimeEstimated(String timeEstimated) {
        this.timeEstimated = timeEstimated;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDateModification() {
        return this.dateModification;
    }

    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    public String getDateCompleted() {
        return this.dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getProjectId() {
        return this.projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getColorId() {
        return this.colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColumnId() {
        return this.columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDateDue() {
        return this.dateDue;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSwimlaneId() {
        return this.swimlaneId;
    }

    public void setSwimlaneId(String swimlaneId) {
        this.swimlaneId = swimlaneId;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRecurrenceStatus() {
        return this.recurrenceStatus;
    }

    public void setRecurrenceStatus(String recurrenceStatus) {
        this.recurrenceStatus = recurrenceStatus;
    }

    public String getRecurrenceTrigger() {
        return this.recurrenceTrigger;
    }

    public void setRecurrenceTrigger(String recurrenceTrigger) {
        this.recurrenceTrigger = recurrenceTrigger;
    }

    public String getRecurrenceFactor() {
        return this.recurrenceFactor;
    }

    public void setRecurrenceFactor(String recurrenceFactor) {
        this.recurrenceFactor = recurrenceFactor;
    }

    public String getRecurrenceTimeFrame() {
        return this.recurrenceTimeFrame;
    }

    public void setRecurrenceTimeFrame(String recurrenceTimeFrame) {
        this.recurrenceTimeFrame = recurrenceTimeFrame;
    }

    public String getRecurrenceBaseDate() {
        return this.recurrenceBaseDate;
    }

    public void setRecurrenceBaseDate(String recurrenceBaseDate) {
        this.recurrenceBaseDate = recurrenceBaseDate;
    }

}
