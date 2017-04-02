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

public class RequestBuilderColumnTest implements KanboardConstant {

    private ObjectMapper mapper;
    private final RequestBuilderColumn requestBuilder = new RequestBuilderColumn();

    @Before
    public void setUp() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    public void testColumnGetAll() throws Exception {
        String projectIdExpected = "1";

        KanboardRequest request = this.requestBuilder.columnGet(projectIdExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getColumns\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"1\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testColumnRemove() throws Exception {
        String columnIdExpected = "44";

        KanboardRequest request = this.requestBuilder.columnRemove(columnIdExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"removeColumn\",\n  \"id\" : null,\n  \"params\" : {\n    \"column_id\" : \"44\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testColumnAdd() throws Exception {
        String projectIdExpected = "11";
        String titleExpected = "title";
        String taskLimitExpected = "3";
        String descriptionExpected = "description";

        KanboardRequest request = this.requestBuilder.columnAdd(
                projectIdExpected,
                    titleExpected,
                    taskLimitExpected,
                    descriptionExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"addColumn\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"11\",\n    \"title\" : \"title\",\n    \"task_limit\" : \"3\",\n    \"description\" : \"description\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testColumnChangePosition() throws Exception {
        String projectIdExpected = "1";
        String columnIdExpected = "2";
        String positionExpected = "3";

        KanboardRequest request = this.requestBuilder.columnChangePosition(
                projectIdExpected,
                    columnIdExpected,
                    positionExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"changeColumnPosition\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"1\",\n    \"column_id\" : \"2\",\n    \"position\" : \"3\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testColumnGetById() throws Exception {
        String columnIdExpected = "2";

        KanboardRequest request = this.requestBuilder.columnGetById(columnIdExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getColumn\",\n  \"id\" : null,\n  \"params\" : {\n    \"column_id\" : \"2\"\n  }\n}";
        assertThat(actual, equalTo(expected));

    }

    @Test
    public void testColumnUpdate() throws Exception {
        String columnIdExpected = "42";
        String titleExpected = "my title";
        Map<String, String> paramsOptional = new HashMap<>();
        paramsOptional.put(PARAM_TASK_LIMIT, "5");
        paramsOptional.put(PARAM_DESCRIPTION, "my description");

        KanboardRequest request = this.requestBuilder.columnUpdate(columnIdExpected, titleExpected, paramsOptional);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"updateColumn\",\n  \"id\" : null,\n  \"params\" : {\n    \"column_id\" : \"42\",\n    \"title\" : \"my title\",\n    \"task_limit\" : \"5\",\n    \"description\" : \"my description\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }
}
