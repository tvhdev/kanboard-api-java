package de.livingfire.kanboard.service;

import de.livingfire.kanboard.util.KanboardUtilDate;

public interface KanboardServiceApplication extends RestService {

    KanboardUtilDate getUtilDate();

    String getTimezone();
}
