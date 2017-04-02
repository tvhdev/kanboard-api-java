package de.livingfire.kanboard.util;

import java.util.HashMap;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardSwimlane;

public class KanboardUtilSwimlane extends KanboardUtil<KanboardSwimlane> {

    @Override
    public Map<String, String> convertToMap(KanboardSwimlane swimlane) {
        if (swimlane == null) {
            return null;
        }
        Map<String, String> h = new HashMap<>();
        h.put(PARAM_ID, swimlane.getId());
        h.put(PARAM_PROJECT_ID, swimlane.getProjectId());
        h.put(PARAM_NAME, swimlane.getName());
        h.put(PARAM_DESCRIPTION, swimlane.getDescription());
        h.put(PARAM_IS_ACTIVE, swimlane.getIsActive());
        h.put(PARAM_POSITION, swimlane.getPosition());
        return h;
    }

    @Override
    public KanboardSwimlane convertToObject(Map<String, String> h) {
        if (h == null) {
            return null;
        }
        KanboardSwimlane swimlane = new KanboardSwimlane();
        swimlane.setId(h.get(PARAM_ID));
        swimlane.setProjectId(h.get(PARAM_PROJECT_ID));
        swimlane.setName(h.get(PARAM_NAME));
        swimlane.setDescription(h.get(PARAM_DESCRIPTION));
        swimlane.setPosition(h.get(PARAM_POSITION));
        swimlane.setIsActive(h.get(PARAM_IS_ACTIVE));
        return swimlane;
    }

}
