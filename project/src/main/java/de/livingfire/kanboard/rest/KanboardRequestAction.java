package de.livingfire.kanboard.rest;

import de.livingfire.kanboard.domain.KanboardAction;

public class KanboardRequestAction {

    private String jsonrpc;
    private String method;
    private String id;
    private KanboardAction params;

    public KanboardRequestAction() {
        super();
    }

    public KanboardRequestAction(String method) {
        this();
        this.method = method;
    }

    @Override
    public String toString() {
        return "KanboardRequestAction [jsonrpc=" + this.jsonrpc + ", method=" + this.method + ", id=" + this.id
                + ", params=" + this.params + "]";
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

    public KanboardAction getParams() {
        return this.params;
    }

    public void setParams(KanboardAction params) {
        this.params = params;
    }

}
