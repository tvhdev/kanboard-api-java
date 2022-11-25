package de.livingfire.kanboard.util;

import java.util.HashMap;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardColumn;

public class KanboardUtilColumn extends KanboardUtil<KanboardColumn> {

    @Override
    public KanboardColumn convertToObject(Map<String, String> h) {
        if (h == null) {
            return null;
        }
        KanboardColumn column = new KanboardColumn();
        column.setId(String.valueOf(h.get(PARAM_ID)));
        column.setProjectId(String.valueOf(h.get(PARAM_PROJECT_ID)));
        column.setTitle(h.get(PARAM_TITLE));
        column.setPosition(String.valueOf(h.get(PARAM_POSITION)));
        column.setTaskLimit(String.valueOf(h.get(PARAM_TASK_LIMIT)));
        column.setDescription(h.get(PARAM_DESCRIPTION));
        column.setHideInDashboard(String.valueOf(h.get(PARAM_HIDE_IN_DASHBOARD)));
        return column;
    }

    @Override
    public Map<String, String> convertToMap(KanboardColumn column) {
        if (column == null) {
            return null;
        }
        Map<String, String> h = new HashMap<>();
        h.put(PARAM_ID, column.getId());
        h.put(PARAM_PROJECT_ID, column.getProjectId());
        h.put(PARAM_TITLE, column.getTitle());
        h.put(PARAM_POSITION, column.getPosition());
        h.put(PARAM_TASK_LIMIT, column.getTaskLimit());
        h.put(PARAM_DESCRIPTION, column.getDescription());
        h.put(PARAM_HIDE_IN_DASHBOARD, column.getHideInDashboard());
        return h;
    }

}
