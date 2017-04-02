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

public class RequestBuilderSwimlaneTest implements KanboardConstant {

    private ObjectMapper mapper;
    private final RequestBuilderSwimlane requestBuilder = new RequestBuilderSwimlane();

    @Before
    public void setUp() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    public void testSwimlaneGetAll() throws Exception {
        String projectIdExpected = "1";
        KanboardRequest request = this.requestBuilder.swimlaneGetAll(projectIdExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getAllSwimlanes\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"1\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testSwimlaneGetById() throws Exception {
        String swimlaneIdExpected = "1";
        KanboardRequest request = this.requestBuilder.swimlaneGetById(swimlaneIdExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getSwimlaneById\",\n  \"id\" : null,\n  \"params\" : {\n    \"swimlane_id\" : \"1\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testSwimlaneAdd() throws Exception {
        String projectIdExpected = "1";
        String nameExpected = "Simlane 1";
        String descriptionExpected = "description 1";

        KanboardRequest request = this.requestBuilder.swimlaneAdd(projectIdExpected, nameExpected, descriptionExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"addSwimlane\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"1\",\n    \"name\" : \"Simlane 1\",\n    \"description\" : \"description 1\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testSwimlaneGetByName() throws Exception {
        String projectIdExpected = "1";
        String nameExpected = "Simlane 1";

        KanboardRequest request = this.requestBuilder.swimlaneGetByName(projectIdExpected, nameExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getSwimlaneByName\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"1\",\n    \"name\" : \"Simlane 1\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testSwimlaneUpdate() throws Exception {
        String projectIdExpected = "1";
        String swimlaneIdExpected = "1";
        String swimlaneNameExpected = "Swimlane 42";
        String swimlaneDescriptionExpected = "Swimlane Description 42";

        KanboardRequest request = this.requestBuilder.swimlaneUpdate(
                projectIdExpected,
                    swimlaneIdExpected,
                    swimlaneNameExpected,
                    swimlaneDescriptionExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"updateSwimlane\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"1\",\n    \"swimlane_id\" : \"1\",\n    \"name\" : \"Swimlane 42\",\n    \"description\" : \"Swimlane Description 42\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testSwimlaneDisable() throws Exception {
        String projectIdExpected = "2";
        String swimlaneIdExpected = "1";

        KanboardRequest request = this.requestBuilder.swimlaneDisable(projectIdExpected, swimlaneIdExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"disableSwimlane\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"2\",\n    \"swimlane_id\" : \"1\"\n  }\n}";
        assertThat(actual, equalTo(expected));

    }

    @Test
    public void testSwimlaneRemove() throws Exception {
        String projectIdExpected = "2";
        String swimlaneIdExpected = "1";

        KanboardRequest request = this.requestBuilder.swimlaneRemove(projectIdExpected, swimlaneIdExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"removeSwimlane\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"2\",\n    \"swimlane_id\" : \"1\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testSwimlaneChangePosition() throws Exception {
        String projectIdExpected = "1";
        String swimlaneIdExpected = "2";
        String positionExpected = "3";

        Map<String, String> params = new HashMap<>();
        params.put(PARAM_PROJECT_ID, projectIdExpected);
        params.put(PARAM_SWIMLANE_ID, swimlaneIdExpected);
        params.put(PARAM_POSITION, positionExpected);
        KanboardRequest request = this.requestBuilder.swimlaneChangePosition(params);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"changeSwimlanePosition\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"1\",\n    \"swimlane_id\" : \"2\",\n    \"position\" : \"3\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

}
