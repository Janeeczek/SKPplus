package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.ActiveUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ActiveUserServiceImpl implements ActiveUserService {
    @Autowired
    private ActiveUsersRepository activeUsersRepository;
    @Autowired
    private UserService userService;




    @Override
    public ActiveUsers findByEmail(String email) {
        return activeUsersRepository.findByEmail(email);
    }
    @Override
    public ActiveUsers findByUsername(String username) {
        return activeUsersRepository.findByUsername(username);
    }
    @Override
    public ActiveUsers save(User user) {
        ActiveUsers activeUsers = new ActiveUsers(user);
        userService.updateLastActiveTime(user.getUsername());
        return activeUsersRepository.save(activeUsers);
    }
    @Override
    public ActiveUsers save(MyUserPrincipal myUserPrincipal) {
        ActiveUsers activeUsers = new ActiveUsers(myUserPrincipal);
        userService.updateLastActiveTime(myUserPrincipal.getUsername());
        return activeUsersRepository.save(activeUsers);
    }

    @Override
    public void delete(User user) {
        userService.updateLastActiveTime(user.getUsername());
        activeUsersRepository.deleteByUsername(user.getUsername());
    }
    @Override
    public void delete(MyUserPrincipal principal) {
        userService.updateLastActiveTime(principal.getUsername());
        activeUsersRepository.deleteByUsername(principal.getUsername());
    }

    @Override
    public void delete(String username) {
        userService.updateLastActiveTime(username);
        activeUsersRepository.deleteByUsername(username);
    }

    @Override
    public void deleteOnInit() {
        activeUsersRepository.deleteAll();
    }

    @Override
    public boolean isActive(String username) {
        if (activeUsersRepository.findByUsername(username) == null) {
            return false;
        } else {
            return true;
        }

    }
}
