package de.livingfire.kanboard.service.impl;

import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardExternalTaskLink;

public class KanboardServiceExternalTaskLinkDefault 
extends KanboardServiceExternalTaskLinkNative {
	public KanboardServiceExternalTaskLinkDefault() {
		super();
	}

	@Override
	public KanboardExternalTaskLink create(KanboardExternalTaskLink externalTaskLink) {
        Map<String, Object> paramsOptional = getUtil().convertToMap(externalTaskLink);
        Integer externalTaskLinkId = externalTaskLinkCreate(externalTaskLink.getTaskId(), externalTaskLink.getUrl(), externalTaskLink.getDependency(), paramsOptional).getResult();
        return getById(externalTaskLinkId);
	}

	@Override
	public KanboardExternalTaskLink update(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KanboardExternalTaskLink getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KanboardExternalTaskLink getByObjectName(KanboardExternalTaskLink kanboardObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> enshureParams() {
		// TODO Auto-generated method stub
		return null;
	}
}