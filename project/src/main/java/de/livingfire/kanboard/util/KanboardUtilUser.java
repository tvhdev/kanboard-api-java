package de.livingfire.kanboard.util;

import java.util.HashMap;
import java.util.Map;

import de.livingfire.kanboard.domain.KanboardUser;

public class KanboardUtilUser extends KanboardUtil<KanboardUser> {

    @Override
    public Map<String, String> convertToMap(KanboardUser user) {
        if (user == null) {
            return null;
        }
        Map<String, String> h = new HashMap<>();
        h.put(PARAM_ID, user.getId());
        h.put(PARAM_USERNAME, user.getUsername());
        h.put(PARAM_PASSWORD, user.getPassword());
        h.put(PARAM_ROLE, user.getRole());
        h.put(PARAM_IS_LDAP_USER, user.getIsLdapUser());
        h.put(PARAM_NAME, user.getName());
        h.put(PARAM_EMAIL, user.getEmail());
        h.put(PARAM_GOOGLE_ID, user.getGoogleId());
        h.put(PARAM_GITHUB_ID, user.getGithubId());
        h.put(PARAM_NOTIFICATIONS_ENABLED, user.getNotificationEnabled());
        return h;
    }

    @Override
    public KanboardUser convertToObject(Map<String, String> h) {
        if (h == null) {
            return null;
        }
        KanboardUser user = new KanboardUser();
        user.setId(h.get(PARAM_ID));
        user.setUsername(h.get(PARAM_USERNAME));
        user.setPassword(h.get(PARAM_PASSWORD));
        user.setRole(h.get(PARAM_ROLE));
        user.setIsLdapUser(h.get(PARAM_IS_LDAP_USER));
        user.setName(h.get(PARAM_NAME));
        user.setEmail(h.get(PARAM_EMAIL));
        user.setGoogleId(h.get(PARAM_GOOGLE_ID));
        user.setGithubId(h.get(PARAM_GITHUB_ID));
        user.setNotificationEnabled(h.get(PARAM_NOTIFICATIONS_ENABLED));
        return user;
    }

}
