package de.livingfire.kanboard.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.livingfire.kanboard.configuration.KanboardApi;
import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.domain.KanboardUser;

public class KanboardServiceUserIntegrationTest implements KanboardConstant {

    private KanboardService service;

    @Before
    public void setUp() throws UnknownHostException {
        KanboardApi kanboardApi = mock(KanboardApi.class);
        when(kanboardApi.getApiVersion()).thenReturn("2.0");
        if ("maven".equals(InetAddress.getLocalHost()
                                      .getHostName())) {
            when(kanboardApi.getApiUrl()).thenReturn("http://kanboard/jsonrpc.php");
        } else {
            System.out.println("--- CONFIGURATION: DEVELOPMENT");
            when(kanboardApi.getApiUrl()).thenReturn("http://127.0.0.1/jsonrpc.php");
        }
        when(kanboardApi.getApiUser()).thenReturn("jsonrpc");
        when(kanboardApi.getApiAuthToken()).thenReturn("19ffd9709d03ce50675c3a43d1c49c1ac207f4bc45f06c5b2701fbdf8929");
        when(kanboardApi.getApiHeader()).thenReturn("X-API-Auth");

        this.service = new KanboardService();
        this.service.setApi(kanboardApi);
        this.service.setVerbose(KanboardConstant.SHOW_HTTP_REQUESTS);
    }

    @Test
    public void testUserApi() {
        String userNameExpected = "user" + UUID.randomUUID()
                                               .toString();
        String passwordExpected = "password" + UUID.randomUUID()
                                                   .toString();
        String roleExpected = ROLE_APP_ADMIN;

        KanboardUser userCreated = new KanboardUser();
        userCreated.setObjectName(userNameExpected);
        userCreated.setPassword(passwordExpected);
        userCreated.setRole(roleExpected);

        userCreated = this.service.user()
                                  .create(userCreated);

        assertThat(userCreated, not(nullValue()));
        assertThat(Integer.valueOf(userCreated.getId()), is(greaterThan(0)));
        assertThat(userCreated.getObjectName(), equalTo(userNameExpected));
        assertThat(userCreated.getRole(), equalTo(roleExpected));

        KanboardUser userByObjectname = this.service.user()
                                                    .getByObjectName(userCreated);
        assertThat(userByObjectname, not(nullValue()));
        assertThat(Integer.valueOf(userByObjectname.getId()), is(greaterThan(0)));
        assertThat(userByObjectname.getObjectName(), equalTo(userNameExpected));
        assertThat(userByObjectname.getRole(), equalTo(roleExpected));
    }

    @Test
    public void testUserEnshure() throws Exception {
        String roleExpected = ROLE_APP_ADMIN;

        KanboardUser userTemplate = new KanboardUser();
        userTemplate.setName("user" + UUID.randomUUID()
                                          .toString());
        userTemplate.setUsername("John Doe" + UUID.randomUUID()
                                                  .toString());
        userTemplate.setPassword("password123" + UUID.randomUUID()
                                                     .toString());
        userTemplate.setRole(roleExpected);

        KanboardUser userCreated = this.service.user()
                                               .enshure(userTemplate);

        assertThat(userCreated, is(not(nullValue())));
        assertThat(userCreated.getUsername(), equalTo(userTemplate.getUsername()));
        assertThat(userCreated.getRole(), equalTo(userTemplate.getRole()));
        assertThat(userCreated.getName(), equalTo(userTemplate.getName()));

        KanboardUser userReloaded = this.service.user()
                                                .enshure(userTemplate);

        Map<String, String> mapCreated = this.service.user()
                                                     .getUtil()
                                                     .convertToMap(userCreated);
        Map<String, String> mapReloaded = this.service.user()
                                                      .getUtil()
                                                      .convertToMap(userReloaded);

        mapCreated.keySet()
                  .stream()
                  .forEach(key -> assertThat(mapCreated.get(key), equalTo(mapReloaded.get(key))));

    }
}
