package de.livingfire.kanboard.service;

import java.util.List;

import de.livingfire.kanboard.domain.KanboardProject;
import de.livingfire.kanboard.domain.KanboardUser;
import de.livingfire.kanboard.exception.KanboardException;

public interface KanboardServiceProject extends RestServiceObject<KanboardProject> {

    default void addUser(KanboardProject project,
                         KanboardUser user,
                         String role) {
        if ((project == null) || (project.getId() == null)) {
            throw new KanboardException("project id must not be null: " + project);
        }
        if ((user == null) || (user.getId() == null)) {
            throw new KanboardException("user id must not be null: " + user);
        }
        addUser(project.getId(), user.getId(), role);
    }

    void addUser(String projectId,
                 String userId,
                 String role);

    List<KanboardProject> getAll();

    KanboardProject getByObjectName(String objectName);
}
