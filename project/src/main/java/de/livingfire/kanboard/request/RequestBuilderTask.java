package de.livingfire.kanboard.request;

import java.util.Map;

import de.livingfire.kanboard.rest.KanboardRequest;

public class RequestBuilderTask extends RequestBuilder {

    public KanboardRequest taskCreate(String title,
                                      String projectId,
                                      Map<String, String> paramsOptional) {
        KanboardRequest request = new KanboardRequest(METHOD_TASK_CREATE);

        request.getParams()
               .put(PARAM_TITLE, title);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);

        addParams(request, paramsOptional, PARAMS_ALLOWED_TASK_CREATE);
        return request;
    }

    public KanboardRequest taskById(String taskId) {
        KanboardRequest request = new KanboardRequest(METHOD_TASK_BY_ID);
        request.getParams()
               .put(PARAM_TASK_ID, taskId);
        return request;
    }

    public KanboardRequest taskGetAll(String projectId,
                                      String statusId) {
        KanboardRequest request = new KanboardRequest(METHOD_TASK_GET_ALL);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        request.getParams()
               .put(PARAM_STATUS_ID, statusId);
        return request;
    }

    public KanboardRequest taskUpdate(String taskId,
                                      Map<String, String> paramsOptional) {
        KanboardRequest request = new KanboardRequest(METHOD_TASK_UPDATE);

        request.getParams()
               .put(PARAM_ID, taskId);
        addParams(request, paramsOptional, PARAMS_ALLOWED_TASK_UPDATE);
        return request;
    }

    public KanboardRequest taskMovePosition(String projectId,
                                            String taskId,
                                            String columnId,
                                            String position,
                                            Map<String, String> paramsOptional) {
        KanboardRequest request = new KanboardRequest(METHOD_TASK_MOVE_POSITION);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        request.getParams()
               .put(PARAM_TASK_ID, taskId);
        request.getParams()
               .put(PARAM_COLUMN_ID, columnId);
        request.getParams()
               .put(PARAM_POSITION, position);
        addParams(request, paramsOptional, PARAMS_ALLOWED_TASK_MOVE_POSITION);
        return request;
    }

}
