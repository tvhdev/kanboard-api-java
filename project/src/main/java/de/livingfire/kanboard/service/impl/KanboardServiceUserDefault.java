package de.livingfire.kanboard.service.impl;

import java.util.Map;

import de.livingfire.kanboard.domain.KanboardUser;

public class KanboardServiceUserDefault extends KanboardServiceUserNative {

    public KanboardServiceUserDefault() {
        super();
    }

    @Override
    public KanboardUser getByObjectName(KanboardUser kanboardObject) {
        Map<String, String> resultMap = userByUsername(kanboardObject.getUsername()).getResult();
        return getUtil().convertToObject(resultMap);
    }

    @Override
    public KanboardUser getById(String userId) {
        return getUtil().convertToObject(userGetById(userId).getResult());
    }

    @Override
    public KanboardUser create(KanboardUser user) {
        Integer responseId = userCreate(getUtil().convertToMap(user)).getResult();
        return getById(responseId);
    }

    @Override
    public KanboardUser update(Map<String, String> map) {
        userUpdate(map);
        return getById(map.get(PARAM_ID));
    }

}
