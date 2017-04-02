package de.livingfire.kanboard.request;

import java.util.HashMap;
import java.util.Map;

import de.livingfire.kanboard.rest.KanboardRequest;

public class RequestBuilderColumn extends RequestBuilder {

    public KanboardRequest columnGet(String projectId) {
        KanboardRequest request = new KanboardRequest(METHOD_COLUMN_GET_ALL);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        return request;
    }

    public KanboardRequest columnRemove(String columnId) {
        KanboardRequest request = new KanboardRequest(METHOD_COLUMN_REMOVE);
        request.getParams()
               .put(PARAM_COLUMN_ID, columnId);
        return request;
    }

    public KanboardRequest columnAdd(String projectId,
                                     String title,
                                     String taskLimit,
                                     String description) {
        KanboardRequest request = new KanboardRequest(METHOD_COLUMN_ADD);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        request.getParams()
               .put(PARAM_TITLE, title);
        if (taskLimit != null) {
            request.getParams()
                   .put(PARAM_TASK_LIMIT, taskLimit);
        }
        if (description != null) {
            request.getParams()
                   .put(PARAM_DESCRIPTION, description);
        }
        return request;
    }

    public KanboardRequest columnChangePosition(String projectId,
                                                String columnId,
                                                String position) {
        KanboardRequest request = new KanboardRequest(METHOD_COLUMN_CHANGE_POSITON);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        request.getParams()
               .put(PARAM_COLUMN_ID, columnId);
        request.getParams()
               .put(PARAM_POSITION, position);
        return request;
    }

    public KanboardRequest columnGetById(String columnId) {
        KanboardRequest request = new KanboardRequest(METHOD_COLUMN_BY_ID);
        request.getParams()
               .put(PARAM_COLUMN_ID, columnId);
        return request;
    }

    public KanboardRequest columnUpdate(Map<String, String> params) {
        KanboardRequest request = new KanboardRequest(METHOD_COLUMN_UPDATE);

        addParams(request, params, PARAMS_ALLOWED_COLUMN_UPDATE);
        return request;
    }

    public KanboardRequest columnUpdate(String columnId,
                                        String title,
                                        Map<String, String> paramsOptional) {
        Map<String, String> params = paramsOptional;
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(PARAM_COLUMN_ID, columnId);
        params.put(PARAM_TITLE, title);
        return columnUpdate(params);
    }

}
