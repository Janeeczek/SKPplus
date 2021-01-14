package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.User;
import org.springframework.security.core.Authentication;

public interface ActiveUserService {
    ActiveUsers findByUsername(String username);
    ActiveUsers findByEmail(String email);
    ActiveUsers save(User user);
    ActiveUsers save(MyUserPrincipal myUserPrincipal);
    void delete(User user);
    void delete(MyUserPrincipal principal);
    void delete(String username);
    void deleteOnInit();
    boolean isActive(String username);
}
