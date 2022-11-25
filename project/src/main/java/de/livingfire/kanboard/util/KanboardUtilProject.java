package de.livingfire.kanboard.util;

import java.util.HashMap;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardProject;

public class KanboardUtilProject extends KanboardUtil<KanboardProject> {

    @Override
    public Map<String, String> convertToMap(KanboardProject project) {
        if (project == null) {
            return null;
        }
        Map<String, String> h = new HashMap<>();
        h.put(PARAM_ID, project.getId());
        h.put(PARAM_NAME, project.getName());
        h.put(PARAM_DESCRIPTION, project.getDescription());
        h.put(PARAM_IS_ACTIVE, project.getIsActive());
        h.put(PARAM_IDENTIFIER, project.getIdentifier());
        h.put(PARAM_OWNER_ID, project.getOwnerId());
        return h;
    }

    @Override
    public KanboardProject convertToObject(Map<String, String> h) {
        if (h == null) {
            return null;
        }
        KanboardProject project = new KanboardProject();
        project.setId(String.valueOf(h.get(PARAM_ID)));
        project.setName(h.get(PARAM_NAME));
        project.setDescription(h.get(PARAM_DESCRIPTION));
        project.setIsActive(String.valueOf(h.get(PARAM_IS_ACTIVE)));
        project.setIdentifier(h.get(PARAM_IDENTIFIER));
        project.setOwnerId(String.valueOf(h.get(PARAM_OWNER_ID)));
        return project;
    }

}
