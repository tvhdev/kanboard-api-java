package de.livingfire.kanboard.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import de.livingfire.kanboard.configuration.KanboardApi;
import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.domain.KanboardProject;
import de.livingfire.kanboard.domain.KanboardSwimlane;
import de.livingfire.kanboard.domain.KanboardTask;
import de.livingfire.kanboard.exception.KanboardException;

public class KanboardServiceTaskIntegrationTest implements KanboardConstant {

    private KanboardService service;
    private SimpleDateFormat dateFormatOffset;

    @Before
    public void setUp() throws UnknownHostException {
        KanboardApi kanboardApi = mock(KanboardApi.class);
        when(kanboardApi.getApiVersion()).thenReturn("2.0");
        when(kanboardApi.getApiUrl()).thenReturn("http://172.19.1.1:81/jsonrpc.php");
        when(kanboardApi.getApiUser()).thenReturn("jsonrpc");
        when(kanboardApi.getApiAuthToken()).thenReturn("19ffd9709d03ce50675c3a43d1c49c1ac207f4bc45f06c5b2701fbdf8929");
        when(kanboardApi.getApiHeader()).thenReturn("X-API-Auth");

        this.service = new KanboardService();
        this.service.setApi(kanboardApi);
        this.service.setVerbose(KanboardConstant.SHOW_HTTP_REQUESTS);

        this.dateFormatOffset = new SimpleDateFormat("'offset' Z 'timezone' z");
    }

    @Test
    public void testTaskCreate() {
        final String projectNameExpected = "project" + UUID.randomUUID();
        final String projectDescriptionExpected = "description" + UUID.randomUUID();

        KanboardProject projectTemplate = new KanboardProject();
        projectTemplate.setObjectName(projectNameExpected);
        projectTemplate.setDescription(projectDescriptionExpected);

        KanboardProject projectCreated = this.service.project()
                                                     .create(projectTemplate);
        assertThat(projectCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(projectCreated.getId()), is(greaterThan(0)));

        KanboardTask taskTemplate = new KanboardTask();
        taskTemplate.setTitle("task 1");
        taskTemplate.setProjectId(projectCreated.getId());

        KanboardTask taskActual = this.service.task()
                                              .create(taskTemplate);

        assertThat(taskActual, is(not(nullValue())));
        assertThat(Integer.valueOf(taskActual.getId()), is(greaterThan(0)));
    }

    @Test
    public void testTaskGetById() {
        final String projectNameExpected = "project" + UUID.randomUUID();
        final String projectDescriptionExpected = "description" + UUID.randomUUID();

        KanboardProject projectTemplate = new KanboardProject();
        projectTemplate.setObjectName(projectNameExpected);
        projectTemplate.setDescription(projectDescriptionExpected);

        KanboardProject projectCreated = this.service.project()
                                                     .create(projectTemplate);
        assertThat(projectCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(projectCreated.getId()), is(greaterThan(0)));

        KanboardTask taskTemplate = new KanboardTask();
        taskTemplate.setTitle("task 1");
        taskTemplate.setProjectId(projectCreated.getId());
        KanboardTask taskExpected = this.service.task()
                                                .create(taskTemplate);

        KanboardTask taskActual = this.service.task()
                                              .getById(taskExpected.getId());

        assertThat(taskActual, is(not(nullValue())));
        assertThat(taskActual.getTitle(), equalTo(taskTemplate.getTitle()));
        assertThat(taskActual.getProjectId(), equalTo(projectCreated.getId()));
    }

