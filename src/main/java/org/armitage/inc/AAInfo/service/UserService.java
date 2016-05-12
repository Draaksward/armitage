package org.armitage.inc.AAInfo.service;

import org.armitage.inc.AAInfo.entity.User;

public interface UserService {

    void createSecurityKey(User user);

    void clearSecurityKey(String userName);

    User getUserByLogin(String login);

    boolean preAuthUserCheck(String username, String password);

}
