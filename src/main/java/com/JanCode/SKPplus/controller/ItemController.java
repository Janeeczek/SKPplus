package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.AccountType;
import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.service.ItemService;
import com.JanCode.SKPplus.service.ItemStorageService;
import com.JanCode.SKPplus.service.UserService;
import com.JanCode.SKPplus.web.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController {
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemStorageService itemStorageService;

    @GetMapping("/dodajItem")
    public ModelAndView showDodajItem(Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {
                modelAndView = new ModelAndView("/user/dodajNowyItem","mode",mode.name());
                modelAndView.addObject("itemDto",new ItemDto());
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("/error");
        return modelAndView;
    }
    @GetMapping("/wydajItem")
    public ModelAndView showWydajItem(Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        modelAndView = new ModelAndView("/error");
        return modelAndView;
    }
    @GetMapping("/listaItem")
    public ModelAndView showListaItem(Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        modelAndView = new ModelAndView("/error");
        return modelAndView;
    }
    @PostMapping("/saveItem")
    public ModelAndView zapiszItem( @ModelAttribute ItemDto itemDto, BindingResult bindingResult,Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {
                if (bindingResult.hasErrors()) {
                    modelAndView = new ModelAndView("/user/dodajNowyItem","mode",mode.name());
                    modelAndView.addObject("itemDto",new ItemDto());
                    return modelAndView;
                }
                User user = userService.findByUsername(sourcePrincipal.getUsername());
                Item item = itemService.createItem(user,itemDto);
                itemStorageService.addItem(item,0);
                modelAndView = new ModelAndView("redirect:/");
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("/error");
        return modelAndView;

    }
}