    @Test
    public void testTaskGetAll() {
        final String projectNameExpected = "project" + UUID.randomUUID();
        final String projectDescriptionExpected = "description" + UUID.randomUUID();

        KanboardProject projectTemplate = new KanboardProject();
        projectTemplate.setObjectName(projectNameExpected);
        projectTemplate.setDescription(projectDescriptionExpected);

        KanboardProject projectCreated = this.service.project()
                                                     .create(projectTemplate);
        assertThat(projectCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(projectCreated.getId()), is(greaterThan(0)));

        final String projectIdExpected = projectCreated.getId();

        List<String> taskIds = IntStream.rangeClosed(1, 10)
                                        .mapToObj(i -> new KanboardTask(projectIdExpected, "task " + i))
                                        .map(task -> this.service.task()
                                                                 .create(task))
                                        .map(task -> task.getId())
                                        .collect(Collectors.toList());

        assertThat(taskIds, is(not(nullValue())));
        assertThat(taskIds.size(), is(10));

        String statusIdExpected = "1";

        List<KanboardTask> tasks = this.service.task()
                                               .getAll(projectIdExpected, statusIdExpected);
        assertThat(tasks, is(not(nullValue())));
        assertThat(tasks.size(), is(10));

        tasks.stream()
             .map(t -> t.getId())
             .forEach(taskId -> assertThat(taskIds.contains(taskId), is(true)));
    }

    @Test
    public void testTaskEnshure() {
        final String projectNameExpected = "project" + UUID.randomUUID();
        final String projectDescriptionExpected = "description" + UUID.randomUUID();

        KanboardProject projectTemplate = new KanboardProject();
        projectTemplate.setObjectName(projectNameExpected);
        projectTemplate.setDescription(projectDescriptionExpected);

        KanboardProject projectCreated = this.service.project()
                                                     .create(projectTemplate);
        assertThat(projectCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(projectCreated.getId()), is(greaterThan(0)));

        String titleExpected = "task 1";
        KanboardTask taskTemp = new KanboardTask(projectCreated.getId(), titleExpected);

        KanboardTask taskCreated = this.service.task()
                                               .enshure(taskTemp);

        assertThat(taskCreated, is(not(nullValue())));
        assertThat(taskCreated.getId(), is(not(nullValue())));
        assertThat(taskCreated.getObjectName(), equalTo(titleExpected));

        KanboardTask taskReloaded = this.service.task()
                                                .enshure(taskTemp);

        assertThat(taskReloaded, is(not(nullValue())));
        assertThat(taskReloaded.getId(), is(not(nullValue())));
        assertThat(taskReloaded.getObjectName(), equalTo(titleExpected));

        Map<String, String> mapCreated = this.service.task()
                                                     .getUtil()
                                                     .convertToMap(taskCreated);
        Map<String, String> mapReloaded = this.service.task()
                                                      .getUtil()
                                                      .convertToMap(taskReloaded);

        mapCreated.keySet()
                  .stream()
                  .forEach(key -> assertThat(mapCreated.get(key), equalTo(mapReloaded.get(key))));
    }

