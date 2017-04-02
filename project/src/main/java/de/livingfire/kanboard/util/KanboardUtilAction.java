package de.livingfire.kanboard.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.domain.KanboardAction;

public class KanboardUtilAction implements KanboardConstant {

    public Map<String, Object> convertToMap(KanboardAction kanboardObject) {
        throw new RuntimeException("implement me");
    }

    @SuppressWarnings("unchecked")
    public KanboardAction convertToObject(Map<String, Object> h) {
        KanboardAction action = new KanboardAction();
        action.setId(String.valueOf(h.get(PARAM_ID)));
        action.setProjectId(String.valueOf(h.get(PARAM_PROJECT_ID)));
        action.setEventName(String.valueOf(h.get(PARAM_EVENT_NAME)));
        action.setActionName(String.valueOf(h.get(PARAM_ACTION_NAME)));
        action.setParams((LinkedHashMap<String, String>) h.get(PARAM_PARAMS));
        return action;
    }

    public List<KanboardAction> convertToObjectList(List<Map<String, Object>> hashMapList) {
        if ((hashMapList == null) || hashMapList.isEmpty()) {
            return new ArrayList<>();
        }
        return hashMapList.stream()
                          .map(h -> convertToObject(h))
                          .collect(Collectors.toCollection(ArrayList::new));
    }

}
