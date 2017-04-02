package de.livingfire.kanboard.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardColumn;
import de.livingfire.kanboard.exception.KanboardException;

public class KanboardServiceColumnDefault extends KanboardServiceColumnNative {

    public KanboardServiceColumnDefault() {
        super();
    }

    @Override
    public KanboardColumn add(KanboardColumn column) {
        Integer responseId = columnAdd(
                column.getProjectId(),
                    column.getTitle(),
                    column.getTaskLimit(),
                    column.getDescription()).getResult();
        return getById(responseId);
    }

    @Override
    public ArrayList<KanboardColumn> getAll(String projectId) {
        return getUtil().convertToObjectList(columnGetAll(projectId).getResult());
    }

    @Override
    public void enshureOrder(List<KanboardColumn> columnsExpected) {
        if ((columnsExpected == null) || columnsExpected.isEmpty()) {
            throw new KanboardException("Argument columns must not be NULL or empty");
        }
        String projectId = columnsExpected.get(0)
                                          .getProjectId();

        ArrayList<KanboardColumn> columnsAvailable = getAll(projectId);
        columnsAvailable.stream()
                        .filter(c -> getUtil().containsObjectName(columnsExpected, c) == false)
                        .forEach(c -> remove(c));

        ArrayList<KanboardColumn> columnsAfterRemove = getAll(projectId);
        columnsExpected.stream()
                       .filter(c -> getUtil().containsObjectName(columnsAfterRemove, c) == false)
                       .forEach(c -> add(c));

        ArrayList<KanboardColumn> columnsUnordered = getAll(projectId);

        if (getUtil().sameObjectNamesAtPosition(columnsExpected, columnsUnordered)) {
            return;
        }

        for (int i = 0; i < columnsExpected.size(); i++) {
            final String currentPosition = String.valueOf(i + 1);
            KanboardColumn columnExpected = getUtil().findByPosition(columnsExpected, currentPosition);
            if (columnExpected.getId() == null) {
                KanboardColumn columnActual = getUtil().findByObjectName(columnsUnordered, columnExpected);
                columnExpected.setId(columnActual.getId());
            }
            changePosition(columnExpected);
        }
    }

    @Override
    public boolean changePosition(KanboardColumn column) {
        return columnChangePosition(column.getProjectId(), column.getId(), column.getPosition()).getResult();
    }

    @Override
    public boolean remove(KanboardColumn column) {
        return columnRemove(column.getId()).getResult();
    }

    @Override
    public KanboardColumn getByObjectName(KanboardColumn column) {
        return getUtil().findByObjectName(getAll(column.getProjectId()), column);
    }

    @Override
    public KanboardColumn getById(String columnId) {
        Map<String, String> map = columnGetById(columnId).getResult();
        return getUtil().convertToObject(map);
    }

    @Override
    public KanboardColumn create(KanboardColumn column) {
        return add(column);
    }

    @Override
    public KanboardColumn update(Map<String, String> map) {
        columnUpdate(map).getResult();
        return getById(map.get(PARAM_ID));
    }

}
