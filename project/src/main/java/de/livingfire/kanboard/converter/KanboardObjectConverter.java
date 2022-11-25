package de.livingfire.kanboard.converter;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import de.livingfire.kanboard.domain.KanboardObject;

public interface KanboardObjectConverter<T extends KanboardObject> {

    public Map<String, Object> convertToMap(T kanboardObject);

    public T convertToObject(Map<String, Object> h);

    default ArrayList<T> convertToObjectList(ArrayList<Map<String, Object>> hashMapList) {
        if ((hashMapList == null) || hashMapList.isEmpty()) {
            return new ArrayList<>();
        }
        return hashMapList.stream()
                          .map(h -> convertToObject(h))
                          .collect(Collectors.toCollection(ArrayList::new));
    }
}
