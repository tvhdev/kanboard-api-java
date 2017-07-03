package de.livingfire.kanboard;

import java.io.File;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import de.livingfire.kanboard.configuration.KanboardConfiguration;
import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.domain.KanboardAction;
import de.livingfire.kanboard.domain.KanboardColumn;
import de.livingfire.kanboard.domain.KanboardProject;
import de.livingfire.kanboard.domain.KanboardUser;
import de.livingfire.kanboard.exception.KanboardException;
import de.livingfire.kanboard.service.KanboardService;

public class KanboardSetupIntegrationTest implements KanboardConstant {

    @Test
    public void setupKanbanBoard() throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        KanboardConfiguration kanboardConfiguration = mapper.readValue(
                new File("src/test/resources/application.yml"),
                    KanboardConfiguration.class);

        KanboardService service = new KanboardService();
        service.setApi(kanboardConfiguration.getKanboardApi());
        service.setVerbose(KanboardConstant.SHOW_HTTP_REQUESTS);

        KanboardUser user = service.user()
                                   .enshure(kanboardConfiguration.getKanboardSetup()
                                                                 .getUser());

        kanboardConfiguration.getKanboardSetup()
                             .getProjects()
                             .stream()
                             .forEach(template -> {
                                 KanboardProject project = service.project()
                                                                  .enshure(template);
                                 service.project()
                                        .addUser(project.getId(), user.getId(), user.getRole());

                                 template.getSwimlanes()
                                         .stream()
                                         .forEach(s -> {
                                             s.setProjectId(project.getId());
                                             service.swimlane()
                                                    .enshure(s);
                                         });

                                 template.getColumns()
                                         .stream()
                                         .forEach(c -> {
                                             c.setProjectId(project.getId());
                                         });
                                 service.column()
                                        .enshureOrder(template.getColumns());

                                 template.getTasks()
                                         .stream()
                                         .forEach(t -> {
                                             t.setProjectId(project.getId());
                                             service.task()
                                                    .enshure(t);
                                         });

                                 template.getActions()
                                         .stream()
                                         .forEach(templateAction -> {
                                             templateAction.setProjectId(project.getId());

                                             if (templateAction.getParams()
                                                               .keySet()
                                                               .contains(PARAM_COLUMN_ID)) {
                                                 String columnName = templateAction.getParams()
                                                                                   .get(PARAM_COLUMN_ID);
                                                 KanboardColumn column = new KanboardColumn();
                                                 column.setObjectName(columnName);
                                                 column.setProjectId(project.getId());

                                                 column = service.column()
                                                                 .getByObjectName(column);
                                                 if (column == null) {
                                                     throw new KanboardException(
                                                             "unable to find column: " + columnName);
                                                 }
                                                 templateAction.getParams()
                                                               .put(PARAM_COLUMN_ID, column.getId());
                                             }

                                             KanboardAction found = service.action()
                                                                           .getAll(project.getId())
                                                                           .stream()
                                                                           .filter(c -> c.equalSimple(templateAction))
                                                                           .findFirst()
                                                                           .orElse(null);
                                             if (found == null) {
                                                 service.action()
                                                        .create(templateAction);
                                             }
                                         });
                             });

    }

}
