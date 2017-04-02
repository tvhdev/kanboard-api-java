package de.livingfire.kanboard.service;

import de.livingfire.kanboard.configuration.KanboardApi;
import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.exception.KanboardException;
import de.livingfire.kanboard.rest.KanboardRequest;
import de.livingfire.kanboard.rest.KanboardResponse;

public interface RestService extends KanboardConstant {

    boolean isVerbose();

    void setVerbose(boolean verbose);

    void setKanboardApi(KanboardApi kanboardApi);

    <RESPONSE_TYPE> KanboardResponse<RESPONSE_TYPE> sendRequest(String jsonRequest);

    <RESPONSE_TYPE> KanboardResponse<RESPONSE_TYPE> sendRequest(KanboardRequest kanboardRequest);

    default <RESPONSE_TYPE> KanboardResponse<RESPONSE_TYPE> sendRequestWithFalseCheck(KanboardRequest request) {
        return requestFalseCheck(sendRequest(request));
    }

    default <RESPONSE_TYPE> KanboardResponse<RESPONSE_TYPE> sendRequestWithFalseCheck(String jsonRequest) {
        return requestFalseCheck(sendRequest(jsonRequest));
    }

    default <RESPONSE_TYPE> KanboardResponse<RESPONSE_TYPE> requestFalseCheck(KanboardResponse<RESPONSE_TYPE> response) {
        if (Boolean.FALSE.equals(response.getResult())) {
            throw new KanboardException("Request response result MUST NOT be false: " + response);
        }
        return response;
    }
}