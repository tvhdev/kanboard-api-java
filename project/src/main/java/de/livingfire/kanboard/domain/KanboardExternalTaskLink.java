package de.livingfire.kanboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class KanboardExternalTaskLink 
implements KanboardObject {

    private String id;
	
    private String taskId;
    
    private String url;
    
    private String dependency;
    
    private String type;
    
    private String title;
    
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

    @JsonIgnore
	@Override
	public String getObjectName() {
    	return "link";
	}

	@Override
	public void setObjectName(String objectName) {
		//	not implemented
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDependency() {
		return dependency;
	}

	public void setDependency(String dependency) {
		this.dependency = dependency;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
