package de.livingfire.kanboard.service;

import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardObject;
import de.livingfire.kanboard.util.KanboardUtil;

public interface RestServiceObject<KANBOARD_OBJECT extends KanboardObject> extends RestService {

    KANBOARD_OBJECT update(KANBOARD_OBJECT kanboardObject);

    KANBOARD_OBJECT update(Map<String, String> map);

    KANBOARD_OBJECT create(KANBOARD_OBJECT kanboardObject);

    KANBOARD_OBJECT getById(String id);

    default KANBOARD_OBJECT getById(Integer id) {
        return getById(String.valueOf(id));
    }

    default KANBOARD_OBJECT getById(KANBOARD_OBJECT kanboardObject) {
        return getById(kanboardObject.getId());
    }

    KANBOARD_OBJECT getByObjectName(KANBOARD_OBJECT kanboardObject);

    List<String> enshureParams();

    KanboardUtil<KANBOARD_OBJECT> getUtil();

    KANBOARD_OBJECT enshure(KANBOARD_OBJECT kanboardObjectEnshure);

}