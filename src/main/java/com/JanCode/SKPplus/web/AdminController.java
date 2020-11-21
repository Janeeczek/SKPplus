package com.JanCode.SKPplus.web;

import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.repository.ActiveUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/admin")
public class AdminController extends MainController{

    @GetMapping
    public String showAdmin() {
        return "/admin/admin";
    }
}
