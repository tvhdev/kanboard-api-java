package de.livingfire.kanboard.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardProject;
import de.livingfire.kanboard.request.RequestBuilderProject;
import de.livingfire.kanboard.rest.KanboardRequest;
import de.livingfire.kanboard.rest.KanboardResponse;
import de.livingfire.kanboard.service.KanboardServiceProject;
import de.livingfire.kanboard.util.KanboardUtil;
import de.livingfire.kanboard.util.KanboardUtilProject;

public abstract class KanboardServiceProjectNative extends RestServiceObjectDefault<KanboardProject>
        implements KanboardServiceProject {

    private static final ArrayList<String> PARAMS_OBJECT_ENSHURE;
    static {
        PARAMS_OBJECT_ENSHURE = new ArrayList<>();
        PARAMS_OBJECT_ENSHURE.add(PARAM_NAME);
        PARAMS_OBJECT_ENSHURE.add(PARAM_DESCRIPTION);
        PARAMS_OBJECT_ENSHURE.add(PARAM_OWNER_ID);
    }
    private final KanboardUtilProject util;

    private final RequestBuilderProject requestBuilder;

    public KanboardServiceProjectNative() {
        super();
        this.util = new KanboardUtilProject();
        this.requestBuilder = new RequestBuilderProject();
    }

    @Override
    public List<String> enshureParams() {
        return PARAMS_OBJECT_ENSHURE;
    }

    @Override
    public KanboardUtil<KanboardProject> getUtil() {
        return this.util;
    }

    public KanboardResponse<List<HashMap<String, String>>> projectGetAll() {
        String json = this.requestBuilder.projectGetAll();
        return sendRequestWithFalseCheck(json);
    }

    public KanboardResponse<Boolean> projectUpdate(Map<String, String> params) {
        KanboardRequest request = this.requestBuilder.projectUpdate(params);
        return sendRequestWithFalseCheck(request);
    }

    public KanboardResponse<Boolean> projectUpdate(String projectId,
                                                   String name,
                                                   String description) {
        return projectUpdate(projectId, name, description, null, null);
    }

    public KanboardResponse<Boolean> projectUpdate(String projectId,
                                                   String name,
                                                   String description,
                                                   String ownerId,
                                                   String identifer) {
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_PROJECT_ID, projectId);
        params.put(PARAM_NAME, name);
        params.put(PARAM_DESCRIPTION, description);
        params.put(PARAM_OWNER_ID, ownerId);
        params.put(PARAM_IDENTIFIER, identifer);
        return projectUpdate(params);
    }

    public KanboardResponse<Map<String, String>> projectGetById(String projectId) {
        KanboardRequest request = this.requestBuilder.projectById(projectId);
        return sendRequest(request);
    }

    public KanboardResponse<Integer> projectCreate(String projectName,
                                                   Map<String, String> paramsOptional) {
        KanboardRequest request = this.requestBuilder.projectCreate(projectName, paramsOptional);
        return sendRequestWithFalseCheck(request);
    }

    public KanboardResponse<Map<String, String>> projectByName(String projectName) {
        KanboardRequest request = this.requestBuilder.projectByName(projectName);
        return sendRequest(request);
    }

    public void projectAddUser(String projectId,
                               String userId,
                               String role) {
        KanboardRequest request = this.requestBuilder.projectAddUser(projectId, userId, role);
        sendRequest(request);
    }

}
