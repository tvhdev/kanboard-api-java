package de.livingfire.kanboard.service.impl;

import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardSwimlane;

public class KanboardServiceSwimlaneDefault extends KanboardServiceSwimlaneNative {

    public KanboardServiceSwimlaneDefault() {
        super();
    }

    @Override
    public List<KanboardSwimlane> getAll(String projectId) {
        return getUtil().convertToObjectList(swimlaneGetAll(projectId).getResult());
    }

    @Override
    public KanboardSwimlane getByObjectName(KanboardSwimlane swimlane) {
        return getUtil().findByObjectName(getAll(swimlane.getProjectId()), swimlane);
    }

    @Override
    public KanboardSwimlane getById(String id) {
        return getUtil().convertToObject(swimlaneGetById(id).getResult());
    }

    @Override
    public KanboardSwimlane create(KanboardSwimlane swimlane) {
        return add(swimlane);
    }

    @Override
    public KanboardSwimlane add(KanboardSwimlane swimlane) {
        Integer responseId = swimlaneAdd(
                swimlane.getProjectId(),
                    swimlane.getName(),
                    swimlane.getDescription()).getResult();
        return getById(responseId);
    }

    @Override
    public void disable(KanboardSwimlane swimlane) {
        swimlaneDisable(swimlane.getProjectId(), swimlane.getId());
    }

    @Override
    public void enable(KanboardSwimlane swimlane) {
        swimlaneEnable(swimlane.getProjectId(), swimlane.getId());
    }

    @Override
    public void changePosition(KanboardSwimlane swimlane) {
        swimlaneChangePosition(swimlane.getProjectId(), swimlane.getId(), swimlane.getPosition());
    }

    @Override
    public KanboardSwimlane update(Map<String, String> hashMap) {
        hashMap.put(PARAM_SWIMLANE_ID, hashMap.get(PARAM_ID));
        swimlaneUpdate(hashMap).getResult();
        return getById(hashMap.get(PARAM_ID));
    }

    @Override
    public KanboardSwimlane getByName(String projectId,
                                      String swimlaneName) {
        return getUtil().convertToObject(swimlaneGetByName(projectId, swimlaneName).getResult());
    }

    @Override
    public KanboardSwimlane getDefault(String projectId) {
        return getUtil().convertToObject(swimlaneGetDefault(projectId).getResult());
    }

    @Override
    public void remove(KanboardSwimlane swimlane) {
        swimlaneRemove(swimlane.getProjectId(), swimlane.getId());
    }

}
