package de.livingfire.kanboard.request;

import java.util.Map;

import de.livingfire.kanboard.rest.KanboardRequest;

public class RequestBuilderSwimlane extends RequestBuilder {

    public KanboardRequest swimlaneGetAll(String projectId) {
        KanboardRequest request = new KanboardRequest(METHOD_SWIMLANE_GET_ALL);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        return request;
    }

    public KanboardRequest swimlaneGetById(String swimlaneId) {
        KanboardRequest request = new KanboardRequest(METHOD_SWIMLANE_GET_BY_ID);
        request.getParams()
               .put(PARAM_SWIMLANE_ID, swimlaneId);
        return request;
    }

    public KanboardRequest swimlaneAdd(String projectId,
                                       String swimlaneName,
                                       String swimlaneDescription) {
        KanboardRequest request = new KanboardRequest(METHOD_SWIMLANE_ADD);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        request.getParams()
               .put(PARAM_NAME, swimlaneName);
        if (swimlaneDescription != null) {
            request.getParams()
                   .put(PARAM_DESCRIPTION, swimlaneDescription);
        }
        return request;
    }

    public KanboardRequest swimlaneGetByName(String projectId,
                                             String swimlaneName) {
        KanboardRequest request = new KanboardRequest(METHOD_SWIMLANE_GET_BY_NAME);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        request.getParams()
               .put(PARAM_NAME, swimlaneName);
        return request;
    }

    public KanboardRequest swimlaneGetDefault(String projectId) {
        return swimlaneGetByName(projectId, DEFAULT_SWIMLANE_NAME);
    }

    public KanboardRequest swimlaneUpdate(Map<String, String> params) {
        return swimlaneUpdate(
                params.get(PARAM_PROJECT_ID),
                    params.get(PARAM_SWIMLANE_ID),
                    params.get(PARAM_NAME),
                    params.get(PARAM_DESCRIPTION));
    }

    public KanboardRequest swimlaneUpdate(String projectId,
                                          String swimlaneId,
                                          String swimlaneName,
                                          String swimlaneDescription) {
        KanboardRequest request = new KanboardRequest(METHOD_SWIMLANE_UPDATE);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        request.getParams()
               .put(PARAM_SWIMLANE_ID, swimlaneId);
        request.getParams()
               .put(PARAM_NAME, swimlaneName);
        if (swimlaneDescription != null) {
            request.getParams()
                   .put(PARAM_DESCRIPTION, swimlaneDescription);
        }
        return request;
    }

    public KanboardRequest swimlaneDisable(String projectId,
                                           String swimlaneId) {
        KanboardRequest request = new KanboardRequest(METHOD_SWIMLANE_DISABLE);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        request.getParams()
               .put(PARAM_SWIMLANE_ID, swimlaneId);
        return request;
    }

    public KanboardRequest swimlaneEnable(String projectId,
                                          String swimlaneId) {
        KanboardRequest request = new KanboardRequest(METHOD_SWIMLANE_ENABLE);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        request.getParams()
               .put(PARAM_SWIMLANE_ID, swimlaneId);
        return request;
    }

    public KanboardRequest swimlaneRemove(String projectId,
                                          String swimlaneId) {
        KanboardRequest request = new KanboardRequest(METHOD_SWIMLANE_REMOVE);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        request.getParams()
               .put(PARAM_SWIMLANE_ID, swimlaneId);
        return request;
    }

    public KanboardRequest swimlaneChangePosition(Map<String, String> params) {
        KanboardRequest request = new KanboardRequest(METHOD_SWIMLANE_CHANGE_POSITION);
        addParams(request, params, PARAMS_ALLOWED_SWIMLANE_CHANGE_POSITION);
        return request;
    }

}
