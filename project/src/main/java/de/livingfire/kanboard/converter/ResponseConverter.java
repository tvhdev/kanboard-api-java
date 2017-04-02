package de.livingfire.kanboard.converter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.exception.KanboardException;
import de.livingfire.kanboard.rest.KanboardResponse;

public class ResponseConverter implements KanboardConstant {

    public <T> KanboardResponse<T> toObject(String json,
                                            TypeReference<T> type) {
        if (json == null) {
            return null;
        }
        try {
            return new ObjectMapper().readValue(json, new TypeReference<KanboardResponse<T>>() {
            });
        } catch (Exception e) {
            throw new KanboardException("convert response JSON -> Object failed", e);
        }
    }

    public String toJson(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        try (InputStreamReader instream = new InputStreamReader(inputStream);
                BufferedReader buffer = new BufferedReader(instream)) {
            String output;
            while ((output = buffer.readLine()) != null) {
                builder.append(output);
            }
        } catch (Exception e) {
            throw new KanboardException("convert response Inputstream -> JSON failed", e);
        }
        return builder.toString();
    }

}
