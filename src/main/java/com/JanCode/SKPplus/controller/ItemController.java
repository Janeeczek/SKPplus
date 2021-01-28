package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.*;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ItemController {
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemStorageService itemStorageService;

    @GetMapping("/item/dodajItem")
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
    @GetMapping("/item/editItem/{id}")
    public ModelAndView showEdytujItem(Authentication authentication,@PathVariable long id) {
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
    @GetMapping("/item/wydajItem")
    public ModelAndView showWydajItem(Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC || mode == AccountType.DIAGNOSTYKA) {

                modelAndView = new ModelAndView("/user/wydajItem","mode",mode.name());
                modelAndView.addObject("ss",null);
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("/error");
        return modelAndView;
    }
    @GetMapping("/item/listaItem")
    public ModelAndView showListaItem(Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if (mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC || mode == AccountType.DIAGNOSTYKA) {
                List<ItemStorage> itemStorageList = itemStorageService.getAllItemStorage();
                if (itemStorageList == null) return new ModelAndView("/error");
                modelAndView = new ModelAndView("/user/listaItem","mode",mode.name());
                modelAndView.addObject("storageList",itemStorageList);
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("/error");
        return modelAndView;
    }
    @PostMapping("/item/dodajItem/save")
    public ModelAndView zapiszItem(@ModelAttribute @Valid ItemDto itemDto, BindingResult bindingResult, Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {
                if (bindingResult.hasErrors()) {
                    modelAndView = new ModelAndView("/user/dodajNowyItem","mode",mode.name());
                    modelAndView.addObject("itemDto",itemDto);
                    modelAndView.addObject("ErrorMessage","Jest błąd!");
                    return modelAndView;
                }
                User user = userService.findByUsername(sourcePrincipal.getUsername());
                Item item = itemService.createItem(user,itemDto);
                ItemStorage itemStorage = itemStorageService.addItem(item,itemDto.getQuantity());
                if(itemStorage == null || item == null || user == null) {
                    modelAndView = new ModelAndView("/user/dodajNowyItem","mode",mode.name());
                    modelAndView.addObject("itemDto",itemDto);
                    modelAndView.addObject("ErrorMessage","Jest błąd!");
                    return modelAndView;
                }
                modelAndView = new ModelAndView("/user/dodajNowyItem","mode",mode.name());
                modelAndView.addObject("SuccessMessage","Pomyślnie dodano nowy przedmiot");
                modelAndView.addObject("itemDto",new ItemDto());
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("/error");
        return modelAndView;

    }
    @GetMapping("/item/delete/{id}")
    public ModelAndView deleteItem(Authentication authentication,@PathVariable long id) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {
                itemStorageService.removeItemStorage(id);
                modelAndView = new ModelAndView("redirect:/item/listaItem");
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("/error");
        return modelAndView;
    }
}
