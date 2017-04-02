package de.livingfire.kanboard.service;

import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardAction;
import de.livingfire.kanboard.util.KanboardUtilAction;

public interface KanboardServiceAction extends RestService {

    KanboardUtilAction getUtil();

    KanboardAction create(KanboardAction action);

    List<KanboardAction> getAll(String projectId);

    Map<String, String> availableActionEvents(String actionName);

    Map<String, String> availableActionEvents();

    Map<String, String> availableActions();

}
