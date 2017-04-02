package de.livingfire.kanboard.service.impl;

import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardAction;

public class KanboardServiceActionDefault extends KanboardServiceActionNative {

    public KanboardServiceActionDefault() {
        super();
    }

    @Override
    public Map<String, String> availableActions() {
        return actionAvailableActions().getResult();
    }

    @Override
    public Map<String, String> availableActionEvents() {
        return actionAvailableActionEvents().getResult();
    }

    @Override
    public Map<String, String> availableActionEvents(String actionName) {
        return actionAvailableActionEvents(actionName).getResult();
    }

    @Override
    public List<KanboardAction> getAll(String projectId) {
        return getUtil().convertToObjectList(actionGetAll(projectId).getResult());
    }

    @Override
    public KanboardAction create(KanboardAction action) {
        Integer responseId = actionCreate(action).getResult();
        action.setId(String.valueOf(responseId));
        return action;
    }
}
