package de.livingfire.kanboard.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.livingfire.kanboard.configuration.KanboardApi;
import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.domain.KanboardColumn;
import de.livingfire.kanboard.domain.KanboardProject;

public class KanboardServiceColumnIntegrationTest implements KanboardConstant {

    private KanboardService service;

    @Before
    public void setUp() throws UnknownHostException {
        KanboardApi kanboardApi = mock(KanboardApi.class);
        when(kanboardApi.getApiVersion()).thenReturn("2.0");
        when(kanboardApi.getApiUrl()).thenReturn("http://172.19.1.1:81/jsonrpc.php");
        when(kanboardApi.getApiUser()).thenReturn("jsonrpc");
        when(kanboardApi.getApiAuthToken()).thenReturn("19ffd9709d03ce50675c3a43d1c49c1ac207f4bc45f06c5b2701fbdf8929");
        when(kanboardApi.getApiHeader()).thenReturn("X-API-Auth");

        this.service = new KanboardService();
        this.service.setApi(kanboardApi);
        this.service.setVerbose(KanboardConstant.SHOW_HTTP_REQUESTS);
    }

    @Test
    public void testColumnGetAll() throws Exception {
        KanboardProject projectExpected = new KanboardProject();
        projectExpected.setName("project" + UUID.randomUUID());
        projectExpected = this.service.project()
                                      .enshure(projectExpected);

        ArrayList<KanboardColumn> columnsActual = this.service.column()
                                                              .getAll(projectExpected.getId());

        assertThat(columnsActual, is(not(nullValue())));
        assertThat(columnsActual.size(), is(4));

        KanboardColumn columnActual = columnsActual.get(0);
        assertThat(columnActual.getTitle(), equalTo("Backlog"));
        assertThat(columnActual.getPosition(), equalTo("1"));

        columnActual = columnsActual.get(1);
        assertThat(columnActual.getTitle(), equalTo("Ready"));
        assertThat(columnActual.getPosition(), equalTo("2"));

        columnActual = columnsActual.get(2);
        assertThat(columnActual.getTitle(), equalTo("Work in progress"));
        assertThat(columnActual.getPosition(), equalTo("3"));

        columnActual = columnsActual.get(3);
        assertThat(columnActual.getTitle(), equalTo("Done"));
        assertThat(columnActual.getPosition(), equalTo("4"));
    }

    @Test
    public void testColumnEnshure() throws Exception {
        KanboardProject projectExpected = new KanboardProject();
        projectExpected.setName("project" + UUID.randomUUID());
        projectExpected = this.service.project()
                                      .enshure(projectExpected);

        List<KanboardColumn> columnsExpected = new ArrayList<>();

        KanboardColumn column1Expected = new KanboardColumn();
        column1Expected.setProjectId(projectExpected.getId());
        column1Expected.setTitle("Backlog");
        column1Expected.setPosition("1");
        columnsExpected.add(column1Expected);

        KanboardColumn column2Expected = new KanboardColumn();
        column2Expected.setProjectId(projectExpected.getId());
        column2Expected.setTitle("column 2");
        column2Expected.setPosition("2");
        columnsExpected.add(column2Expected);

        KanboardColumn column3Expected = new KanboardColumn();
        column3Expected.setProjectId(projectExpected.getId());
        column3Expected.setTitle("Work in progress");
        column3Expected.setPosition("3");
        columnsExpected.add(column3Expected);

        KanboardColumn column4Expected = new KanboardColumn();
        column4Expected.setProjectId(projectExpected.getId());
        column4Expected.setTitle("column 4");
        column4Expected.setPosition("4");
        columnsExpected.add(column4Expected);

        this.service.column()
                    .enshureOrder(columnsExpected);

        List<KanboardColumn> columnsActual = this.service.column()
                                                         .getAll(projectExpected.getId());

        KanboardColumn column1Actual = this.service.column()
                                                   .getUtil()
                                                   .findByPosition(columnsActual, String.valueOf(1));
        assertThat(column1Actual, is(not(nullValue())));
        assertThat(projectExpected.getId(), equalTo(column1Actual.getProjectId()));
        assertThat("Backlog", equalTo(column1Actual.getTitle()));
        assertThat("1", equalTo(column1Actual.getPosition()));

        KanboardColumn column2Actual = this.service.column()
                                                   .getUtil()
                                                   .findByPosition(columnsActual, String.valueOf(2));
        assertThat(column2Actual, is(not(nullValue())));
        assertThat(projectExpected.getId(), equalTo(column2Actual.getProjectId()));
        assertThat("column 2", equalTo(column2Actual.getTitle()));
        assertThat("2", equalTo(column2Actual.getPosition()));

        KanboardColumn column3Actual = this.service.column()
                                                   .getUtil()
                                                   .findByPosition(columnsActual, String.valueOf(3));
        assertThat(column3Actual, is(not(nullValue())));
        assertThat(projectExpected.getId(), equalTo(column3Actual.getProjectId()));
        assertThat("Work in progress", equalTo(column3Actual.getTitle()));
        assertThat("3", equalTo(column3Actual.getPosition()));

        KanboardColumn column4Actual = this.service.column()
                                                   .getUtil()
                                                   .findByPosition(columnsActual, String.valueOf(4));
        assertThat(column4Actual, is(not(nullValue())));
        assertThat(projectExpected.getId(), equalTo(column4Actual.getProjectId()));
        assertThat("column 4", equalTo(column4Actual.getTitle()));
        assertThat("4", equalTo(column4Actual.getPosition()));
    }
}
