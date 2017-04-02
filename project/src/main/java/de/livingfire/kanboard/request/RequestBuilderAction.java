package de.livingfire.kanboard.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.livingfire.kanboard.domain.KanboardAction;
import de.livingfire.kanboard.exception.KanboardException;
import de.livingfire.kanboard.rest.KanboardRequest;
import de.livingfire.kanboard.rest.KanboardRequestAction;

public class RequestBuilderAction extends RequestBuilder {

    public KanboardRequest actionAvailableActions() {
        KanboardRequest request = new KanboardRequest(METHOD_ACTION_AVAILABLE_ACTIONS);
        return request;
    }

    public KanboardRequest actionAvailableActionEvents() {
        KanboardRequest request = new KanboardRequest(METHOD_ACTION_AVAILABLE_ACTION_EVENTS);
        return request;
    }

    public KanboardRequest actionAvailableActionEvents(String actionName) {
        KanboardRequest request = new KanboardRequest(METHOD_ACTION_AVAILABLE_ACTION_EVENTS_COMPATIBLE);
        request.getParams()
               .put(PARAM_ACTION_NAME, actionName);
        return request;
    }

    public KanboardRequest actionGetAll(String projectId) {
        KanboardRequest request = new KanboardRequest(METHOD_ACTION_GET_ALL);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        return request;
    }

    public String actionCreate(KanboardAction action) {
        KanboardRequestAction request = new KanboardRequestAction(METHOD_ACTION_CREATE);
        request.setParams(action);
        request.setId(REQUEST_ID_DEFAULT);
        request.setJsonrpc(REQUEST_JSONRPC_DEFAULT);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new KanboardException("unable to create JSON actionCreate");
        }
    }

}
