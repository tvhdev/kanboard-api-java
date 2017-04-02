package de.livingfire.kanboard.constants;

public interface KanboardConstant {

    public static final String PROFILE_DEVELOPMENT = "development";
    public static final String PROFILE_BUILD = "build";
    public static final String PROFILE = PROFILE_BUILD;

    public static final boolean SHOW_HTTP_REQUESTS = false;

    public static final String KANBOARD_USER_DEFAULT = "admin";
    public static final String KANBOARD_USER_DEFAULT_PASSWORD = "admin";

    public static final String ACTION_NAME_TASK_CLOSE_COLUMN = "\\Kanboard\\Action\\TaskCloseColumn";

    public static final String EVENT_NAME_TASK_MOVE_COLUMN = "task.move.column";

    public static final String METHOD_ACTION_AVAILABLE_ACTIONS = "getAvailableActions";
    public static final String METHOD_ACTION_AVAILABLE_ACTION_EVENTS = "getAvailableActionEvents";
    public static final String METHOD_ACTION_AVAILABLE_ACTION_EVENTS_COMPATIBLE = "getCompatibleActionEvents";
    public static final String METHOD_ACTION_CREATE = "createAction";
    public static final String METHOD_ACTION_GET_ALL = "getActions";
    public static final String METHOD_APPLICATION_GET_TIMEZONE = "getTimezone";
    public static final String METHOD_COLUMN_ADD = "addColumn";
    public static final String METHOD_COLUMN_BY_ID = "getColumn";
    public static final String METHOD_COLUMN_CHANGE_POSITON = "changeColumnPosition";
    public static final String METHOD_COLUMN_GET_ALL = "getColumns";
    public static final String METHOD_COLUMN_REMOVE = "removeColumn";
    public static final String METHOD_COLUMN_UPDATE = "updateColumn";
    public static final String METHOD_PROJECT_ADD_USER = "addProjectUser";
    public static final String METHOD_PROJECT_CREATE = "createProject";
    public static final String METHOD_PROJECT_GET_ALL = "getAllProjects";
    public static final String METHOD_PROJECT_GET_BY_ID = "getProjectById";
    public static final String METHOD_PROJECT_GET_BY_NAME = "getProjectByName";
    public static final String METHOD_PROJECT_UPDATE = "updateProject";
    public static final String METHOD_SWIMLANE_ADD = "addSwimlane";
    public static final String METHOD_SWIMLANE_CHANGE_POSITION = "changeSwimlanePosition";
    public static final String METHOD_SWIMLANE_DISABLE = "disableSwimlane";
    public static final String METHOD_SWIMLANE_ENABLE = "enableSwimlane";
    public static final String METHOD_SWIMLANE_GET_ALL = "getAllSwimlanes";
    public static final String METHOD_SWIMLANE_GET_BY_ID = "getSwimlaneById";
    public static final String METHOD_SWIMLANE_GET_BY_NAME = "getSwimlaneByName";
    public static final String METHOD_SWIMLANE_REMOVE = "removeSwimlane";
    public static final String METHOD_SWIMLANE_UPDATE = "updateSwimlane";
    public static final String METHOD_TASK_BY_ID = "getTask";
    public static final String METHOD_TASK_CREATE = "createTask";
    public static final String METHOD_TASK_GET_ALL = "getAllTasks";
    public static final String METHOD_TASK_MOVE_POSITION = "moveTaskPosition";
    public static final String METHOD_TASK_UPDATE = "updateTask";
    public static final String METHOD_USER_CREATE = "createUser";
    public static final String METHOD_USER_DISABLE = "disableUser";
    public static final String METHOD_USER_GET_BY_ID = "getUser";
    public static final String METHOD_USER_GET_BY_USERNAME = "getUserByName";
    public static final String METHOD_USER_UPDATE = "updateUser";

    public static final String ROLE_APP_ADMIN = "app-admin";
    public static final String ROLE_APP_MANAGER = "app-manager";
    public static final String ROLE_APP_PUBLIC = "app-public";
    public static final String ROLE_APP_USER = "app-user";
    public static final String ROLE_PROJECT_MANAGER = "project-manager";
    public static final String ROLE_PROJECT_MEMBER = "project-member";
    public static final String ROLE_PROJECT_VIEWER = "project-viewer";

    public static final String PARAM_ACTION_NAME = "action_name";
    public static final String PARAM_CATEGORY_ID = "category_id";
    public static final String PARAM_COLOR_ID = "color_id";
    public static final String PARAM_COLUMN_ID = "column_id";
    public static final String PARAM_CREATOR_ID = "creator_id";
    public static final String PARAM_DATE_COMPLETED = "date_completed";
    public static final String PARAM_DATE_CREATION = "date_creation";
    public static final String PARAM_DATE_DUE = "date_due";
    public static final String PARAM_DATE_MODIFICATION = "date_modification";
    public static final String PARAM_DATE_MOVED = "date_moved";
    public static final String PARAM_DATE_STARTED = "date_started";
    public static final String PARAM_DEFAULT_SWIMLANE = "default_swimlane";
    public static final String PARAM_DESCRIPTION = "description";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_EVENT_NAME = "event_name";
    public static final String PARAM_GITHUB_ID = "github_id";
    public static final String PARAM_GOOGLE_ID = "google_id";
    public static final String PARAM_HIDE_IN_DASHBOARD = "hide_in_dashboard";
    public static final String PARAM_ID = "id";
    public static final String PARAM_IDENTIFIER = "identifier";
    public static final String PARAM_IS_ACTIVE = "is_active";
    public static final String PARAM_IS_LDAP_USER = "is_ldap_user";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_NOTIFICATIONS_ENABLED = "notifications_enabled";
    public static final String PARAM_OWNER_ID = "owner_id";
    public static final String PARAM_PARAMS = "params";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_POSITION = "position";
    public static final String PARAM_PRIORITY = "priority";
    public static final String PARAM_PROJECT_ID = "project_id";
    public static final String PARAM_RECURRENCE_BASEDATE = "recurrence_basedate";
    public static final String PARAM_RECURRENCE_CHILD = "recurrence_child";
    public static final String PARAM_RECURRENCE_FACTOR = "recurrence_factor";
    public static final String PARAM_RECURRENCE_PARENT = "recurrence_parent";
    public static final String PARAM_RECURRENCE_STATUS = "recurrence_status";
    public static final String PARAM_RECURRENCE_TIMEFRAME = "recurrence_timeframe";
    public static final String PARAM_RECURRENCE_TRIGGER = "recurrence_trigger";
    public static final String PARAM_REFERENCE = "reference";
    public static final String PARAM_ROLE = "role";
    public static final String PARAM_SCORE = "score";
    public static final String PARAM_SHOW_DEFAULT_SWIMLANE = "show_default_swimlane";
    public static final String PARAM_STATUS_ID = "status_id";
    public static final String PARAM_SWIMLANE_ID = "swimlane_id";
    public static final String PARAM_TASK_ID = "task_id";
    public static final String PARAM_TASK_LIMIT = "task_limit";
    public static final String PARAM_TIME_ESTIMATED = "time_estimated";
    public static final String PARAM_TIME_SPEND = "time_spent";
    public static final String PARAM_TITLE = "title";
    public static final String PARAM_URL = "url";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_USER_ID = "user_id";

    public static final String STATUS_ACTIVE = "1";
    public static final String STATUS_INACTIVE = "0";

    public static final String REQUEST_JSONRPC_DEFAULT = "2.0";
    public static final String REQUEST_ID_DEFAULT = "667";

    public static final String DEFAULT_SWIMLANE_NAME = "Default swimlane";
}
