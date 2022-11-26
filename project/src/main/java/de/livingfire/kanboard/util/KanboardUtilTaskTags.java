package de.livingfire.kanboard.util;

import java.util.HashMap;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardTaskTags;

public class KanboardUtilTaskTags extends KanboardUtil<KanboardTaskTags> {

	@Override
	public Map<String, Object> convertToMap(KanboardTaskTags taskTags) {
        if (taskTags == null) {
            return null;
        }
        Map<String, Object> h = new HashMap<>();
        h.put(PARAM_PROJECT_ID, taskTags.getProjectId());
        h.put(PARAM_TASK_ID, taskTags.getTaskId());
        h.put(PARAM_TAGS, taskTags.getTags());
        
        return h;
	}

	@Override
	public KanboardTaskTags convertToObject(Map<String, Object> h) {
		if (h == null) {
            return null;
        }
		KanboardTaskTags taskTags = new KanboardTaskTags();
		taskTags.setTags(h.values().toArray(new String[0]));
		return taskTags;
	}

}
