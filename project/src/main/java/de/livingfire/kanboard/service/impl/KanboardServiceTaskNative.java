package de.livingfire.kanboard.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardTask;
import de.livingfire.kanboard.request.RequestBuilderTask;
import de.livingfire.kanboard.rest.KanboardRequest;
import de.livingfire.kanboard.rest.KanboardResponse;
import de.livingfire.kanboard.service.KanboardServiceTask;
import de.livingfire.kanboard.util.KanboardUtil;
import de.livingfire.kanboard.util.KanboardUtilTask;

public abstract class KanboardServiceTaskNative extends RestServiceObjectDefault<KanboardTask>
        implements KanboardServiceTask {
    private static final ArrayList<String> PARAMS_OBJECT_ENSHURE;
    static {
        PARAMS_OBJECT_ENSHURE = new ArrayList<>();
        PARAMS_OBJECT_ENSHURE.add(PARAM_COLOR_ID);
        PARAMS_OBJECT_ENSHURE.add(PARAM_COLUMN_ID);
        PARAMS_OBJECT_ENSHURE.add(PARAM_DESCRIPTION);
        PARAMS_OBJECT_ENSHURE.add(PARAM_CATEGORY_ID);
        PARAMS_OBJECT_ENSHURE.add(PARAM_SWIMLANE_ID);
        PARAMS_OBJECT_ENSHURE.add(PARAM_PRIORITY);
    }

    private final KanboardUtilTask util;

    private final RequestBuilderTask requestBuilder;

    public KanboardServiceTaskNative() {
        super();
        this.util = new KanboardUtilTask();
        this.requestBuilder = new RequestBuilderTask();
    }

    @Override
    public List<String> enshureParams() {
        return PARAMS_OBJECT_ENSHURE;
    }

    @Override
    public KanboardUtil<KanboardTask> getUtil() {
        return this.util;
    }

    public void taskUpdate(String taskId,
                           Map<String, String> paramsOptional) {
        KanboardRequest request = this.requestBuilder.taskUpdate(taskId, paramsOptional);
        sendRequestWithFalseCheck(request);
    }

    public KanboardResponse<Integer> taskCreate(String title,
                                                String projectId,
                                                Map<String, String> paramsOptional) {
        KanboardRequest request = this.requestBuilder.taskCreate(title, projectId, paramsOptional);
        return sendRequestWithFalseCheck(request);
    }

    public KanboardResponse<ArrayList<Map<String, String>>> taskGetActive(String projectId) {
        return taskGetAll(projectId, STATUS_ACTIVE);
    }

    public KanboardResponse<ArrayList<Map<String, String>>> taskGetInactive(String projectId) {
        return taskGetAll(projectId, STATUS_INACTIVE);
    }

    public KanboardResponse<ArrayList<Map<String, String>>> taskGetAll(String projectId,
                                                                       String statusId) {
        KanboardRequest request = this.requestBuilder.taskGetAll(projectId, statusId);
        return sendRequestWithFalseCheck(request);
    }

    public KanboardResponse<Map<String, String>> taskById(String taskId) {
        KanboardRequest request = this.requestBuilder.taskById(taskId);
        return sendRequest(request);
    }

    public KanboardResponse<Boolean> taskMovePosition(String projectId,
                                                      String taskId,
                                                      String columnId,
                                                      String position,
                                                      Map<String, String> paramsOptional) {
        KanboardRequest request = this.requestBuilder.taskMovePosition(
                projectId,
                    taskId,
                    columnId,
                    position,
                    paramsOptional);
        return sendRequestWithFalseCheck(request);
    }
}
