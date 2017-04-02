package de.livingfire.kanboard.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardProject;
import de.livingfire.kanboard.domain.KanboardTask;

public interface KanboardServiceTask extends RestServiceObject<KanboardTask> {

    public static final int DEADLINE_LIMIT_FIRST = 1;
    public static final int DEADLINE_LIMIT_SECOND = 3;

    void sortByObjectName(KanboardProject project);

    void sortByObjectName(String projectId);

    void moveToPostion(String projectId,
                       String taskId,
                       String columnId,
                       String position,
                       String swimlaneId);

    void moveToPostion(KanboardTask task,
                       String position);

    ArrayList<KanboardTask> getAll(String projectId,
                                   String statusId);

    List<KanboardTask> getAllActive(String projectId);

    List<KanboardTask> getAllActive(KanboardProject projectCreated);

    List<KanboardTask> getAllActive(KanboardTask task);

    List<KanboardTask> getAllActiveWithDueDate(String projectId);

    List<KanboardTask> getAllActiveWithDueDate(KanboardProject project);

    List<KanboardTask> getAllInactive(String projectId);

    List<KanboardTask> getAllInactive(KanboardTask task);

    Map<Date, List<KanboardTask>> getByDeadline(String projectId);

    Map<Date, List<KanboardTask>> getByDeadline(KanboardProject project);

    Map<Date, List<KanboardTask>> getByDeadline(String projectId,
                                                List<Date> deadlineDates);

    Map<Date, List<KanboardTask>> getByDeadline(KanboardProject project,
                                                List<Date> deadlineDates);

    List<Date> getDeadlineDefault();

    void setServiceApplication(KanboardServiceApplication serviceApplication);

}
