package de.livingfire.kanboard.util;

import java.util.HashMap;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardTask;

public class KanboardUtilTask extends KanboardUtil<KanboardTask> {

    @Override
    public Map<String, String> convertToMap(KanboardTask task) {
        if (task == null) {
            return null;
        }
        Map<String, String> h = new HashMap<>();
        h.put(PARAM_TITLE, task.getTitle());
        h.put(PARAM_PROJECT_ID, task.getProjectId());
        h.put(PARAM_DESCRIPTION, task.getDescription());
        h.put(PARAM_COLOR_ID, task.getColorId());
        h.put(PARAM_COLUMN_ID, task.getColumnId());
        h.put(PARAM_OWNER_ID, task.getOwnerId());
        h.put(PARAM_CREATOR_ID, task.getCreatorId());
        h.put(PARAM_DATE_DUE, task.getDateDue());
        h.put(PARAM_CATEGORY_ID, task.getCategoryId());
        h.put(PARAM_SCORE, task.getScore());
        h.put(PARAM_SWIMLANE_ID, task.getSwimlaneId());
        h.put(PARAM_PRIORITY, task.getPriority());
        h.put(PARAM_RECURRENCE_STATUS, task.getRecurrenceStatus());
        h.put(PARAM_RECURRENCE_TRIGGER, task.getRecurrenceTrigger());
        h.put(PARAM_RECURRENCE_FACTOR, task.getRecurrenceFactor());
        h.put(PARAM_RECURRENCE_TIMEFRAME, task.getRecurrenceTimeFrame());
        h.put(PARAM_RECURRENCE_BASEDATE, task.getRecurrenceBaseDate());
        return h;
    }

    @Override
    public KanboardTask convertToObject(Map<String, String> h) {
        if (h == null) {
            return null;
        }
        KanboardTask task = new KanboardTask();
        task.setId(h.get(PARAM_ID));
        task.setTitle(h.get(PARAM_TITLE));
        task.setDescription(h.get(PARAM_DESCRIPTION));
        task.setDateCreation(h.get(PARAM_DATE_CREATION));
        task.setColorId(h.get(PARAM_COLOR_ID));
        task.setProjectId(h.get(PARAM_PROJECT_ID));
        task.setColumnId(h.get(PARAM_COLUMN_ID));
        task.setOwnerId(h.get(PARAM_OWNER_ID));
        task.setPosition(h.get(PARAM_POSITION));
        task.setActive(h.get(PARAM_IS_ACTIVE));
        task.setDateCompleted(h.get(PARAM_DATE_COMPLETED));
        task.setScore(h.get(PARAM_SCORE));
        task.setDateDue(h.get(PARAM_DATE_DUE));
        task.setCategoryId(h.get(PARAM_CATEGORY_ID));
        task.setCreatorId(h.get(PARAM_CREATOR_ID));
        task.setDateModification(h.get(PARAM_DATE_MODIFICATION));
        task.setReference(h.get(PARAM_REFERENCE));
        task.setDateStarted(h.get(PARAM_DATE_STARTED));
        task.setTimeSpent(h.get(PARAM_TIME_SPEND));
        task.setTimeEstimated(h.get(PARAM_TIME_ESTIMATED));
        task.setSwimlaneId(h.get(PARAM_SWIMLANE_ID));
        task.setDateMoved(h.get(PARAM_DATE_MOVED));
        task.setRecurrenceStatus(h.get(PARAM_RECURRENCE_STATUS));
        task.setRecurrenceTrigger(h.get(PARAM_RECURRENCE_TRIGGER));
        task.setRecurrenceFactor(h.get(PARAM_RECURRENCE_FACTOR));
        task.setRecurrenceTimeFrame(h.get(PARAM_RECURRENCE_TIMEFRAME));
        task.setRecurrenceBaseDate(h.get(PARAM_RECURRENCE_BASEDATE));
        task.setRecurrenceParent(h.get(PARAM_RECURRENCE_PARENT));
        task.setRecurrenceChild(h.get(PARAM_RECURRENCE_CHILD));
        task.setUrl(h.get(PARAM_URL));
        task.setPriority(h.get(PARAM_PRIORITY));
        return task;
    }

}
