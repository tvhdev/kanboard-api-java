package de.livingfire.kanboard.rest;

public class KanboardRequestWithoutParams {

    private String jsonrpc;
    private String method;
    private String id;

    public KanboardRequestWithoutParams() {
        super();
    }

    public KanboardRequestWithoutParams(String method) {
        this();
        this.method = method;
    }

    @Override
    public String toString() {
        return "KanboardRequestWithoutParams [jsonrpc=" + this.jsonrpc + ", method=" + this.method + ", id=" + this.id
                + "]";
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

}
