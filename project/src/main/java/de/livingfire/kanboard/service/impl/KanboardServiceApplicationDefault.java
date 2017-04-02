package de.livingfire.kanboard.service.impl;

import de.livingfire.kanboard.util.KanboardUtilDate;

public class KanboardServiceApplicationDefault extends KanboardServiceApplicationNative {

    @Override
    public String getTimezone() {
        return applicationGetTimezone().getResult();
    }

    @Override
    public KanboardUtilDate getUtilDate() {
        return new KanboardUtilDate(getTimezone());
    }
}
