package de.livingfire.kanboard.request;

import java.util.Map;

import de.livingfire.kanboard.rest.KanboardRequest;

public class RequestBuilderExternalTaskLink extends RequestBuilder {
	public KanboardRequest externalTaskLinkCreate(String taskId, String url, String dependency, Map<String, Object> paramsOptional) {
        KanboardRequest request = new KanboardRequest(METHOD_CREATE_EXTERNAL_TASK_LINK);
        request.getParams().put(PARAM_TASK_ID, taskId);
        request.getParams().put(PARAM_URL, url);
        request.getParams().put(PARAM_DEPENDENCY, dependency);
        addParams(request, paramsOptional, PARAMS_ALLOWED_EXTERNAL_TASK_LINK_CREATE);
        return request;
    }
}
