package de.livingfire.kanboard.domain;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import de.livingfire.kanboard.constants.KanboardConstant;

@JsonPropertyOrder({ "projectId", "eventName", "target", "actionName", "params" })
public class KanboardAction implements KanboardObject, KanboardConstant {

    @JsonIgnore
    private String id;

    @JsonProperty(PARAM_PROJECT_ID)
    private String projectId;

    @JsonProperty(PARAM_EVENT_NAME)
    private String eventName;

    @JsonProperty(PARAM_ACTION_NAME)
    private String actionName;

    private LinkedHashMap<String, String> params;

    public KanboardAction() {
        super();
        this.params = new LinkedHashMap<>();
    }

    public boolean equalSimple(KanboardAction other) {
        if ((getProjectId() == null) || (getEventName() == null) || (getActionName() == null)) {
            throw new IllegalArgumentException("projectId, eventName and actionName must not be NULL");
        }
        if (!getProjectId().equals(other.getProjectId())) {
            return false;
        }
        if (!getEventName().equals(other.getEventName())) {
            return false;
        }
        if (!getActionName().equals(other.getActionName())) {
            return false;
        }
        if (getParams() == null) {
            return true;
        }

        return getParams().keySet()
                          .stream()
                          .map(k -> {
                              String attributeThis = getParams().get(k);
                              String attributeOther = getParams().get(k);
                              if (!attributeThis.equals(attributeOther)) {
                                  return false;
                              }
                              return true;
                          })
                          .filter(b -> b == false)
                          .collect(Collectors.toList())
                          .isEmpty();
    }

    @JsonIgnore
    @Override
    public String getObjectName() {
        throw new RuntimeException("KanboardActions have no ObjectName");
    }

    @Override
    public void setObjectName(String objectName) {
        throw new RuntimeException("implement me");
    }

    @Override
    public String toString() {
        return "KanboardAction [id=" + this.id + ", projectId=" + this.projectId + ", eventName=" + this.eventName
                + ", actionName=" + this.actionName + ", params=" + this.params + "]";
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getActionName() {
        return this.actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public LinkedHashMap<String, String> getParams() {
        return this.params;
    }

    public void setParams(LinkedHashMap<String, String> params) {
        this.params = params;
    }

}
