package de.livingfire.kanboard.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardUser;
import de.livingfire.kanboard.request.RequestBuilderUser;
import de.livingfire.kanboard.rest.KanboardRequest;
import de.livingfire.kanboard.rest.KanboardResponse;
import de.livingfire.kanboard.service.KanboardServiceUser;
import de.livingfire.kanboard.util.KanboardUtil;
import de.livingfire.kanboard.util.KanboardUtilUser;

public abstract class KanboardServiceUserNative extends RestServiceObjectDefault<KanboardUser>
        implements KanboardServiceUser {
    private static final ArrayList<String> PARAMS_OBJECT_ENSHURE;
    static {
        PARAMS_OBJECT_ENSHURE = new ArrayList<>();
        PARAMS_OBJECT_ENSHURE.add(PARAM_USERNAME);
    }

    private final KanboardUtilUser util;

    private final RequestBuilderUser requestBuilder;

    public KanboardServiceUserNative() {
        super();
        this.requestBuilder = new RequestBuilderUser();
        this.util = new KanboardUtilUser();
    }

    @Override
    public List<String> enshureParams() {
        return PARAMS_OBJECT_ENSHURE;
    }

    @Override
    public KanboardUtil<KanboardUser> getUtil() {
        return this.util;
    }

    public KanboardResponse<Map<String, String>> userGetById(String userId) {
        KanboardRequest request = this.requestBuilder.userGetById(userId);
        return sendRequest(request);
    }

    public void userDisable(String userId) {
        KanboardRequest request = this.requestBuilder.userDisable(userId);
        sendRequestWithFalseCheck(request);
    }

    public KanboardResponse<Integer> userCreate(String userName,
                                                String password,
                                                Map<String, String> paramsOptional) {
        Map<String, String> params = paramsOptional;
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(PARAM_USERNAME, userName);
        params.put(PARAM_PASSWORD, password);
        return userCreate(params);
    }

    public KanboardResponse<Integer> userCreate(Map<String, String> params) {
        KanboardRequest request = this.requestBuilder.userCreate(params);
        return sendRequestWithFalseCheck(request);
    }

    public KanboardResponse<Map<String, String>> userByUsername(String userName) {
        KanboardRequest request = this.requestBuilder.userByUsername(userName);
        return sendRequest(request);
    }

    public void userUpdate(String userId,
                           Map<String, String> paramsOptional) {
        Map<String, String> params = paramsOptional;
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(PARAM_USER_ID, userId);
        userUpdate(params);
    }

    public void userUpdate(Map<String, String> params) {
        KanboardRequest request = this.requestBuilder.userUpdate(params);
        sendRequestWithFalseCheck(request);
    }
}
