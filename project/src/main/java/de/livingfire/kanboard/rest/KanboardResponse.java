package de.livingfire.kanboard.rest;

public class KanboardResponse<RESPONSE_TYPE> {

    private String jsonrpc;
    private String id;
    private RESPONSE_TYPE result;

    public KanboardResponse() {
        super();
    }

    @Override
    public String toString() {
        return "KanboardResponse [jsonrpc=" + this.jsonrpc + ", id=" + this.id + ", result=" + this.result + "]";
    }

    public String getJsonrpc() {
        return this.jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RESPONSE_TYPE getResult() {
        return this.result;
    }

    public void setResult(RESPONSE_TYPE result) {
        this.result = result;
    }

}
