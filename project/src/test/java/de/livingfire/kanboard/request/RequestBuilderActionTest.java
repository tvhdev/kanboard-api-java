package de.livingfire.kanboard.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.domain.KanboardAction;
import de.livingfire.kanboard.rest.KanboardRequest;

public class RequestBuilderActionTest implements KanboardConstant {

    private ObjectMapper mapper;
    private final RequestBuilderAction requestBuilder = new RequestBuilderAction();

    @Before
    public void setUp() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    public void testColumnGetAll() throws Exception {

    }

    @Test
    public void testActionAvailableActions() throws Exception {
        KanboardRequest request = this.requestBuilder.actionAvailableActions();

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getAvailableActions\",\n  \"id\" : null,\n  \"params\" : { }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testActionAvailableActionEvents() throws Exception {
        KanboardRequest request = this.requestBuilder.actionAvailableActionEvents();

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getAvailableActionEvents\",\n  \"id\" : null,\n  \"params\" : { }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testActionAvailableActionEventsString() throws Exception {
        String actionNameExpected = "FakeAction";

        KanboardRequest request = this.requestBuilder.actionAvailableActionEvents(actionNameExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getCompatibleActionEvents\",\n  \"id\" : null,\n  \"params\" : {\n    \"action_name\" : \"FakeAction\"\n  }\n}";
        assertThat(actual, equalTo(expected));

    }

    @Test
    public void testActionGetAll() throws Exception {
        String projectIdExpected = "42";

        KanboardRequest request = this.requestBuilder.actionGetAll(projectIdExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getActions\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"42\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testActionCreateCloseColumn() throws Exception {
        KanboardAction action = new KanboardAction();
        action.setProjectId("2");
        action.setEventName(EVENT_NAME_TASK_MOVE_COLUMN);
        action.setActionName("fakeName");
        action.getParams()
              .put(PARAM_COLUMN_ID, "7");

        String actual = this.requestBuilder.actionCreate(action);

        String expected = "{\n  \"jsonrpc\" : \"2.0\",\n  \"method\" : \"createAction\",\n  \"id\" : \"667\",\n  \"params\" : {\n    \"project_id\" : \"2\",\n    \"event_name\" : \"task.move.column\",\n    \"action_name\" : \"fakeName\",\n    \"params\" : {\n      \"column_id\" : \"7\"\n    }\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

}
