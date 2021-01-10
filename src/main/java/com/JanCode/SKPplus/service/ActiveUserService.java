package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.User;
import org.springframework.security.core.Authentication;

public interface ActiveUserService {
    ActiveUsers findByEmail(String email);
    ActiveUsers findByUsername(String username);
    ActiveUsers save(User user);
    void delete(User user);
    void delete(MyUserPrincipal principal);
    boolean isActive(String email);
}
