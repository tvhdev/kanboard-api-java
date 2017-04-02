package de.livingfire.kanboard.service.impl;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.livingfire.kanboard.configuration.KanboardApi;
import de.livingfire.kanboard.converter.RequestConverter;
import de.livingfire.kanboard.converter.ResponseConverter;
import de.livingfire.kanboard.exception.KanboardException;
import de.livingfire.kanboard.rest.KanboardRequest;
import de.livingfire.kanboard.rest.KanboardResponse;
import de.livingfire.kanboard.service.RestService;

public abstract class RestServiceDefault implements RestService {

    private boolean verbose;
    private KanboardApi kanboardApi;
    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;

    public RestServiceDefault() {
        super();
        this.requestConverter = new RequestConverter();
        this.responseConverter = new ResponseConverter();
        this.verbose = false;
    }

    @Override
    public boolean isVerbose() {
        return this.verbose;
    }

    @Override
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    protected KanboardApi getKanboardApi() {
        return this.kanboardApi;
    }

    @Override
    public void setKanboardApi(KanboardApi kanboardApi) {
        this.kanboardApi = kanboardApi;
    }

    @Override
    public <RESPONSE_TYPE> KanboardResponse<RESPONSE_TYPE> sendRequest(String jsonRequest) {

        HttpURLConnection httpConnection = null;
        try {
            httpConnection = (HttpURLConnection) new URL(this.kanboardApi.getApiUrl()).openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setRequestProperty("X-API-Auth", encodeApiToken());

            if (this.verbose) {
                System.out.println("\n\n----------  send request  ---------->");
                System.out.println(jsonRequest);
            }

            OutputStream os = null;
            os = httpConnection.getOutputStream();
            os.write(jsonRequest.getBytes());
            os.flush();
            os.close();

            checkHttpCode2xx(httpConnection);
            String jsonResponse = this.responseConverter.toJson(httpConnection.getInputStream());
            if (this.verbose) {
                System.out.println("<----------  get response  ----------");
                ObjectMapper mapper = new ObjectMapper();
                Object json = mapper.readValue(jsonResponse, Object.class);
                String indented = mapper.writerWithDefaultPrettyPrinter()
                                        .writeValueAsString(json);
                System.out.println(indented);
            }

            return this.responseConverter.toObject(jsonResponse, new TypeReference<RESPONSE_TYPE>() {
            });
        } catch (Exception e) {
            throw new KanboardException("sending kanboard request failed", e);
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }

    }

    @Override
    public <RESPONSE_TYPE> KanboardResponse<RESPONSE_TYPE> sendRequest(KanboardRequest kanboardRequest) {
        prepareRequest(kanboardRequest);
        String jsonRequest = this.requestConverter.toJson(kanboardRequest, this.verbose);
        return sendRequest(jsonRequest);
    }

    protected void prepareRequest(KanboardRequest kanboardRequest) {
        if (kanboardRequest.getId() == null) {
            kanboardRequest.setId(UUID.randomUUID()
                                      .toString());
        }
        kanboardRequest.setJsonrpc(this.kanboardApi.getApiVersion());
    }

    protected String encodeApiToken() {
        byte[] xApiAuthTokenBytes = String.join(":", this.kanboardApi.getApiUser(), this.kanboardApi.getApiAuthToken())
                                          .getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder()
                     .encodeToString(xApiAuthTokenBytes);
    }

    private void checkHttpCode2xx(HttpURLConnection httpConnection) {
        try {
            if ((httpConnection.getResponseCode() < 200) && (httpConnection.getResponseCode() >= 300)) {
                throw new KanboardException("Failed : HTTP error code : " + httpConnection.getResponseCode());
            }
        } catch (Exception e) {
            throw new KanboardException("checkHttpCode2xx failed", e);
        }
    }

}
