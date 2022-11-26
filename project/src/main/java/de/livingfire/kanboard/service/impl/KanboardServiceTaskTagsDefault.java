package de.livingfire.kanboard.service.impl;

import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardTaskTags;

public class KanboardServiceTaskTagsDefault 
extends KanboardServiceTaskTagsNative {
	public KanboardServiceTaskTagsDefault() {
		super();
	}

	@Override
	public KanboardTaskTags create(KanboardTaskTags taskTags) {
		return null;
	}

	@Override
	public KanboardTaskTags update(Map<String, Object> map) {
		String taskId = (String)map.get(PARAM_TASK_ID);
		taskTagsUpdate((String)map.get(PARAM_PROJECT_ID), taskId, (String[])map.get(PARAM_TAGS));
		return getUtil().convertToObject(taskTagsById(taskId).getResult());
	}

	@Override
	public KanboardTaskTags getById(String taskId) {
		return getUtil().convertToObject(taskTagsById(taskId).getResult());
	}

	@Override
	public KanboardTaskTags getByObjectName(KanboardTaskTags kanboardObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> enshureParams() {
		// TODO Auto-generated method stub
		return null;
	}
}