package de.livingfire.kanboard.configuration;

public interface KanboardApi {

    void setApiUser(String apiUser);

    String getApiUser();

    void setApiVersion(String apiVersion);

    String getApiVersion();

    void setApiHeader(String apiHeader);

    String getApiHeader();

    void setApiAuthToken(String apiAuthToken);

    String getApiAuthToken();

    void setApiUrl(String apiUrl);

    String getApiUrl();

}
