package de.livingfire.kanboard.util;

import java.util.List;

import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.converter.KanboardObjectConverter;
import de.livingfire.kanboard.domain.KanboardObject;
import de.livingfire.kanboard.domain.KanboardObjectPosition;

public abstract class KanboardUtil<T extends KanboardObject> implements KanboardObjectConverter<T>, KanboardConstant {

    public T findByObjectName(List<T> hayStack,
                              String objectName) {
        return hayStack.stream()
                       .filter(c -> c.getObjectName()
                                     .equals(objectName))
                       .findFirst()
                       .orElse(null);
    }

    public T findByObjectName(List<T> hayStack,
                              T needle) {
        return hayStack.stream()
                       .filter(c -> c.getObjectName()
                                     .equals(needle.getObjectName()))
                       .findFirst()
                       .orElse(null);
    }

    public <PO extends KanboardObjectPosition> PO findByPosition(List<PO> hayStack,
                                                                 String position) {
        return hayStack.stream()
                       .filter(c -> c.getPosition()
                                     .equals(position))
                       .findFirst()
                       .orElse(null);
    }

    public boolean containsObjectName(List<T> hayStack,
                                      String objectName) {
        T itemFound = findByObjectName(hayStack, objectName);
        return (itemFound != null);
    }

    public boolean containsObjectName(List<T> hayStack,
                                      T needle) {
        T itemFound = findByObjectName(hayStack, needle);
        return (itemFound != null);
    }

    public <PO extends KanboardObjectPosition> boolean sameObjectNamesAtPosition(List<PO> hayStack1,
                                                                                 List<PO> hayStack2) {
        if (hayStack1.size() != hayStack2.size()) {
            throw new IllegalArgumentException("haystack size unbalaced");
        }
        for (int i = 0; i < hayStack2.size(); i++) {
            final int currentPosition = i + 1;
            PO needle1 = findByPosition(hayStack1, String.valueOf(currentPosition));
            if (needle1 == null) {
                return false;
            }

            PO needle2 = findByPosition(hayStack2, String.valueOf(currentPosition));
            if (needle2 == null) {
                return false;
            }

            if (!needle1.getObjectName()
                        .equals(needle2.getObjectName())) {
                return false;
            }
        }
        return true;
    }
}
