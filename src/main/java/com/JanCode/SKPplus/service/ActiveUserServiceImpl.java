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

        return activeUsersRepository.save(activeUsers);
    }

    @Override
    public void delete(User user) {
        activeUsersRepository.deleteeByEmail(user.getEmail());
    }
    @Override
    public void delete(MyUserPrincipal principal) {
        activeUsersRepository.deleteeByEmail(principal.getEmail());
    }

    @Override
    public boolean isActive(String email) {
        if (activeUsersRepository.findByEmail(email) == null) {
            return false;
        } else {
            return true;
        }

    }
}
