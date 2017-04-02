package de.livingfire.kanboard.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class RequestBuilderApplicationTest {

    private final RequestBuilderApplication requestBuilder = new RequestBuilderApplication();

    @Test
    public void testApplicationGetTimezone() throws Exception {
        String actual = this.requestBuilder.applicationGetTimezone();

        String expected = "{\n  \"jsonrpc\" : \"2.0\",\n  \"method\" : \"getTimezone\",\n  \"id\" : \"667\"\n}";
        assertThat(actual, equalTo(expected));
    }

}
