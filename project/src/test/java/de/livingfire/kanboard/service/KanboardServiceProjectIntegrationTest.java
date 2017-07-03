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
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.livingfire.kanboard.configuration.KanboardApi;
import de.livingfire.kanboard.constants.IntegrationTestConstant;
import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.domain.KanboardProject;
import de.livingfire.kanboard.domain.KanboardUser;

public class KanboardServiceProjectIntegrationTest implements KanboardConstant, IntegrationTestConstant {

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
    public void testProjectApi() {
        final String projectNameExpected = "project" + UUID.randomUUID();
        final String projectDescriptionExpected = "description" + UUID.randomUUID();

        KanboardProject projectTemplate = new KanboardProject();
        projectTemplate.setObjectName(projectNameExpected);
        projectTemplate.setDescription(projectDescriptionExpected);

        KanboardProject projectNull = this.service.project()
                                                  .getByObjectName(projectTemplate);

        assertThat(projectNull, is(nullValue()));

        KanboardProject projectCreated = this.service.project()
                                                     .create(projectTemplate);
        assertThat(projectCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(projectCreated.getId()), is(greaterThan(0)));

        KanboardProject projectNotNull = this.service.project()
                                                     .getById(projectCreated);

        assertThat(projectNotNull, is(not(nullValue())));
        assertThat(Integer.valueOf(projectNotNull.getId()), is(greaterThan(0)));
        assertThat(projectNotNull.getId(), equalTo(projectCreated.getId()));

    }

    @Test
    public void testColumnEnshure() throws Exception {
        KanboardProject project1Expected = new KanboardProject();
        project1Expected.setName("project1 " + UUID.randomUUID());
        this.service.project()
                    .create(project1Expected);

        KanboardProject project2Expected = new KanboardProject();
        project2Expected.setName("project2 " + UUID.randomUUID());
        this.service.project()
                    .create(project2Expected);

        KanboardProject project3Expected = new KanboardProject();
        project3Expected.setName("project3 " + UUID.randomUUID());
        this.service.project()
                    .create(project3Expected);

        assertThat(
                this.service.project()
                            .getAll()
                            .stream()
                            .filter(e -> project1Expected.getObjectName()
                                                         .equals(e.getObjectName()))
                            .findFirst()
                            .orElse(null),
                    is(not(nullValue())));

        assertThat(
                this.service.project()
                            .getAll()
                            .stream()
                            .filter(e -> project2Expected.getObjectName()
                                                         .equals(e.getObjectName()))
                            .findFirst()
                            .orElse(null),
                    is(not(nullValue())));

        assertThat(
                this.service.project()
                            .getAll()
                            .stream()
                            .filter(e -> project3Expected.getObjectName()
                                                         .equals(e.getObjectName()))
                            .findFirst()
                            .orElse(null),
                    is(not(nullValue())));
    }

    @Test
    public void testUserPermissionApi() {
        String projectNameExpected = "projectname" + UUID.randomUUID()
                                                         .toString();

        KanboardProject projectTemplate = new KanboardProject();
        projectTemplate.setObjectName(projectNameExpected);

        KanboardProject projectCreated = this.service.project()
                                                     .create(projectTemplate);
        assertThat(projectCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(projectCreated.getId()), is(greaterThan(0)));

        String userNameExpected = "user" + UUID.randomUUID()
                                               .toString();
        String passwordExpected = "password" + UUID.randomUUID()
                                                   .toString();
        String roleExpected = ROLE_APP_ADMIN;

        KanboardUser userTemplate = new KanboardUser();
        userTemplate.setObjectName(userNameExpected);
        userTemplate.setPassword(passwordExpected);
        userTemplate.setRole(roleExpected);

        KanboardUser userCreated = this.service.user()
                                               .create(userTemplate);
        assertThat(userCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(userCreated.getId()), is(greaterThan(0)));

        this.service.project()
                    .addUser(projectCreated, userCreated, ROLE_PROJECT_MANAGER);
    }

}
