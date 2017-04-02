package de.livingfire.kanboard.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.rest.KanboardRequest;

public class RequestBuilderTaskTest implements KanboardConstant {

    private ObjectMapper mapper;

    private final RequestBuilderTask requestBuilder = new RequestBuilderTask();

    @Before
    public void setUp() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    public void testTaskCreate() throws Exception {
        String titleExpected = "title";
        String projectIdExpected = "42";
        String categoryIdExpected = "1";
        String colorIdExpected = "blue";

        Map<String, String> paramsOptional = new HashMap<>();
        paramsOptional.put(PARAM_CATEGORY_ID, categoryIdExpected);
        paramsOptional.put(PARAM_COLOR_ID, colorIdExpected);

        KanboardRequest request = this.requestBuilder.taskCreate(titleExpected, projectIdExpected, paramsOptional);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"createTask\",\n  \"id\" : null,\n  \"params\" : {\n    \"title\" : \"title\",\n    \"project_id\" : \"42\",\n    \"color_id\" : \"blue\",\n    \"category_id\" : \"1\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testTaskById() throws Exception {
        String taskIdExpected = "42";
        KanboardRequest request = this.requestBuilder.taskById(taskIdExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getTask\",\n  \"id\" : null,\n  \"params\" : {\n    \"task_id\" : \"42\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testTaskGetAll() throws Exception {
        String projectIdExpected = "42";
        String statusIdExpected = "1";
        KanboardRequest request = this.requestBuilder.taskGetAll(projectIdExpected, statusIdExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getAllTasks\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"42\",\n    \"status_id\" : \"1\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testTaskUpdate() throws Exception {
        String taskIdExpected = "42";
        String titleExpected = "title";
        String categoryIdExpected = "1";
        String colorIdExpected = "blue";

        Map<String, String> paramsOptional = new HashMap<>();
        paramsOptional.put(PARAM_CATEGORY_ID, categoryIdExpected);
        paramsOptional.put(PARAM_COLOR_ID, colorIdExpected);
        paramsOptional.put(PARAM_TITLE, titleExpected);

        KanboardRequest request = this.requestBuilder.taskUpdate(taskIdExpected, paramsOptional);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"updateTask\",\n  \"id\" : null,\n  \"params\" : {\n    \"id\" : \"42\",\n    \"color_id\" : \"blue\",\n    \"category_id\" : \"1\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testTaskMovePosition() throws Exception {
        String projectIdExpected = "34";
        String taskIdExpected = "42";
        String columnIdExpected = "4";
        String positionExpected = "5";
        String swimlaneIdExpected = "1";

        Map<String, String> paramsOptional = new HashMap<>();
        paramsOptional.put(PARAM_SWIMLANE_ID, swimlaneIdExpected);

        KanboardRequest request = this.requestBuilder.taskMovePosition(
                projectIdExpected,
                    taskIdExpected,
                    columnIdExpected,
                    positionExpected,
                    paramsOptional);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"moveTaskPosition\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"34\",\n    \"task_id\" : \"42\",\n    \"column_id\" : \"4\",\n    \"position\" : \"5\",\n    \"swimlane_id\" : \"1\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

}
