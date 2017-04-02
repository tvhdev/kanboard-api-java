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
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import de.livingfire.kanboard.configuration.KanboardApi;
import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.domain.KanboardProject;
import de.livingfire.kanboard.domain.KanboardSwimlane;

public class KanboardServiceSwimlaneIntegrationTest implements KanboardConstant {

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
    public void testSwimlaneApi() {
        final String projectNameExpected = "project" + UUID.randomUUID();
        final String projectDescriptionExpected = "description" + UUID.randomUUID();

        KanboardProject projectTemplate = new KanboardProject();
        projectTemplate.setObjectName(projectNameExpected);
        projectTemplate.setDescription(projectDescriptionExpected);

        KanboardProject projectCreated = this.service.project()
                                                     .create(projectTemplate);
        assertThat(projectCreated, is(not(nullValue())));
        assertThat(Integer.valueOf(projectCreated.getId()), is(greaterThan(0)));

        // test no swimelines available
        List<KanboardSwimlane> swimlanesGetAll = this.service.swimlane()
                                                             .getAll(projectCreated);

        assertThat(swimlanesGetAll.size(), is(1));

        // test add swimlane
        String swimlaneNameExpected = "Swimlane 1";
        String swimlaneDescriptionExpected = "Swimlane description 1";
        KanboardSwimlane swimlane1 = new KanboardSwimlane();
        swimlane1.setObjectName(swimlaneNameExpected);
        swimlane1.setDescription(swimlaneDescriptionExpected);
        swimlane1.setProjectId(projectCreated.getId());

        swimlane1 = this.service.swimlane()
                                .add(swimlane1);
        assertThat(swimlane1, is(not(nullValue())));
        assertThat(Integer.valueOf(swimlane1.getId()), is(greaterThan(0)));

        // test getById
        KanboardSwimlane swimlaneById = this.service.swimlane()
                                                    .getById(swimlane1);
        assertThat(swimlaneById, is(not(nullValue())));
        assertThat(Integer.valueOf(swimlaneById.getId()), is(greaterThan(0)));
        assertThat(swimlaneById.getObjectName(), equalTo(swimlaneNameExpected));
        assertThat(swimlaneById.getDescription(), equalTo(swimlaneDescriptionExpected));

        // test getByName
        KanboardSwimlane swimlaneByName = this.service.swimlane()
                                                      .getByName(projectCreated.getId(), swimlaneNameExpected);
        assertThat(swimlaneByName, is(not(nullValue())));
        assertThat(Integer.valueOf(swimlaneByName.getId()), is(greaterThan(0)));
        assertThat(swimlaneByName.getObjectName(), equalTo(swimlaneNameExpected));
        assertThat(swimlaneByName.getDescription(), equalTo(swimlaneDescriptionExpected));

        // test getByName fail
        KanboardSwimlane swimlaneByNameFail = this.service.swimlane()
                                                          .getByName(projectCreated.getId(), UUID.randomUUID()
                                                                                                 .toString());

        assertThat(swimlaneByNameFail, is(nullValue()));

        // get default
        KanboardSwimlane swimlaneDefault = this.service.swimlane()
                                                       .getDefault(projectCreated.getId());

        assertThat(swimlaneDefault, is(not(nullValue())));
        assertThat(swimlaneDefault.getId(), is(not(nullValue())));

        // test update swimlane
        String nameDefaultExpected = "Name 42";
        String descriptionDefaultExpected = "Description 42";

        KanboardSwimlane swimlaneUpdate = swimlane1;
        swimlaneUpdate.setObjectName(nameDefaultExpected);
        swimlaneUpdate.setDescription(descriptionDefaultExpected);

        this.service.swimlane()
                    .update(swimlaneUpdate);

        KanboardSwimlane swimlaneUpdateActual = this.service.swimlane()
                                                            .getById(swimlaneUpdate);

        assertThat(swimlaneUpdateActual.getObjectName(), equalTo(nameDefaultExpected));
        assertThat(swimlaneUpdateActual.getDescription(), equalTo(descriptionDefaultExpected));

        // disable swimlane
        KanboardSwimlane swimlaneDisable = swimlane1;
        this.service.swimlane()
                    .disable(swimlaneDisable);

        KanboardSwimlane swimlaneDisabledActual = this.service.swimlane()
                                                              .getById(swimlaneDisable);
        assertThat(swimlaneDisabledActual.getIsActive(), equalTo("0"));

        // enable swimlane
        KanboardSwimlane swimlaneEnable = swimlaneDisable;
        this.service.swimlane()
                    .enable(swimlaneEnable);

        KanboardSwimlane swimlaneEnableActual = this.service.swimlane()
                                                            .getById(swimlaneEnable);
        assertThat(swimlaneEnableActual.getIsActive(), equalTo("1"));

        // change swimline position
        String swimlaneName2Expected = "Swimlane 2";
        String swimlaneDescription2Expected = "Swimlane description 2";

        KanboardSwimlane swimlane2 = new KanboardSwimlane();
        swimlane2.setObjectName(swimlaneName2Expected);
        swimlane2.setDescription(swimlaneDescription2Expected);
        swimlane2.setProjectId(projectCreated.getId());

        swimlane2 = this.service.swimlane()
                                .add(swimlane2);
        assertThat(swimlane2, is(not(nullValue())));
        assertThat(Integer.valueOf(swimlane2.getId()), is(greaterThan(0)));
        assertThat(swimlane2.getPosition(), equalTo("3"));

        String positionExpected = "1";
        swimlane2.setPosition(positionExpected);
        this.service.swimlane()
                    .changePosition(swimlane2);

        KanboardSwimlane swimlanePositionAfter = this.service.swimlane()
                                                             .getById(swimlane2);
        assertThat(swimlanePositionAfter.getPosition(), equalTo(positionExpected));

        // remove swimlane
        String removeId = swimlane2.getId();
        this.service.swimlane()
                    .remove(swimlane2);

        assertThat(
                this.service.swimlane()
                            .getById(removeId),
                    is(nullValue()));
    }
}
