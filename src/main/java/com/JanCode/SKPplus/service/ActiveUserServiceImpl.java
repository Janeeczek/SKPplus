package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.AccountType;
import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.ActiveUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        userService.updateLastActiveTime(user.getUserName());
        return activeUsersRepository.save(activeUsers);
    }

    @Override
    public List<ActiveUsers> findAll() {
        return activeUsersRepository.findAll();
    }

    @Override
    public int findAllEmployees() {
        List<ActiveUsers> activeUsersList = activeUsersRepository.findAll();
        List<ActiveUsers> activeEmp = new ArrayList<>();
        if(activeUsersList.size() > 0)
        {
            for(ActiveUsers activeUsers : activeUsersList) {
                User user = userService.findByUsername(activeUsers.getUsername());
                if(user != null) {
                    if(user.getAccountType() == AccountType.DIAGNOSTYKA || user.getAccountType() == AccountType.KSIEGOWOSC) activeEmp.add(activeUsers);
                }
            }
            return activeEmp.size();
        }
        return 0;
    }

    @Override
    public ActiveUsers save(MyUserPrincipal myUserPrincipal) {
        ActiveUsers activeUsers = new ActiveUsers(myUserPrincipal);
        userService.updateLastActiveTime(myUserPrincipal.getUsername());
        return activeUsersRepository.save(activeUsers);
    }

    @Override
    public void delete(User user) {
        userService.updateLastActiveTime(user.getUserName());
        activeUsersRepository.deleteByUsername(user.getUserName());
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
