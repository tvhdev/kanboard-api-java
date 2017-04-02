package de.livingfire.kanboard.request;

import java.util.Map;

import de.livingfire.kanboard.rest.KanboardRequest;

public class RequestBuilderUser extends RequestBuilder {

    public KanboardRequest userByUsername(String userName) {
        KanboardRequest request = new KanboardRequest(METHOD_USER_GET_BY_USERNAME);
        request.getParams()
               .put(PARAM_USERNAME, userName);
        return request;
    }

    public KanboardRequest userDisable(String userId) {
        KanboardRequest request = new KanboardRequest(METHOD_USER_DISABLE);
        request.getParams()
               .put(PARAM_USER_ID, userId);

        return request;
    }

    public KanboardRequest userCreate(Map<String, String> params) {
        KanboardRequest request = new KanboardRequest(METHOD_USER_CREATE);
        addParams(request, params, PARAMS_ALLOWED_USER_CREATE);
        return request;
    }

    public KanboardRequest userUpdate(Map<String, String> params) {
        KanboardRequest request = new KanboardRequest(METHOD_USER_UPDATE);
        addParams(request, params, PARAMS_ALLOWED_USER_UPDATE);
        return request;
    }

    public KanboardRequest userGetById(String userId) {
        KanboardRequest request = new KanboardRequest(METHOD_USER_GET_BY_ID);
        request.getParams()
               .put(PARAM_USER_ID, userId);
        return request;
    }
}
