package de.livingfire.kanboard.configuration;

import java.util.List;

import de.livingfire.kanboard.domain.KanboardProject;
import de.livingfire.kanboard.domain.KanboardUser;

public interface KanboardSetup {

    void setProjects(List<KanboardProject> projects);

    List<KanboardProject> getProjects();

    void setUser(KanboardUser user);

    KanboardUser getUser();

}