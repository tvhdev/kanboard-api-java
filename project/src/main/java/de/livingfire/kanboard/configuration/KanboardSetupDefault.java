package de.livingfire.kanboard.configuration;

import java.util.List;

import de.livingfire.kanboard.domain.KanboardProject;
import de.livingfire.kanboard.domain.KanboardUser;

public class KanboardSetupDefault implements KanboardSetup {

    private KanboardUser user;
    private List<KanboardProject> projects;

    public KanboardSetupDefault() {
        super();
    }

    public KanboardSetupDefault(KanboardUser user,
                                List<KanboardProject> projects) {
        super();
        this.user = user;
        this.projects = projects;
    }

    public KanboardUser getUser() {
        return user;
    }

    public void setUser(KanboardUser user) {
        this.user = user;
    }

    public List<KanboardProject> getProjects() {
        return projects;
    }

    public void setProjects(List<KanboardProject> projects) {
        this.projects = projects;
    }

}
