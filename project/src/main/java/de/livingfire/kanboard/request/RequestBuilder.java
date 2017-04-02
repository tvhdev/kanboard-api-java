package de.livingfire.kanboard.request;

import java.util.ArrayList;
import java.util.Map;

import de.livingfire.kanboard.constants.KanboardConstant;
import de.livingfire.kanboard.rest.KanboardRequest;

public abstract class RequestBuilder implements KanboardConstant {

    protected static final ArrayList<String> PARAMS_ALLOWED_SWIMLANE_CHANGE_POSITION;
    static {
        PARAMS_ALLOWED_SWIMLANE_CHANGE_POSITION = new ArrayList<>();
        PARAMS_ALLOWED_SWIMLANE_CHANGE_POSITION.add(PARAM_PROJECT_ID);
        PARAMS_ALLOWED_SWIMLANE_CHANGE_POSITION.add(PARAM_SWIMLANE_ID);
        PARAMS_ALLOWED_SWIMLANE_CHANGE_POSITION.add(PARAM_POSITION);
    }

    protected static final ArrayList<String> PARAMS_ALLOWED_PROJECT_UPDATE;
    static {
        PARAMS_ALLOWED_PROJECT_UPDATE = new ArrayList<>();
        PARAMS_ALLOWED_PROJECT_UPDATE.add(PARAM_PROJECT_ID);
        PARAMS_ALLOWED_PROJECT_UPDATE.add(PARAM_NAME);
        PARAMS_ALLOWED_PROJECT_UPDATE.add(PARAM_DESCRIPTION);
        PARAMS_ALLOWED_PROJECT_UPDATE.add(PARAM_OWNER_ID);
        PARAMS_ALLOWED_PROJECT_UPDATE.add(PARAM_IDENTIFIER);
    }

    protected static final ArrayList<String> PARAMS_ALLOWED_USER_UPDATE;
    static {
        PARAMS_ALLOWED_USER_UPDATE = new ArrayList<>();
        PARAMS_ALLOWED_USER_UPDATE.add(PARAM_USERNAME);
        PARAMS_ALLOWED_USER_UPDATE.add(PARAM_PASSWORD);
        PARAMS_ALLOWED_USER_UPDATE.add(PARAM_NAME);
        PARAMS_ALLOWED_USER_UPDATE.add(PARAM_EMAIL);
        PARAMS_ALLOWED_USER_UPDATE.add(PARAM_ROLE);
    }

    protected static final ArrayList<String> PARAMS_ALLOWED_USER_CREATE;
    static {
        PARAMS_ALLOWED_USER_CREATE = new ArrayList<>();
        PARAMS_ALLOWED_USER_CREATE.add(PARAM_USERNAME);
        PARAMS_ALLOWED_USER_CREATE.add(PARAM_PASSWORD);
        PARAMS_ALLOWED_USER_CREATE.add(PARAM_NAME);
        PARAMS_ALLOWED_USER_CREATE.add(PARAM_EMAIL);
        PARAMS_ALLOWED_USER_CREATE.add(PARAM_ROLE);
    }

    protected static final ArrayList<String> PARAMS_ALLOWED_PROJECT_CREATE;
    static {
        PARAMS_ALLOWED_PROJECT_CREATE = new ArrayList<>();
        PARAMS_ALLOWED_PROJECT_CREATE.add(PARAM_NAME);
        PARAMS_ALLOWED_PROJECT_CREATE.add(PARAM_DESCRIPTION);
        PARAMS_ALLOWED_PROJECT_CREATE.add(PARAM_OWNER_ID);
        PARAMS_ALLOWED_PROJECT_CREATE.add(PARAM_IDENTIFIER);
    }

    protected static final ArrayList<String> PARAMS_ALLOWED_COLUMN_UPDATE;
    static {
        PARAMS_ALLOWED_COLUMN_UPDATE = new ArrayList<>();
        PARAMS_ALLOWED_COLUMN_UPDATE.add(PARAM_COLUMN_ID);
        PARAMS_ALLOWED_COLUMN_UPDATE.add(PARAM_TITLE);
        PARAMS_ALLOWED_COLUMN_UPDATE.add(PARAM_TASK_LIMIT);
        PARAMS_ALLOWED_COLUMN_UPDATE.add(PARAM_DESCRIPTION);
    }

    protected static final ArrayList<String> PARAMS_ALLOWED_TASK_UPDATE;
    static {
        PARAMS_ALLOWED_TASK_UPDATE = new ArrayList<>();
        PARAMS_ALLOWED_TASK_UPDATE.add(PARAM_COLOR_ID);
        PARAMS_ALLOWED_TASK_UPDATE.add(PARAM_OWNER_ID);
        PARAMS_ALLOWED_TASK_UPDATE.add(PARAM_DATE_DUE);
        PARAMS_ALLOWED_TASK_UPDATE.add(PARAM_DESCRIPTION);
        PARAMS_ALLOWED_TASK_UPDATE.add(PARAM_CATEGORY_ID);
        PARAMS_ALLOWED_TASK_UPDATE.add(PARAM_SCORE);
        PARAMS_ALLOWED_TASK_UPDATE.add(PARAM_PRIORITY);
        PARAMS_ALLOWED_TASK_UPDATE.add(PARAM_RECURRENCE_STATUS);
        PARAMS_ALLOWED_TASK_UPDATE.add(PARAM_RECURRENCE_TRIGGER);
        PARAMS_ALLOWED_TASK_UPDATE.add(PARAM_RECURRENCE_FACTOR);
        PARAMS_ALLOWED_TASK_UPDATE.add(PARAM_RECURRENCE_TIMEFRAME);
        PARAMS_ALLOWED_TASK_UPDATE.add(PARAM_RECURRENCE_BASEDATE);
    }

    protected static final ArrayList<String> PARAMS_ALLOWED_TASK_CREATE;
    static {
        PARAMS_ALLOWED_TASK_CREATE = new ArrayList<>();
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_COLOR_ID);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_COLUMN_ID);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_OWNER_ID);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_CREATOR_ID);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_DATE_DUE);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_DESCRIPTION);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_CATEGORY_ID);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_SCORE);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_SWIMLANE_ID);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_PRIORITY);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_RECURRENCE_STATUS);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_RECURRENCE_TRIGGER);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_RECURRENCE_FACTOR);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_RECURRENCE_TIMEFRAME);
        PARAMS_ALLOWED_TASK_CREATE.add(PARAM_RECURRENCE_BASEDATE);
    }

    protected static final ArrayList<String> PARAMS_ALLOWED_TASK_MOVE_POSITION;
    static {
        PARAMS_ALLOWED_TASK_MOVE_POSITION = new ArrayList<>();
        PARAMS_ALLOWED_TASK_MOVE_POSITION.add(PARAM_PROJECT_ID);
        PARAMS_ALLOWED_TASK_MOVE_POSITION.add(PARAM_TASK_ID);
        PARAMS_ALLOWED_TASK_MOVE_POSITION.add(PARAM_COLUMN_ID);
        PARAMS_ALLOWED_TASK_MOVE_POSITION.add(PARAM_SWIMLANE_ID);
    }

    protected void addParams(KanboardRequest request,
                             Map<String, String> params,
                             ArrayList<String> paramsAllowed) {
        paramsAllowed.stream()
                     .filter(p -> params.get(p) != null)
                     .filter(p -> !params.get(p)
                                         .isEmpty())
                     .forEach(p -> request.getParams()
                                          .put(p, params.get(p)));
    }

}
