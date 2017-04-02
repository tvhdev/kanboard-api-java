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

public class RequestBuilderProjectTest implements KanboardConstant {

    private ObjectMapper mapper;
    private final RequestBuilderProject requestBuilder = new RequestBuilderProject();

    @Before
    public void setUp() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    public void testProjectByName() throws Exception {
        String projectNameExpected = "projectName";

        KanboardRequest request = this.requestBuilder.projectByName(projectNameExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getProjectByName\",\n  \"id\" : null,\n  \"params\" : {\n    \"name\" : \"projectName\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testProjectCreate() throws Exception {
        String projectNameExpected = "projectName";
        Map<String, String> paramsOptional = new HashMap<>();
        paramsOptional.put(PARAM_DESCRIPTION, "description");

        KanboardRequest request = this.requestBuilder.projectCreate(projectNameExpected, paramsOptional);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"createProject\",\n  \"id\" : null,\n  \"params\" : {\n    \"name\" : \"projectName\",\n    \"description\" : \"description\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testProjectById() throws Exception {
        String projectId = "1";
        KanboardRequest request = this.requestBuilder.projectById(projectId);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getProjectById\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"1\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testProjectAddUser() throws Exception {
        String projectIdExpected = "1";
        String userIdExpected = "2";
        String roleExpected = ROLE_PROJECT_VIEWER;

        KanboardRequest request = this.requestBuilder.projectAddUser(projectIdExpected, userIdExpected, roleExpected);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"addProjectUser\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"1\",\n    \"user_id\" : \"2\",\n    \"role\" : \"project-viewer\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testProjectUpdate() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_PROJECT_ID, "1");
        params.put(PARAM_NAME, "my name");
        params.put(PARAM_DESCRIPTION, "my description");
        params.put(PARAM_OWNER_ID, "42");
        params.put(PARAM_IDENTIFIER, "my identifier");

        KanboardRequest request = this.requestBuilder.projectUpdate(params);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"updateProject\",\n  \"id\" : null,\n  \"params\" : {\n    \"project_id\" : \"1\",\n    \"name\" : \"my name\",\n    \"description\" : \"my description\",\n    \"owner_id\" : \"42\",\n    \"identifier\" : \"my identifier\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testProjectGetAll() throws Exception {
        String actual = this.requestBuilder.projectGetAll();
        String expected = "{\n  \"jsonrpc\" : \"2.0\",\n  \"method\" : \"getAllProjects\",\n  \"id\" : \"667\"\n}";
        assertThat(actual, equalTo(expected));
    }

}