    @Test
    public void testGetByDeadline() {
        final String projectNameExpected = "project" + UUID.randomUUID();
        final String projectDescriptionExpected = "description" + UUID.randomUUID();

        KanboardProject projectTemplate = new KanboardProject();
        projectTemplate.setObjectName(projectNameExpected);
        projectTemplate.setDescription(projectDescriptionExpected);

        KanboardProject projectCreated = this.service.project()
                                                     .create(projectTemplate);
        assertThat(projectCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(projectCreated.getId()), is(greaterThan(0)));

        // test exceptions
        try {
            this.service.task()
                        .getByDeadline(projectCreated, null);
            fail("Exception expected");
        } catch (KanboardException e) {
            assertThat(e.getMessage(), is("DeadlineDates must not be NULL or EMPTY"));
        }

        try {
            this.service.task()
                        .getByDeadline(projectCreated, new ArrayList<>());
            fail("Exception expected");
        } catch (KanboardException e) {
            assertThat(e.getMessage(), is("DeadlineDates must not be NULL or EMPTY"));
        }

        List<Date> deadlineExpected = this.service.task()
                                                  .getDeadlineDefault();

        List<Date> deadlineScrambled = new ArrayList<>();
        deadlineScrambled.add(deadlineExpected.get(1));
        deadlineScrambled.add(deadlineExpected.get(0));
        deadlineScrambled.add(deadlineExpected.get(2));

        // test empty
        Map<Date, List<KanboardTask>> deadlineResult = this.service.task()
                                                                   .getByDeadline(projectCreated, deadlineScrambled);

        assertThat(deadlineResult, is(not(nullValue())));
        assertThat(deadlineResult.size(), is(3));

        // test
        boolean debugDeadlineDates = false;
        DateFormat dateFormat = null;

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeZone(this.service.date()
                                         .getTimeZone());
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        calendar.add(Calendar.MILLISECOND, -1);
        if (debugDeadlineDates) {
            dateFormat = this.service.date()
                                     .getDateFormatDebug();

            System.out.println("\n\n----------------------------------");
            System.out.println("Kanbaord is working with timezone  " + dateFormat.getTimeZone()
                                                                                 .getID());
            System.out.println("JVM setting: " + this.dateFormatOffset.format(new Date()));

            System.out.println("\nExample deadlines:\n");
            System.out.println(
                    "deadline0 ==               dateToCompare < " + dateFormat.format(deadlineExpected.get(0)));
            System.out.println(
                    "deadline1 == !deadline0 && dateToCompare < " + dateFormat.format(deadlineExpected.get(1)));
            System.out.println(
                    "deadline2 == !deadline1 && dateToCompare < " + dateFormat.format(deadlineExpected.get(2)));

            System.out.println("\nIdea deadline:");
            System.out.println("    1) A deadline date is the start of a day 00:00:00.");
            System.out.println(
                    "    2) To get tasks BEFORE the deadline we need to define timespans which will categorize the urgency:");
            System.out.println(
                    "      a) Each timespan starts with the deadline date preceding it in the list or infinity");
            System.out.println("      b) Each timespan ends with the dateline date -1ms");

            System.out.println("\n" + dateFormat.format(calendar.getTime()) + "  --- yesterday -> deadline0");
        }
        KanboardTask taskDeadline0Expected1 = new KanboardTask(projectCreated, "taskDeadline0Expected1");
        taskDeadline0Expected1.setDateDue(this.service.date()
                                                      .convertToDateKanboard(calendar.getTime()));

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        if (debugDeadlineDates) {
            System.out.println(dateFormat.format(calendar.getTime()) + "  --- day before yesterday -> deadline0");
        }
        KanboardTask taskDeadline0Expected2 = new KanboardTask(projectCreated, "taskDeadline0Expected2");
        taskDeadline0Expected2.setDateDue(this.service.date()
                                                      .convertToDateKanboard(calendar.getTime()));

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MILLISECOND, 1);
        if (debugDeadlineDates) {
            System.out.println(dateFormat.format(calendar.getTime()) + "  --- today 00:00:00 -> first of deadline1");
        }
        KanboardTask taskDeadline1Expected1 = new KanboardTask(projectCreated, "taskDeadline1Expected1");
        taskDeadline1Expected1.setDateDue(this.service.date()
                                                      .convertToDateKanboard(calendar.getTime()));

        calendar.add(Calendar.MILLISECOND, -1);
        calendar.add(Calendar.DAY_OF_MONTH, KanboardServiceTask.DEADLINE_LIMIT_FIRST);
        if (debugDeadlineDates) {
            System.out.println(
                    dateFormat.format(calendar.getTime()) + "  --- DEADLINE_LIMIT_FIRST - 1ms -> last deadline1");
        }
        KanboardTask taskDeadline1Expected2 = new KanboardTask(projectCreated, "taskDeadline1Expected2");
        taskDeadline1Expected2.setDateDue(this.service.date()
                                                      .convertToDateKanboard(calendar.getTime()));

        calendar.add(Calendar.MILLISECOND, 1);
        if (debugDeadlineDates) {
            System.out.println(dateFormat.format(calendar.getTime()) + "  --- DEADLINE_LIMIT_FIRST -> first deadline2");
        }
        KanboardTask taskDeadline2Expected1 = new KanboardTask(projectCreated, "taskDeadline2Expected1");
        taskDeadline2Expected1.setDateDue(this.service.date()
                                                      .convertToDateKanboard(calendar.getTime()));

