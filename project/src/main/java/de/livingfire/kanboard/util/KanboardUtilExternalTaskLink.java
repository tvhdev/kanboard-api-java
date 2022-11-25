package de.livingfire.kanboard.util;

import java.util.HashMap;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardExternalTaskLink;
import de.livingfire.kanboard.domain.KanboardTask;

public class KanboardUtilExternalTaskLink extends KanboardUtil<KanboardExternalTaskLink> {

	@Override
	public Map<String, Object> convertToMap(KanboardExternalTaskLink externalTaskLink) {
        if (externalTaskLink == null) {
            return null;
        }
        Map<String, Object> h = new HashMap<>();
        h.put(PARAM_ID, externalTaskLink.getId());
        h.put(PARAM_TASK_ID, externalTaskLink.getTaskId());
        h.put(PARAM_URL, externalTaskLink.getUrl());
        h.put(PARAM_TITLE, externalTaskLink.getTitle());
        h.put(PARAM_DEPENDENCY, externalTaskLink.getDependency());
        h.put(PARAM_TYPE, externalTaskLink.getType());
        
        return h;
	}

	@Override
	public KanboardExternalTaskLink convertToObject(Map<String, Object> h) {
		if (h == null) {
            return null;
        }
		KanboardExternalTaskLink externalTaskLink = new KanboardExternalTaskLink();
		externalTaskLink.setId(String.valueOf(h.get(PARAM_ID)));
		externalTaskLink.setTaskId((String)h.get(PARAM_TASK_ID));
		externalTaskLink.setUrl((String)h.get(PARAM_URL));
		externalTaskLink.setTitle((String)h.get(PARAM_TITLE));
		externalTaskLink.setDependency((String)h.get(PARAM_DEPENDENCY));
		externalTaskLink.setType((String)h.get(PARAM_TYPE));
		return externalTaskLink;
	}

}
