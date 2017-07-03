package de.livingfire.kanboard.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.livingfire.kanboard.configuration.KanboardApi;
import de.livingfire.kanboard.constants.IntegrationTestConstant;
import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.domain.KanboardAction;
import de.livingfire.kanboard.domain.KanboardColumn;
import de.livingfire.kanboard.domain.KanboardProject;

public class KanboardServiceActionIntegrationTest implements KanboardConstant, IntegrationTestConstant {

    private KanboardService service;

    @Before
    public void setUp() throws UnknownHostException {
        KanboardApi kanboardApi = mock(KanboardApi.class);
        when(kanboardApi.getApiVersion()).thenReturn(REQUEST_JSONRPC_DEFAULT);
        when(kanboardApi.getApiUrl()).thenReturn(INTEGRATION_TEST_URL);
        when(kanboardApi.getApiUser()).thenReturn("jsonrpc");
        when(kanboardApi.getApiAuthToken()).thenReturn("19ffd9709d03ce50675c3a43d1c49c1ac207f4bc45f06c5b2701fbdf8929");
        when(kanboardApi.getApiHeader()).thenReturn("X-API-Auth");

        this.service = new KanboardService();
        this.service.setApi(kanboardApi);
        this.service.setVerbose(KanboardConstant.SHOW_HTTP_REQUESTS);
    }

    @Test
    public void testObjectAvailableActions() throws Exception {

        Map<String, String> actions = this.service.action()
                                                  .availableActions();
        assertThat(actions.containsKey("\\Kanboard\\Action\\TaskAssignColorCategory"), is(true));
        assertThat(actions.containsKey("\\Kanboard\\Action\\TaskMoveColumnClosed"), is(true));
    }

    @Test
    public void testObjectAvailableActionEvents() throws Exception {
        Map<String, String> actions = this.service.action()
                                                  .availableActionEvents();
        assertThat(actions.containsKey("task.close"), is(true));
        assertThat(actions.containsKey("task.create"), is(true));
    }

    @Test
    public void testObjectGetAvailableActionEventsString() throws Exception {
        Map<String, String> actions = this.service.action()
                                                  .availableActionEvents("\\Kanboard\\Action\\TaskMoveColumnClosed");
        assertThat(actions, is(not(nullValue())));
        assertThat(actions.get("task.close"), equalTo("Closing a task"));
    }

    @Test
    public void testObjectGetAll() throws Exception {
        KanboardProject projectExpected = new KanboardProject();
        projectExpected.setName("project" + UUID.randomUUID());
        projectExpected = this.service.project()
                                      .enshure(projectExpected);

        KanboardColumn column1Expected = new KanboardColumn();
        column1Expected.setProjectId(projectExpected.getId());
        column1Expected.setTitle("Backlog");
        column1Expected.setPosition("1");

        column1Expected = this.service.column()
                                      .enshure(column1Expected);

        KanboardAction action = new KanboardAction();
        action.setProjectId(projectExpected.getId());
        action.setEventName(EVENT_NAME_TASK_MOVE_COLUMN);
        action.setActionName(ACTION_NAME_TASK_CLOSE_COLUMN);
        action.getParams()
              .put(PARAM_COLUMN_ID, column1Expected.getId());

        KanboardAction actionActual = this.service.action()
                                                  .create(action);
        assertThat(actionActual, is(not(nullValue())));
        assertThat(actionActual.getId(), is(not(nullValue())));
        assertThat(Integer.valueOf(actionActual.getId()), is(greaterThan(0)));

        List<KanboardAction> actions = this.service.action()
                                                   .getAll(projectExpected.getId());
        assertThat(actions, is(not(nullValue())));
        assertThat(actions.size(), is(1));
        assertThat(
                actions.get(0)
                       .getProjectId(),
                    equalTo(projectExpected.getId()));
    }

    @Test
    public void testObjectCreate() throws Exception {
        KanboardProject projectExpected = new KanboardProject();
        projectExpected.setName("project" + UUID.randomUUID());
        projectExpected = this.service.project()
                                      .enshure(projectExpected);

        KanboardColumn column1Expected = new KanboardColumn();
        column1Expected.setProjectId(projectExpected.getId());
        column1Expected.setTitle("Backlog");
        column1Expected.setPosition("1");

        column1Expected = this.service.column()
                                      .enshure(column1Expected);

        KanboardAction action = new KanboardAction();
        action.setProjectId(projectExpected.getId());
        action.setEventName(EVENT_NAME_TASK_MOVE_COLUMN);
        action.setActionName(ACTION_NAME_TASK_CLOSE_COLUMN);
        action.getParams()
              .put(PARAM_COLUMN_ID, column1Expected.getId());

        KanboardAction actionActual = this.service.action()
                                                  .create(action);
        assertThat(actionActual, is(not(nullValue())));
        assertThat(actionActual.getId(), is(not(nullValue())));
        assertThat(Integer.valueOf(actionActual.getId()), is(greaterThan(0)));
    }

}