        calendar.add(Calendar.MILLISECOND, -1);
        calendar.add(
                Calendar.DAY_OF_MONTH,
                    KanboardServiceTask.DEADLINE_LIMIT_SECOND - KanboardServiceTask.DEADLINE_LIMIT_FIRST);
        if (debugDeadlineDates) {
            System.out.println(
                    dateFormat.format(calendar.getTime()) + "  --- DEADLINE_LIMIT_SECOND -1ms -> last deadline2");
        }
        KanboardTask taskDeadline2Expected2 = new KanboardTask(projectCreated, "taskDeadline2Expected1");
        taskDeadline2Expected2.setDateDue(this.service.date()
                                                      .convertToDateKanboard(calendar.getTime()));

        calendar.add(Calendar.MILLISECOND, 1);
        if (debugDeadlineDates) {
            System.out.println(
                    dateFormat.format(calendar.getTime()) + "  --- DEADLINE_LIMIT_SECOND -> first unreachable date");
            System.out.println("----------------------------------\n\n");
        }
        KanboardTask taskDeadlineUnreachable = new KanboardTask(projectCreated, "taskDeadlineUnreachable");
        taskDeadlineUnreachable.setDateDue(this.service.date()
                                                       .convertToDateKanboard(calendar.getTime()));

        this.service.task()
                    .create(taskDeadline0Expected1);
        this.service.task()
                    .create(taskDeadline0Expected2);
        this.service.task()
                    .create(taskDeadline1Expected1);
        this.service.task()
                    .create(taskDeadline1Expected2);
        this.service.task()
                    .create(taskDeadline2Expected1);
        this.service.task()
                    .create(taskDeadline2Expected2);
        this.service.task()
                    .create(taskDeadlineUnreachable);

        deadlineResult = this.service.task()
                                     .getByDeadline(projectCreated, deadlineScrambled);
        assertThat(deadlineResult, is(not(nullValue())));
        assertThat(deadlineResult.size(), is(3));

        List<KanboardTask> deadline0 = deadlineResult.get(deadlineExpected.get(0));
        assertThat(deadline0, is(not(nullValue())));
        assertThat(deadline0.size(), is(2));
        assertThat(
                deadline0.stream()
                         .map(e -> "taskDeadline0Expected1".equals(e.getTitle()))
                         .findFirst()
                         .orElse(null),
                    is(not(nullValue())));
        assertThat(
                deadline0.stream()
                         .map(e -> "taskDeadline0Expected2".equals(e.getTitle()))
                         .findFirst()
                         .orElse(null),
                    is(not(nullValue())));

        List<KanboardTask> deadline1 = deadlineResult.get(deadlineExpected.get(1));
        assertThat(deadline1, is(not(nullValue())));
        assertThat(deadline1.size(), is(2));
        assertThat(
                deadline0.stream()
                         .map(e -> "taskDeadline1Expected1".equals(e.getTitle()))
                         .findFirst()
                         .orElse(null),
                    is(not(nullValue())));
        assertThat(
                deadline0.stream()
                         .map(e -> "taskDeadline1Expected2".equals(e.getTitle()))
                         .findFirst()
                         .orElse(null),
                    is(not(nullValue())));

        List<KanboardTask> deadline2 = deadlineResult.get(deadlineExpected.get(2));
        assertThat(deadline2, is(not(nullValue())));
        assertThat(deadline2.size(), is(2));
        assertThat(
                deadline0.stream()
                         .map(e -> "taskDeadline2Expected1".equals(e.getTitle()))
                         .findFirst()
                         .orElse(null),
                    is(not(nullValue())));
        assertThat(
                deadline0.stream()
                         .map(e -> "taskDeadline2Expected2".equals(e.getTitle()))
                         .findFirst()
                         .orElse(null),
                    is(not(nullValue())));

