package de.livingfire.kanboard.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import de.livingfire.kanboard.configuration.KanboardApi;
import de.livingfire.kanboard.constants.IntegrationTestConstant;
import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.util.KanboardUtilDate;

public class KanboardServiceApplicationIntegrationTest implements KanboardConstant, IntegrationTestConstant {

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
    public void testGetTimezone() throws Exception {

        String actual = this.service.application()
                                    .getTimezone();

        assertThat(actual, is(not(nullValue())));
        assertThat(actual, equalTo("UTC"));
    }

    @Test
    public void testGetUtilDate() throws Exception {
        KanboardUtilDate actual = this.service.application()
                                              .getUtilDate();

        assertThat(actual, is(not(nullValue())));
        assertThat(
                actual.getTimeZone()
                      .getID(),
                    equalTo("UTC"));
    }

}
