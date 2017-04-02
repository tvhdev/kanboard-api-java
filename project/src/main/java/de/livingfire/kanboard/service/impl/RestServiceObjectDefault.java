package de.livingfire.kanboard.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.livingfire.kanboard.domain.KanboardObject;
import de.livingfire.kanboard.exception.KanboardException;
import de.livingfire.kanboard.service.RestServiceObject;

public abstract class RestServiceObjectDefault<KANBOARD_OBJECT extends KanboardObject> extends RestServiceDefault
        implements RestServiceObject<KANBOARD_OBJECT> {

    @Override
    public KANBOARD_OBJECT enshure(KANBOARD_OBJECT kanboardObjectEnshure) {
        if (kanboardObjectEnshure.getObjectName() == null) {
            throw new KanboardException("enshure needs a objectName: " + kanboardObjectEnshure.toString());
        }
        KANBOARD_OBJECT kanboardLoaded = getByObjectName(kanboardObjectEnshure);
        if (kanboardLoaded == null) {
            return create(kanboardObjectEnshure);
        }

        Map<String, String> hashmapEnshure = getUtil().convertToMap(kanboardObjectEnshure);
        Map<String, String> hashmapLoaded = getUtil().convertToMap(kanboardLoaded);

        List<Boolean> differencesList = hashmapEnshure.keySet()
                                                      .stream()
                                                      .filter(k -> enshureParams().contains(k))
                                                      .map(k -> {
                                                          String expected = hashmapEnshure.get(k);
                                                          String actual = hashmapLoaded.get(k);
                                                          if (expected == null) {
                                                              return false;
                                                          }
                                                          return !expected.equals(actual);
                                                      })
                                                      .filter(b -> b == true)
                                                      .collect(Collectors.toList());
        if (differencesList.isEmpty()) {
            return kanboardLoaded;
        }

        return update(hashmapEnshure);
    }

    @Override
    public final KANBOARD_OBJECT update(KANBOARD_OBJECT kanboardObject) {
        return update(getUtil().convertToMap(kanboardObject));
    }

}
