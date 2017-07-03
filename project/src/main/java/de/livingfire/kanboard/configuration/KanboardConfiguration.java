package de.livingfire.kanboard.configuration;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class KanboardConfiguration {

    @JsonDeserialize(as = KanboardApiDefault.class)
    private KanboardApi kanboardApi;

    @JsonDeserialize(as = KanboardSetupDefault.class)
    private KanboardSetup kanboardSetup;

    public KanboardConfiguration() {
        super();
    }

    public KanboardApi getKanboardApi() {
        return kanboardApi;
    }

    public void setKanboardApi(KanboardApi kanboardApi) {
        this.kanboardApi = kanboardApi;
    }

    public KanboardSetup getKanboardSetup() {
        return kanboardSetup;
    }

    public void setKanboardSetup(KanboardSetup kanboardSetup) {
        this.kanboardSetup = kanboardSetup;
    }

}
