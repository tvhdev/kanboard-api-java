package de.livingfire.kanboard.service;

import java.util.ArrayList;
import java.util.List;

import de.livingfire.kanboard.domain.KanboardColumn;

public interface KanboardServiceColumn extends RestServiceObject<KanboardColumn> {

    void enshureOrder(List<KanboardColumn> columnsExpected);

    ArrayList<KanboardColumn> getAll(String projectId);

    boolean remove(KanboardColumn column);

    KanboardColumn add(KanboardColumn column);

    boolean changePosition(KanboardColumn kanboardColumn);

}
