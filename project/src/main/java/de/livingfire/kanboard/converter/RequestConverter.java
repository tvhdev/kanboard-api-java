package de.livingfire.kanboard.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.exception.KanboardException;
import de.livingfire.kanboard.rest.KanboardRequest;

public class RequestConverter implements KanboardConstant {

    public String toJson(KanboardRequest kanboardRequest,
                         boolean verbose) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (verbose) {
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
            }
            return mapper.writeValueAsString(kanboardRequest);
        } catch (Exception e) {
            throw new KanboardException("convert request Object -> JSON failed", e);
        }
    }

    public KanboardRequest toObject(String json) {
        if (json == null) {
            return null;
        }
        try {
            return new ObjectMapper().readValue(json, KanboardRequest.class);
        } catch (Exception e) {
            throw new KanboardException("convert request JSON -> Object failed", e);
        }
    }

}
