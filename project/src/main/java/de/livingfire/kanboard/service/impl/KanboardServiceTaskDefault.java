package de.livingfire.kanboard.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.livingfire.kanboard.domain.KanboardProject;
import de.livingfire.kanboard.domain.KanboardTask;
import de.livingfire.kanboard.exception.KanboardException;
import de.livingfire.kanboard.service.KanboardServiceApplication;
import de.livingfire.kanboard.util.KanboardUtilDate;

public class KanboardServiceTaskDefault extends KanboardServiceTaskNative {

    private KanboardServiceApplication serviceApplication;

    private KanboardUtilDate utilDate;

    public KanboardServiceTaskDefault() {
        super();
    }

    public KanboardServiceTaskDefault(KanboardUtilDate utilDate) {
        super();
        this.utilDate = utilDate;
    }

    private KanboardUtilDate getLazyUtilDate() {
        if (this.utilDate == null) {
            this.utilDate = this.serviceApplication.getUtilDate();
        }
        return this.utilDate;
    }

    @Override
    public void setServiceApplication(KanboardServiceApplication serviceApplication) {
        this.serviceApplication = serviceApplication;
    }

    @Override
    public KanboardTask create(KanboardTask task) {
        Map<String, String> paramsOptional = getUtil().convertToMap(task);
        Integer taskId = taskCreate(task.getTitle(), task.getProjectId(), paramsOptional).getResult();
        return getById(taskId);
    }

    @Override
    public KanboardTask getById(String taskId) {
        return getUtil().convertToObject(taskById(taskId).getResult());
    }

    @Override
    public List<KanboardTask> getAllInactive(KanboardTask task) {
        return getAllInactive(task.getProjectId());
    }

    @Override
    public List<KanboardTask> getAllInactive(String projectId) {
        return getUtil().convertToObjectList(taskGetInactive(projectId).getResult());
    }

    @Override
    public List<KanboardTask> getAllActive(KanboardProject project) {
        return getAllActive(project.getId());
    }

    @Override
    public List<KanboardTask> getAllActive(KanboardTask task) {
        return getAllActive(task.getProjectId());
    }

    @Override
    public List<KanboardTask> getAllActive(String projectId) {
        return getUtil().convertToObjectList(taskGetActive(projectId).getResult());
    }

    @Override
    public ArrayList<KanboardTask> getAll(String projectId,
                                          String statusId) {
        return getUtil().convertToObjectList(taskGetAll(projectId, statusId).getResult());
    }

    @Override
    public KanboardTask getByObjectName(KanboardTask task) {
        return getUtil().findByObjectName(getAllActive(task), task);
    }

    @Override
    public KanboardTask update(Map<String, String> hashMap) {
        String taskId = hashMap.get(PARAM_ID);
        taskUpdate(taskId, hashMap);
        return getById(taskId);
    }

    @Override
    public Map<Date, List<KanboardTask>> getByDeadline(String projectId) {
        return getByDeadline(projectId, getDeadlineDefault());
    }

    @Override
    public List<Date> getDeadlineDefault() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(getLazyUtilDate().getTimeZone());
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        List<Date> deadlines = new ArrayList<>();
        deadlines.add(calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, DEADLINE_LIMIT_FIRST);
        deadlines.add(calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, DEADLINE_LIMIT_SECOND - DEADLINE_LIMIT_FIRST);
        deadlines.add(calendar.getTime());

        return deadlines;
    }

    @Override
    public Map<Date, List<KanboardTask>> getByDeadline(KanboardProject project) {
        return getByDeadline(project.getId());
    }

    @Override
    public Map<Date, List<KanboardTask>> getByDeadline(String projectId,
                                                       List<Date> deadlineDates) {
        if ((deadlineDates == null) || deadlineDates.isEmpty()) {
            throw new KanboardException("DeadlineDates must not be NULL or EMPTY");
        }
        List<KanboardTask> tasks = getAllActiveWithDueDate(projectId);

        Map<Date, List<KanboardTask>> deadline = new LinkedHashMap<>();

        deadlineDates.stream()
                     .sorted()
                     .forEach(date -> {
                         deadline.put(date, new ArrayList<>());
                         tasks.stream()
                              .forEach(e -> {
                                  Date compareDate = getLazyUtilDate().convertToDateJava(e.getDateDue());
                                  if (date.after(compareDate)) {
                                      deadline.get(date)
                                              .add(e);
                                  }
                              });

                         deadline.get(date)
                                 .stream()
                                 .forEach(e -> tasks.remove(e));
                     });

        return deadline;
    }

    @Override
    public Map<Date, List<KanboardTask>> getByDeadline(KanboardProject project,
                                                       List<Date> deadlineDates) {
        return getByDeadline(project.getId(), deadlineDates);
    }

    @Override
    public List<KanboardTask> getAllActiveWithDueDate(String projectId) {
        return getAllActive(projectId).stream()
                                      .filter(e -> !"0".equals(e.getDateDue()))
                                      .collect(Collectors.toList());
    }

    @Override
    public List<KanboardTask> getAllActiveWithDueDate(KanboardProject project) {
        return getAllActiveWithDueDate(project.getId());
    }

    @Override
    public void moveToPostion(KanboardTask task,
                              String position) {
        moveToPostion(task.getProjectId(), task.getId(), task.getColumnId(), position, task.getSwimlaneId());
    }

    @Override
    public void moveToPostion(String projectId,
                              String taskId,
                              String columnId,
                              String position,
                              String swimlaneId) {
        Map<String, String> paramsOptional = new HashMap<>();
        paramsOptional.put(PARAM_SWIMLANE_ID, swimlaneId);
        taskMovePosition(projectId, taskId, columnId, position, paramsOptional);
    }

    @Override
    public void sortByObjectName(KanboardProject project) {
        sortByObjectName(project.getId());
    }

    @Override
    public void sortByObjectName(String projectId) {
        Map<String, List<KanboardTask>> tasksBoard = getAllActive(projectId).stream()
                                                                            .collect(
                                                                                    Collectors.groupingBy(
                                                                                            KanboardTask::getSwimlaneId));
        for (Map.Entry<String, List<KanboardTask>> entry : tasksBoard.entrySet()) {
            sortByObjectNameInSwimlane(entry.getValue());
        }
    }

    private void sortByObjectNameInSwimlane(List<KanboardTask> tasksSwimlane) {
        Map<String, List<KanboardTask>> tasksColumn = tasksSwimlane.stream()
                                                                   .collect(
                                                                           Collectors.groupingBy(
                                                                                   KanboardTask::getColumnId));
        for (Map.Entry<String, List<KanboardTask>> entry : tasksColumn.entrySet()) {
            sortByObjectNameInColumn(entry.getValue());
        }
    }

    private void sortByObjectNameInColumn(List<KanboardTask> tasks) {
        ArrayList<KanboardTask> sortedTasks = tasks.stream()
                                                   .sorted((e1,
                                                            e2) -> e1.getObjectName()
                                                                     .compareTo(e2.getObjectName()))
                                                   .collect(Collectors.toCollection(ArrayList::new));
        for (int i = 0; i < sortedTasks.size(); i++) {
            KanboardTask task = getById(sortedTasks.get(i)
                                                   .getId());
            String positionNew = String.valueOf(i + 1);
            if (!task.getPosition()
                     .equals(positionNew)) {
                moveToPostion(task, positionNew);
            }
        }

    }

}
