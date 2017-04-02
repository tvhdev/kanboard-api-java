package de.livingfire.kanboard.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardSwimlane;
import de.livingfire.kanboard.request.RequestBuilderSwimlane;
import de.livingfire.kanboard.rest.KanboardRequest;
import de.livingfire.kanboard.rest.KanboardResponse;
import de.livingfire.kanboard.service.KanboardServiceSwimlane;
import de.livingfire.kanboard.util.KanboardUtil;
import de.livingfire.kanboard.util.KanboardUtilSwimlane;

public abstract class KanboardServiceSwimlaneNative extends RestServiceObjectDefault<KanboardSwimlane>
        implements KanboardServiceSwimlane {
    private static final ArrayList<String> PARAMS_OBJECT_ENSHURE;
    static {
        PARAMS_OBJECT_ENSHURE = new ArrayList<>();
        PARAMS_OBJECT_ENSHURE.add(PARAM_NAME);
        PARAMS_OBJECT_ENSHURE.add(PARAM_POSITION);
        PARAMS_OBJECT_ENSHURE.add(PARAM_IS_ACTIVE);
        PARAMS_OBJECT_ENSHURE.add(PARAM_PROJECT_ID);
    }

    private final KanboardUtilSwimlane util;

    private final RequestBuilderSwimlane requestBuilder;

    public KanboardServiceSwimlaneNative() {
        super();
        this.util = new KanboardUtilSwimlane();
        this.requestBuilder = new RequestBuilderSwimlane();
    }

    @Override
    public KanboardUtil<KanboardSwimlane> getUtil() {
        return this.util;
    }

    @Override
    public List<String> enshureParams() {
        return PARAMS_OBJECT_ENSHURE;
    }

    public void swimlaneChangePosition(String projectId,
                                       String swimlaneId,
                                       String position) {
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_PROJECT_ID, projectId);
        params.put(PARAM_SWIMLANE_ID, swimlaneId);
        params.put(PARAM_POSITION, position);
        KanboardRequest request = this.requestBuilder.swimlaneChangePosition(params);
        sendRequestWithFalseCheck(request);
    }

    public void swimlaneRemove(String projectId,
                               String swimlaneId) {
        KanboardRequest request = this.requestBuilder.swimlaneRemove(projectId, swimlaneId);
        sendRequestWithFalseCheck(request);
    }

    public void swimlaneDisable(String projectId,
                                String swimlaneId) {
        KanboardRequest request = this.requestBuilder.swimlaneDisable(projectId, swimlaneId);
        sendRequestWithFalseCheck(request);
    }

    public void swimlaneEnable(String projectId,
                               String swimlaneId) {
        KanboardRequest request = this.requestBuilder.swimlaneEnable(projectId, swimlaneId);
        sendRequestWithFalseCheck(request);
    }

    protected KanboardResponse<Boolean> swimlaneUpdate(Map<String, String> params) {
        KanboardRequest request = this.requestBuilder.swimlaneUpdate(params);
        return sendRequestWithFalseCheck(request);
    }

    public void swimlaneUpdate(String projectId,
                               String swimlaneId,
                               String swimlaneName,
                               String swimlaneDescription) {
        KanboardRequest request = this.requestBuilder.swimlaneUpdate(
                projectId,
                    swimlaneId,
                    swimlaneName,
                    swimlaneDescription);
        sendRequestWithFalseCheck(request);
    }

    public KanboardResponse<Map<String, String>> swimlaneGetDefault(String projectId) {
        KanboardRequest request = this.requestBuilder.swimlaneGetDefault(projectId);
        return sendRequest(request);
    }

    public KanboardResponse<Map<String, String>> swimlaneGetByName(String projectId,
                                                                   String swimlaneName) {
        KanboardRequest request = this.requestBuilder.swimlaneGetByName(projectId, swimlaneName);
        return sendRequest(request);
    }

    public KanboardResponse<Integer> swimlaneAdd(String projectId,
                                                 String swimlaneName,
                                                 String swimlaneDescription) {
        KanboardRequest request = this.requestBuilder.swimlaneAdd(projectId, swimlaneName, swimlaneDescription);
        return sendRequestWithFalseCheck(request);
    }

    public KanboardResponse<Map<String, String>> swimlaneGetById(String swimlaneId) {
        KanboardRequest request = this.requestBuilder.swimlaneGetById(swimlaneId);
        return sendRequest(request);
    }

    public KanboardResponse<ArrayList<Map<String, String>>> swimlaneGetAll(String projectId) {
        KanboardRequest request = this.requestBuilder.swimlaneGetAll(projectId);
        return sendRequest(request);
    }

}
