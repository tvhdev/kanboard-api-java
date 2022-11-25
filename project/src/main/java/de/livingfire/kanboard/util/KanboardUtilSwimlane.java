package de.livingfire.kanboard.util;

import java.util.HashMap;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardSwimlane;

public class KanboardUtilSwimlane extends KanboardUtil<KanboardSwimlane> {

    @Override
    public Map<String, Object> convertToMap(KanboardSwimlane swimlane) {
        if (swimlane == null) {
            return null;
        }
        Map<String, Object> h = new HashMap<>();
        h.put(PARAM_ID, swimlane.getId());
        h.put(PARAM_PROJECT_ID, swimlane.getProjectId());
        h.put(PARAM_NAME, swimlane.getName());
        h.put(PARAM_DESCRIPTION, swimlane.getDescription());
        h.put(PARAM_IS_ACTIVE, swimlane.getIsActive());
        h.put(PARAM_POSITION, swimlane.getPosition());
        return h;
    }

    @Override
    public KanboardSwimlane convertToObject(Map<String, Object> h) {
        if (h == null) {
            return null;
        }
        KanboardSwimlane swimlane = new KanboardSwimlane();
        swimlane.setId(String.valueOf(h.get(PARAM_ID)));
        swimlane.setProjectId(String.valueOf(h.get(PARAM_PROJECT_ID)));
        swimlane.setName((String)h.get(PARAM_NAME));
        swimlane.setDescription((String)h.get(PARAM_DESCRIPTION));
        swimlane.setPosition(String.valueOf(h.get(PARAM_POSITION)));
        swimlane.setIsActive(String.valueOf(h.get(PARAM_IS_ACTIVE)));
        return swimlane;
    }

}
