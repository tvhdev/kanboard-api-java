package de.livingfire.kanboard.rest;

import java.util.LinkedHashMap;

public class KanboardRequest {

    private String jsonrpc;
    private String method;
    private String id;
    private LinkedHashMap<String, String> params;

    public KanboardRequest() {
        super();
        this.params = new LinkedHashMap<>();
    }

    public KanboardRequest(String method) {
        this();
        this.method = method;
    }

    @Override
    public String toString() {
        return "KanboardRequest [jsonrpc=" + this.jsonrpc + ", method=" + this.method + ", id=" + this.id + ", params="
                + this.params + "]";
    }

    public String getJsonrpc() {
        return this.jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkedHashMap<String, String> getParams() {
        return this.params;
    }

    public void setParams(LinkedHashMap<String, String> params) {
        this.params = params;
    }

}
