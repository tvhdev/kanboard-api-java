package de.livingfire.kanboard.domain;

public class KanboardUser implements KanboardObject {

    private String id;
    private String username;
    private String password;
    private String role;
    private String isLdapUser;
    private String name;
    private String email;
    private String googleId;
    private String githubId;
    private String notificationEnabled;

    public KanboardUser() {
        super();
    }

    @Override
    public String getObjectName() {
        return getUsername();
    }

    @Override
    public void setObjectName(String objectName) {
        setUsername(objectName);
    }

    @Override
    public String toString() {
        return "KanboardUser [id=" + this.id + ", username=" + this.username + ", password=" + this.password + ", role="
                + this.role + ", isLdapUser=" + this.isLdapUser + ", name=" + this.name + ", email=" + this.email
                + ", googleId=" + this.googleId + ", githubId=" + this.githubId + ", notificationEnabled="
                + this.notificationEnabled + "]";
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIsLdapUser() {
        return this.isLdapUser;
    }

    public void setIsLdapUser(String isLdapUser) {
        this.isLdapUser = isLdapUser;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoogleId() {
        return this.googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getGithubId() {
        return this.githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public String getNotificationEnabled() {
        return this.notificationEnabled;
    }

    public void setNotificationEnabled(String notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }

}
