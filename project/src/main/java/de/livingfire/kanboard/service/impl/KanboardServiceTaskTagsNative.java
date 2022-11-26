package de.livingfire.kanboard.service.impl;

import java.util.Map;

import de.livingfire.kanboard.domain.KanboardExternalTaskLink;
import de.livingfire.kanboard.domain.KanboardTaskTags;
import de.livingfire.kanboard.request.RequestBuilderExternalTaskLink;
import de.livingfire.kanboard.request.RequestBuilderTaskTags;
import de.livingfire.kanboard.rest.KanboardRequest;
import de.livingfire.kanboard.rest.KanboardResponse;
import de.livingfire.kanboard.service.KanboardServiceExternalTaskLink;
import de.livingfire.kanboard.service.KanboardServiceTaskTags;
import de.livingfire.kanboard.util.KanboardUtilExternalTaskLink;
import de.livingfire.kanboard.util.KanboardUtilTaskTags;

public abstract class KanboardServiceTaskTagsNative 
extends RestServiceObjectDefault<KanboardTaskTags>
implements KanboardServiceTaskTags {
	
    private final RequestBuilderTaskTags requestBuilder;
    
    private final KanboardUtilTaskTags util;
    
	public KanboardServiceTaskTagsNative() {
        this.requestBuilder = new RequestBuilderTaskTags();
        this.util = new KanboardUtilTaskTags();
	}
	
    public KanboardResponse<Integer> taskTagsUpdate(String projectId, String taskId, String[] tags) {
    		KanboardRequest request = this.requestBuilder.taskTagsUpdate(projectId,  taskId, tags);
    		return sendRequestWithFalseCheck(request);
    }
    
    public KanboardResponse<Map<String, Object>> taskTagsById(String taskId) {
        KanboardRequest request = this.requestBuilder.taskTagsById(taskId);
        return sendRequest(request);
    }
    
    public KanboardUtilTaskTags getUtil() {
		return this.util;
	}
}
