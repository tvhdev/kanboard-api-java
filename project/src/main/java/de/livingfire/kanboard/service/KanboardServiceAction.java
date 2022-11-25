package de.livingfire.kanboard.service;

import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardAction;
import de.livingfire.kanboard.util.KanboardUtilAction;

public interface KanboardServiceAction extends RestService {

    KanboardUtilAction getUtil();

    KanboardAction create(KanboardAction action);

    List<KanboardAction> getAll(String projectId);

    Map<String, Object> availableActionEvents(String actionName);

    Map<String, Object> availableActionEvents();

    Map<String, Object> availableActions();

}
