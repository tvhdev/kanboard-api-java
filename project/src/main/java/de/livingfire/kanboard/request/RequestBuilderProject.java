package de.livingfire.kanboard.request;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.livingfire.kanboard.exception.KanboardException;
import de.livingfire.kanboard.rest.KanboardRequest;
import de.livingfire.kanboard.rest.KanboardRequestWithoutParams;

public class RequestBuilderProject extends RequestBuilder {

    public KanboardRequest projectByName(String projectName) {
        KanboardRequest request = new KanboardRequest(METHOD_PROJECT_GET_BY_NAME);
        request.getParams()
               .put(PARAM_NAME, projectName);
        return request;
    }

    public KanboardRequest projectCreate(Map<String, String> params) {
        KanboardRequest request = new KanboardRequest(METHOD_PROJECT_CREATE);
        addParams(request, params, PARAMS_ALLOWED_PROJECT_CREATE);
        return request;
    }

    public KanboardRequest projectCreate(String projectName,
                                         Map<String, String> paramsOptional) {
        Map<String, String> params = paramsOptional;
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(PARAM_NAME, projectName);

        return projectCreate(params);
    }

    public KanboardRequest projectById(String projectId) {
        KanboardRequest request = new KanboardRequest(METHOD_PROJECT_GET_BY_ID);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        return request;
    }

    public KanboardRequest projectAddUser(String projectId,
                                          String userId,
                                          String role) {
        KanboardRequest request = new KanboardRequest(METHOD_PROJECT_ADD_USER);
        request.getParams()
               .put(PARAM_PROJECT_ID, projectId);
        request.getParams()
               .put(PARAM_USER_ID, userId);
        if (role != null) {
            request.getParams()
                   .put(PARAM_ROLE, role);
        }
        return request;
    }

    public KanboardRequest projectUpdate(Map<String, String> params) {
        KanboardRequest request = new KanboardRequest(METHOD_PROJECT_UPDATE);
        addParams(request, params, PARAMS_ALLOWED_PROJECT_UPDATE);
        return request;
    }

    public String projectGetAll() {
        KanboardRequestWithoutParams request = new KanboardRequestWithoutParams(METHOD_PROJECT_GET_ALL);
        request.setId(REQUEST_ID_DEFAULT);
        request.setJsonrpc(REQUEST_JSONRPC_DEFAULT);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new KanboardException("unable to create JSON getTimezone");
        }
    }
}
