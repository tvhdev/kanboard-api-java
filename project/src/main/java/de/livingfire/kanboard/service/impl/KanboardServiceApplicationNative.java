package de.livingfire.kanboard.service.impl;

import de.livingfire.kanboard.request.RequestBuilderApplication;
import de.livingfire.kanboard.rest.KanboardResponse;
import de.livingfire.kanboard.service.KanboardServiceApplication;

public abstract class KanboardServiceApplicationNative extends RestServiceDefault
        implements KanboardServiceApplication {

    private final RequestBuilderApplication requestBuilder;

    public KanboardServiceApplicationNative() {
        super();
        this.requestBuilder = new RequestBuilderApplication();
    }

    protected KanboardResponse<String> applicationGetTimezone() {
        String json = this.requestBuilder.applicationGetTimezone();
        return sendRequest(json);
    }
}