        deadlineResult.values()
                      .forEach(list -> list.forEach(e -> {
                          if ("taskDeadlineUnreachable".equals(e.getTitle())) {
                              fail("found the impossible! " + e);
                          }
                      }));

    }

    @Test
    public void testTaskDate() {
        final String projectNameExpected = "project" + UUID.randomUUID();
        final String projectDescriptionExpected = "description" + UUID.randomUUID();

        KanboardProject projectTemplate = new KanboardProject();
        projectTemplate.setObjectName(projectNameExpected);
        projectTemplate.setDescription(projectDescriptionExpected);

        KanboardProject projectCreated = this.service.project()
                                                     .create(projectTemplate);
        assertThat(projectCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(projectCreated.getId()), is(greaterThan(0)));

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeZone(this.service.date()
                                         .getTimeZone());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONDAY, 3);
        calendar.set(Calendar.YEAR, 2016);

        KanboardTask taskExpected = new KanboardTask(projectCreated.getId(), "1");
        taskExpected.setDateDue(this.service.date()
                                            .convertToDateKanboard(calendar.getTime()));

        this.service.task()
                    .create(taskExpected);

        List<KanboardTask> tasksActual = this.service.task()
                                                     .getAllActive(projectCreated);

        assertThat(tasksActual, is(not(nullValue())));
        assertThat(tasksActual.size(), is(1));
        assertThat(
                tasksActual.get(0)
                           .getDateDue(),
                    equalTo("1459468800"));
    }

    @Test
    public void testGetAllActiveWithDueDate() {
        final String projectNameExpected = "project" + UUID.randomUUID();
        final String projectDescriptionExpected = "description" + UUID.randomUUID();

        KanboardProject projectTemplate = new KanboardProject();
        projectTemplate.setObjectName(projectNameExpected);
        projectTemplate.setDescription(projectDescriptionExpected);

        KanboardProject projectCreated = this.service.project()
                                                     .create(projectTemplate);
        assertThat(projectCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(projectCreated.getId()), is(greaterThan(0)));

        KanboardTask taskWith1Expected = new KanboardTask(projectCreated.getId(), "with 1");
        taskWith1Expected.setDateDue("2016-04-01");

        KanboardTask taskWithout2Expected = new KanboardTask(projectCreated.getId(), "without 2");

        KanboardTask taskWith3Expected = new KanboardTask(projectCreated.getId(), "with 3");
        taskWith3Expected.setDateDue("2016-04-02");

        this.service.task()
                    .create(taskWith1Expected);
        this.service.task()
                    .create(taskWithout2Expected);
        this.service.task()
                    .create(taskWith3Expected);

        List<KanboardTask> tasksActual = this.service.task()
                                                     .getAllActiveWithDueDate(projectCreated);

        assertThat(tasksActual, is(not(nullValue())));
        assertThat(tasksActual.size(), is(2));

        KanboardTask task2Found = tasksActual.stream()
                                             .filter(e -> taskWithout2Expected.getTitle()
                                                                              .equals(e.getTitle()))
                                             .findFirst()
                                             .orElse(null);
        assertThat(task2Found, is(nullValue()));
    }

    @Test
    public void testTaskMoveToPositionShouldMovePosition2ToPosition3() {
        final String projectNameExpected = "project" + UUID.randomUUID();
        final String projectDescriptionExpected = "description" + UUID.randomUUID();

        KanboardProject projectTemplate = new KanboardProject();
        projectTemplate.setObjectName(projectNameExpected);
        projectTemplate.setDescription(projectDescriptionExpected);

        KanboardProject projectCreated = this.service.project()
                                                     .create(projectTemplate);
        assertThat(projectCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(projectCreated.getId()), is(greaterThan(0)));

        KanboardTask task1Expected = new KanboardTask(projectCreated.getId(), "1");
        KanboardTask task2Expected = new KanboardTask(projectCreated.getId(), "2");
        KanboardTask task3Expected = new KanboardTask(projectCreated.getId(), "3");

        this.service.task()
                    .create(task1Expected);
        this.service.task()
                    .create(task2Expected);
        this.service.task()
                    .create(task3Expected);

        // position 2 -> 3
        KanboardTask taskPosition2 = getTaskAtPosition(
                this.service.task()
                            .getAllActive(projectCreated),
                    "2");
        this.service.task()
                    .moveToPostion(taskPosition2, "3");

        List<KanboardTask> tasksActual = this.service.task()
                                                     .getAllActive(projectCreated);
        Assertions.assertThat(getTaskAtPosition(tasksActual, "1").getObjectName())
                  .isEqualTo("1");
        Assertions.assertThat(getTaskAtPosition(tasksActual, "2").getObjectName())
                  .isEqualTo("3");
        Assertions.assertThat(getTaskAtPosition(tasksActual, "3").getObjectName())
                  .isEqualTo("2");
    }

    @Test
    public void testTaskMoveToPositionShouldMovePosition2ToPosition1() {
        final String projectNameExpected = "project" + UUID.randomUUID();
        final String projectDescriptionExpected = "description" + UUID.randomUUID();

        KanboardProject projectTemplate = new KanboardProject();
        projectTemplate.setObjectName(projectNameExpected);
        projectTemplate.setDescription(projectDescriptionExpected);

        KanboardProject projectCreated = this.service.project()
                                                     .create(projectTemplate);
        assertThat(projectCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(projectCreated.getId()), is(greaterThan(0)));

        KanboardTask task1Expected = new KanboardTask(projectCreated.getId(), "1");
        KanboardTask task2Expected = new KanboardTask(projectCreated.getId(), "2");
        KanboardTask task3Expected = new KanboardTask(projectCreated.getId(), "3");

        this.service.task()
                    .create(task1Expected);
        this.service.task()
                    .create(task2Expected);
        this.service.task()
                    .create(task3Expected);

        // position 2 -> 1
        KanboardTask taskPosition2 = getTaskAtPosition(
                this.service.task()
                            .getAllActive(projectCreated),
                    "2");
        this.service.task()
                    .moveToPostion(taskPosition2, "1");

        List<KanboardTask> tasksActual = this.service.task()
                                                     .getAllActive(projectCreated);
        Assertions.assertThat(getTaskAtPosition(tasksActual, "1").getObjectName())
                  .isEqualTo("2");
        Assertions.assertThat(getTaskAtPosition(tasksActual, "2").getObjectName())
                  .isEqualTo("1");
        Assertions.assertThat(getTaskAtPosition(tasksActual, "3").getObjectName())
                  .isEqualTo("3");
    }

    private KanboardTask getTaskAtPosition(List<KanboardTask> tasks,
                                           String position) {
        return tasks.stream()
                    .filter(task -> task.getPosition()
                                        .equals(position))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("task at position " + position + " missing"));
    }

    @Test
    public void testSortByObjectNameShouldSortCompleteProject() throws Exception {
        final String projectNameExpected = "project" + UUID.randomUUID();
        final String projectDescriptionExpected = "description" + UUID.randomUUID();

        KanboardProject projectTemplate = new KanboardProject();
        projectTemplate.setObjectName(projectNameExpected);
        projectTemplate.setDescription(projectDescriptionExpected);

        KanboardProject projectCreated = this.service.project()
                                                     .create(projectTemplate);
        assertThat(projectCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(projectCreated.getId()), is(greaterThan(0)));

        // add swimmlanes
        String swimlaneNameExpected = "Swimlane2";
        String swimlaneDescriptionExpected = "Swimlane description 2";
        KanboardSwimlane swimlane2 = new KanboardSwimlane();
        swimlane2.setObjectName(swimlaneNameExpected);
        swimlane2.setDescription(swimlaneDescriptionExpected);
        swimlane2.setProjectId(projectCreated.getId());

        swimlane2 = this.service.swimlane()
                                .add(swimlane2);

        String swimlaneName3Expected = "Swimlane3";
        String swimlaneDescription3Expected = "Swimlane description 3";
        KanboardSwimlane swimlane3 = new KanboardSwimlane();
        swimlane3.setObjectName(swimlaneName3Expected);
        swimlane3.setDescription(swimlaneDescription3Expected);
        swimlane3.setProjectId(projectCreated.getId());

        swimlane3 = this.service.swimlane()
                                .add(swimlane3);

        int sizeX = 3;
        int sizeY = 3;
        int sizeZ = 3;
        KanboardTask[][][] matrix = new KanboardTask[sizeX][sizeY][sizeZ];
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (int z = 0; z < sizeZ; z++) {
                    matrix[x][y][z] = new KanboardTask(projectCreated.getId(), getMatrixTaskObjectName(x, y, z));
                }
            }
        }
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (int z = 0; z < sizeZ; z++) {
                    matrix[x][y][z] = this.service.task()
                                                  .create(matrix[x][y][z]);
                }
            }
        }

        List<String> columnIds = this.service.column()
                                             .getAll(projectCreated.getId())
                                             .stream()
                                             .map(column -> column.getId())
                                             .collect(Collectors.toList());

        String columnId1 = columnIds.get(0);
        String columnId2 = columnIds.get(1);
        String columnId3 = columnIds.get(2);

        // move across board
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (int z = 0; z < sizeZ; z++) {
                    if ((x == 0) && (y == 0)) {
                        continue;
                    }
                    KanboardTask moveMe = matrix[x][y][z];
                    String projectId = moveMe.getProjectId();
                    String taskId = moveMe.getId();
                    String columnId = columnId1;
                    if (y == 1) {
                        columnId = columnId2;
                    }
                    if (y == 2) {
                        columnId = columnId3;
                    }
                    String position = moveMe.getPosition();
                    String swimlaneId = moveMe.getSwimlaneId();
                    if (x == 1) {
                        swimlaneId = swimlane2.getId();
                    }
                    if (x == 2) {
                        swimlaneId = swimlane3.getId();
                    }
                    this.service.task()
                                .moveToPostion(projectId, taskId, String.valueOf(columnId), position, swimlaneId);
                }
            }
        }

        this.service.task()
                    .sortByObjectName(projectCreated);

        // assert
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (int z = 0; z < sizeZ; z++) {
                    KanboardTask checkMe = this.service.task()
                                                       .getById(matrix[x][y][z].getId());
                    // DEBUG
                    // System.out.println(checkMe.getObjectName() + "_" + (x + 1) + "_" + (y + 1) + "_" + (z + 1));
                    if (x == 2) {
                        // skip times equal -> unpredictible sorting
                        continue;
                    }
                    switch (checkMe.getObjectName()) {
                    case "aaa":
                        Assertions.assertThat(checkMe.getPosition())
                                  .isEqualTo("1");
                        break;

                    case "bbb":
                        Assertions.assertThat(checkMe.getPosition())
                                  .isEqualTo("2");
                        break;

                    case "ccc":
                        Assertions.assertThat(checkMe.getPosition())
                                  .isEqualTo("3");
                        break;

                    default:
                        throw new IllegalArgumentException("unknown name: " + checkMe.getObjectName());
                    }
                }
            }
        }
        // DEBUG:
        //        String url = "http://127.0.0.1/?controller=BoardViewController&action=show&project_id=" + projectCreated.getId()
        //                + "&search=status%3Aopen";
        //        System.out.println(url);
    }

    private String getMatrixTaskObjectName(int outerX,
                                           int outerY,
                                           int outerZ) {
        String[][][] matrix = new String[3][3][3];

        matrix[0][0][0] = "aaa";
        matrix[0][0][1] = "bbb";
        matrix[0][0][2] = "ccc";

        matrix[0][1][0] = "aaa";
        matrix[0][1][1] = "ccc";
        matrix[0][1][2] = "bbb";

        matrix[0][2][0] = "bbb";
        matrix[0][2][1] = "aaa";
        matrix[0][2][2] = "ccc";

        matrix[1][0][0] = "bbb";
        matrix[1][0][1] = "ccc";
        matrix[1][0][2] = "aaa";

        matrix[1][1][0] = "ccc";
        matrix[1][1][1] = "aaa";
        matrix[1][1][2] = "bbb";

        matrix[1][2][0] = "ccc";
        matrix[1][2][1] = "bbb";
        matrix[1][2][2] = "aaa";

        matrix[2][0][0] = "aaa";
        matrix[2][0][1] = "aaa";
        matrix[2][0][2] = "aaa";

        matrix[2][1][0] = "bbb";
        matrix[2][1][1] = "bbb";
        matrix[2][1][2] = "bbb";

        matrix[2][2][0] = "ccc";
        matrix[2][2][1] = "ccc";
        matrix[2][2][2] = "ccc";

        // DEBUG:
        // return matrix[outerX][outerY][outerZ] + "_task_" + (outerX + 1) + "_" + (outerY + 1) + "_" + (outerZ + 1);
        return matrix[outerX][outerY][outerZ];
    }

}
