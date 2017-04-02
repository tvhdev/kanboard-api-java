package de.livingfire.kanboard.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardColumn;
import de.livingfire.kanboard.request.RequestBuilderColumn;
import de.livingfire.kanboard.rest.KanboardRequest;
import de.livingfire.kanboard.rest.KanboardResponse;
import de.livingfire.kanboard.service.KanboardServiceColumn;
import de.livingfire.kanboard.util.KanboardUtilColumn;

public abstract class KanboardServiceColumnNative extends RestServiceObjectDefault<KanboardColumn>
        implements KanboardServiceColumn {
    private static final ArrayList<String> PARAMS_OBJECT_ENSHURE;
    static {
        PARAMS_OBJECT_ENSHURE = new ArrayList<>();
        PARAMS_OBJECT_ENSHURE.add(PARAM_TITLE);
        PARAMS_OBJECT_ENSHURE.add(PARAM_DESCRIPTION);
    }

    private final KanboardUtilColumn util;

    private final RequestBuilderColumn requestBuilder;

    public KanboardServiceColumnNative() {
        super();
        this.util = new KanboardUtilColumn();
        this.requestBuilder = new RequestBuilderColumn();
    }

    @Override
    public List<String> enshureParams() {
        return PARAMS_OBJECT_ENSHURE;
    }

    @Override
    public KanboardUtilColumn getUtil() {
        return this.util;
    }

    public KanboardResponse<Boolean> columnUpdate(Map<String, String> params) {
        KanboardRequest request = this.requestBuilder.columnUpdate(params);
        return sendRequestWithFalseCheck(request);
    }

    public KanboardResponse<Boolean> columnUpdate(String columnId,
                                                  String title,
                                                  Map<String, String> optinalParams) {
        KanboardRequest request = this.requestBuilder.columnUpdate(columnId, title, optinalParams);
        return sendRequestWithFalseCheck(request);
    }

    public KanboardResponse<Map<String, String>> columnGetById(String columnId) {
        KanboardRequest request = this.requestBuilder.columnGetById(columnId);
        return sendRequest(request);
    }

    public KanboardResponse<Boolean> columnChangePosition(String projectId,
                                                          String columnId,
                                                          String position) {
        KanboardRequest request = this.requestBuilder.columnChangePosition(projectId, columnId, position);
        return sendRequestWithFalseCheck(request);
    }

    public KanboardResponse<Integer> columnAdd(String projectId,
                                               String title) {
        return columnAdd(projectId, title, null, null);
    }

    public KanboardResponse<Integer> columnAdd(String projectId,
                                               String title,
                                               String taskLimit,
                                               String description) {
        KanboardRequest request = this.requestBuilder.columnAdd(projectId, title, taskLimit, description);
        return sendRequestWithFalseCheck(request);
    }

    public KanboardResponse<ArrayList<Map<String, String>>> columnGetAll(String projectId) {
        KanboardRequest request = this.requestBuilder.columnGet(projectId);
        return sendRequest(request);
    }

    public KanboardResponse<Boolean> columnRemove(String columnId) {
        KanboardRequest request = this.requestBuilder.columnRemove(columnId);
        return sendRequestWithFalseCheck(request);
    }

}
