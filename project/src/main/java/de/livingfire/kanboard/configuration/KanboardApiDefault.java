package de.livingfire.kanboard.configuration;

public class KanboardApiDefault implements KanboardApi {

    private String apiUrl;
    private String apiAuthToken;
    private String apiHeader;
    private String apiVersion;
    private String apiUser;

    public KanboardApiDefault() {
        super();
    }

    public KanboardApiDefault(String apiUrl,
                              String apiAuthToken,
                              String apiHeader,
                              String apiVersion,
                              String apiUser) {
        super();
        this.apiUrl = apiUrl;
        this.apiAuthToken = apiAuthToken;
        this.apiHeader = apiHeader;
        this.apiVersion = apiVersion;
        this.apiUser = apiUser;
    }

    @Override
    public String toString() {
        return "KanboardApiConfigurationDefault [apiUrl=" + this.apiUrl + ", apiAuthToken=*****, apiHeader="
                + this.apiHeader + ", apiVersion=" + this.apiVersion + ", apiUser=" + this.apiUser + "]";
    }

    @Override
    public String getApiUrl() {
        return this.apiUrl;
    }

    @Override
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public String getApiAuthToken() {
        return this.apiAuthToken;
    }

    @Override
    public void setApiAuthToken(String apiAuthToken) {
        this.apiAuthToken = apiAuthToken;
    }

    @Override
    public String getApiHeader() {
        return this.apiHeader;
    }

    @Override
    public void setApiHeader(String apiHeader) {
        this.apiHeader = apiHeader;
    }

    @Override
    public String getApiVersion() {
        return this.apiVersion;
    }

    @Override
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public String getApiUser() {
        return this.apiUser;
    }

    @Override
    public void setApiUser(String apiUser) {
        this.apiUser = apiUser;
    }

}
