package de.livingfire.kanboard.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.livingfire.kanboard.domain.KanboardProject;

public class KanboardServiceProjectDefault extends KanboardServiceProjectNative {

    public KanboardServiceProjectDefault() {
        super();
    }

    @Override
    public KanboardProject getByObjectName(String projectName) {
        return getUtil().convertToObject(projectByName(projectName).getResult());
    }

    @Override
    public KanboardProject getByObjectName(KanboardProject project) {
        return getByObjectName(project.getName());
    }

    @Override
    public KanboardProject getById(String id) {
        return getUtil().convertToObject(projectGetById(id).getResult());
    }

    @Override
    public KanboardProject create(KanboardProject project) {
        Integer responseId = projectCreate(project.getName(), getUtil().convertToMap(project)).getResult();
        return getById(responseId);
    }

    @Override
    public KanboardProject update(Map<String, String> hashMap) {
        projectUpdate(hashMap).getResult();
        return getById(hashMap.get(PARAM_ID));
    }

    @Override
    public void addUser(String projectId,
                        String userId,
                        String role) {
        projectAddUser(projectId, userId, role);
    }

    @Override
    public List<KanboardProject> getAll() {
        return projectGetAll().getResult()
                              .stream()
                              .map(e -> getUtil().convertToObject(e))
                              .collect(Collectors.toList());
    }
}
