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

public class RequestBuilderUserTest implements KanboardConstant {

    private ObjectMapper mapper;

    RequestBuilderUser requestBuilder = new RequestBuilderUser();

    @Before
    public void setUp() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    public void testUserByUsername() throws Exception {
        String userName = "skylar";

        KanboardRequest request = this.requestBuilder.userByUsername(userName);

        String actual = this.mapper.writeValueAsString(request);
        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getUserByName\",\n  \"id\" : null,\n  \"params\" : {\n    \"username\" : \"skylar\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testUserDisable() throws Exception {
        String userId = "1";
        KanboardRequest request = this.requestBuilder.userDisable(userId);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"disableUser\",\n  \"id\" : null,\n  \"params\" : {\n    \"user_id\" : \"1\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testUserCreate() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_USERNAME, "user");
        params.put(PARAM_PASSWORD, "password");
        params.put(PARAM_ROLE, ROLE_APP_ADMIN);

        KanboardRequest request = this.requestBuilder.userCreate(params);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"createUser\",\n  \"id\" : null,\n  \"params\" : {\n    \"username\" : \"user\",\n    \"password\" : \"password\",\n    \"role\" : \"app-admin\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testUserUpdate() throws Exception {

        Map<String, String> params = new HashMap<>();
        params.put(PARAM_USERNAME, "skylar");
        params.put(PARAM_ROLE, ROLE_APP_MANAGER);

        KanboardRequest request = this.requestBuilder.userUpdate(params);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"updateUser\",\n  \"id\" : null,\n  \"params\" : {\n    \"username\" : \"skylar\",\n    \"role\" : \"app-manager\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testUserGetById() throws Exception {
        String userId = "1";

        KanboardRequest request = this.requestBuilder.userGetById(userId);

        String actual = this.mapper.writeValueAsString(request);

        String expected = "{\n  \"jsonrpc\" : null,\n  \"method\" : \"getUser\",\n  \"id\" : null,\n  \"params\" : {\n    \"user_id\" : \"1\"\n  }\n}";
        assertThat(actual, equalTo(expected));
    }

}
