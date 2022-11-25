package de.livingfire.kanboard.service.impl;

import java.util.Map;

import de.livingfire.kanboard.domain.KanboardExternalTaskLink;
import de.livingfire.kanboard.request.RequestBuilderExternalTaskLink;
import de.livingfire.kanboard.rest.KanboardRequest;
import de.livingfire.kanboard.rest.KanboardResponse;
import de.livingfire.kanboard.service.KanboardServiceExternalTaskLink;
import de.livingfire.kanboard.util.KanboardUtilExternalTaskLink;

public abstract class KanboardServiceExternalTaskLinkNative 
extends RestServiceObjectDefault<KanboardExternalTaskLink>
implements KanboardServiceExternalTaskLink {
	
    private final RequestBuilderExternalTaskLink requestBuilder;
    
    private final KanboardUtilExternalTaskLink util;
    
	public KanboardServiceExternalTaskLinkNative() {
        this.requestBuilder = new RequestBuilderExternalTaskLink();
        this.util = new KanboardUtilExternalTaskLink();
	}
	
    public KanboardResponse<Integer> externalTaskLinkCreate(String taskId, String url, String dependency, Map<String, Object> paramsOptional) {
    		KanboardRequest request = this.requestBuilder.externalTaskLinkCreate(taskId, url, dependency, paramsOptional);
    		return sendRequestWithFalseCheck(request);
    }

	public KanboardUtilExternalTaskLink getUtil() {
		return this.util;
	}
}
