package de.livingfire.kanboard.service;

import java.util.List;

import de.livingfire.kanboard.domain.KanboardProject;
import de.livingfire.kanboard.domain.KanboardSwimlane;
import de.livingfire.kanboard.exception.KanboardException;

public interface KanboardServiceSwimlane extends RestServiceObject<KanboardSwimlane> {

    List<KanboardSwimlane> getAll(String projectId);

    default List<KanboardSwimlane> getAll(KanboardProject project) {
        if ((project == null) || (project.getId() == null)) {
            throw new KanboardException("project id must not be null: " + project);
        }
        return getAll(project.getId());
    }

    KanboardSwimlane add(KanboardSwimlane swimlane);

    void enable(KanboardSwimlane swimlane);

    void disable(KanboardSwimlane swimlane);

    void changePosition(KanboardSwimlane swimlane);

    KanboardSwimlane getByName(String projectId,
                               String swimlaneName);

    KanboardSwimlane getDefault(String projectId);

    void remove(KanboardSwimlane swimlane);

}
