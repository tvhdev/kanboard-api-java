package de.livingfire.kanboard.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.livingfire.kanboard.exception.KanboardException;
import de.livingfire.kanboard.rest.KanboardRequestWithoutParams;

public class RequestBuilderApplication extends RequestBuilder {

    public String applicationGetTimezone() {
        KanboardRequestWithoutParams request = new KanboardRequestWithoutParams(METHOD_APPLICATION_GET_TIMEZONE);
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
