package de.livingfire.kanboard.request;

import de.livingfire.kanboard.rest.KanboardRequest;

public class RequestBuilderTaskTags extends RequestBuilder {
    public KanboardRequest taskTagsById(String taskId) {
        KanboardRequest request = new KanboardRequest(METHOD_GET_TASK_TAGS);
        request.getParams()
               .put(PARAM_TASK_ID, taskId);
        return request;
    }

    public KanboardRequest taskTagsUpdate(String taskId, String projectId, String[] tags) {
        KanboardRequest request = new KanboardRequest(METHOD_SET_TASK_TAGS);
        request.getParams()
               .put(PARAM_TASK_ID, taskId);
        request.getParams()
        		.put(PARAM_PROJECT_ID, taskId);
        request.getParams()
        		.put(PARAM_TAGS, tags);
        return request;
    }
}
