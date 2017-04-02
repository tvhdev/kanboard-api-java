package de.livingfire.kanboard.service.impl;

import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardAction;
import de.livingfire.kanboard.request.RequestBuilderAction;
import de.livingfire.kanboard.rest.KanboardRequest;
import de.livingfire.kanboard.rest.KanboardResponse;
import de.livingfire.kanboard.service.KanboardServiceAction;
import de.livingfire.kanboard.util.KanboardUtilAction;

public abstract class KanboardServiceActionNative extends RestServiceDefault implements KanboardServiceAction {

    private final RequestBuilderAction requestBuilder;
    private final KanboardUtilAction util;

    public KanboardServiceActionNative() {
        super();
        this.requestBuilder = new RequestBuilderAction();
        this.util = new KanboardUtilAction();
    }

    @Override
    public KanboardUtilAction getUtil() {
        return this.util;
    }

    protected KanboardResponse<Integer> actionCreate(KanboardAction action) {
        String json = this.requestBuilder.actionCreate(action);
        return sendRequestWithFalseCheck(json);
    }

    protected KanboardResponse<List<Map<String, Object>>> actionGetAll(String projectId) {
        KanboardRequest request = this.requestBuilder.actionGetAll(projectId);
        return sendRequestWithFalseCheck(request);
    }

    protected KanboardResponse<Map<String, String>> actionAvailableActionEvents(String actionName) {
        KanboardRequest request = this.requestBuilder.actionAvailableActionEvents(actionName);
        return sendRequestWithFalseCheck(request);
    }

    protected KanboardResponse<Map<String, String>> actionAvailableActionEvents() {
        KanboardRequest request = this.requestBuilder.actionAvailableActionEvents();
        return sendRequestWithFalseCheck(request);
    }

    protected KanboardResponse<Map<String, String>> actionAvailableActions() {
        KanboardRequest request = this.requestBuilder.actionAvailableActions();
        return sendRequestWithFalseCheck(request);
    }

}
