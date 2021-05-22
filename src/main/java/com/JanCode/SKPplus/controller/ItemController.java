package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.exeception.QuantityTooSmallException;
import com.JanCode.SKPplus.model.*;
import com.JanCode.SKPplus.service.ItemService;
import com.JanCode.SKPplus.service.ItemStorageService;
import com.JanCode.SKPplus.service.RejestrItemService;
import com.JanCode.SKPplus.service.UserService;
import com.JanCode.SKPplus.web.dto.ItemDto;
import com.JanCode.SKPplus.web.dto.QuantityDto;
import com.JanCode.SKPplus.web.dto.WydajItemDto;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ItemController {
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemStorageService itemStorageService;
    @Autowired
    private RejestrItemService rejestrItemService;

    @GetMapping("/item/add")
    public ModelAndView showAdd(Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {
                modelAndView = new ModelAndView("user/dodajNowyItem","mode",mode.name());
                modelAndView.addObject("itemDto",new ItemDto());
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("error");
        return modelAndView;
    }
    @GetMapping("/item/give")
    public ModelAndView showGive(Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC || mode == AccountType.DIAGNOSTYKA) {
                List<ItemStorage> itemStorageList = itemStorageService.getAllActiveItemStorage();

                modelAndView = new ModelAndView("user/wydajItem","mode",mode.name());

                modelAndView.addObject("wydajItemDto",new WydajItemDto());
                modelAndView.addObject("itemStorageList",itemStorageList);
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("error");
        return modelAndView;
    }
    @GetMapping("/item/edit/{id}")
    public ModelAndView showEdit(Authentication authentication,@PathVariable long id) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {
                ItemStorage itemStorage = itemStorageService.getItemStorage(id);
                modelAndView = new ModelAndView("user/editItem","mode",mode.name());
                modelAndView.addObject("itemFoto",itemStorage);
                modelAndView.addObject("idTemp",id);
                modelAndView.addObject("itemDto",new ItemDto(itemStorage));
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("error");
        return modelAndView;
    }

    @GetMapping("/item/list")
    public ModelAndView showList(Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if (mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC || mode == AccountType.DIAGNOSTYKA) {
                //itemStorageService.updateZetony();
                List<ItemStorage> itemStorageList = itemStorageService.getAllActiveItemStorage();

                if (itemStorageList == null) return new ModelAndView("error");
                modelAndView = new ModelAndView("user/listaItem","mode",mode.name());
                modelAndView.addObject("storageList",itemStorageList);

                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("error");
        return modelAndView;
    }
    @GetMapping("/item/listToday")
    public ModelAndView showListToday(Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if (mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC || mode == AccountType.DIAGNOSTYKA) {
                List<RejestrItem> rejestrItemList = rejestrItemService.getAllFromToday();

                if (rejestrItemList == null) return new ModelAndView("error");
                modelAndView = new ModelAndView("user/listaItemToday","mode",mode.name());
                modelAndView.addObject("rejestrItemList",rejestrItemList);
                modelAndView.addObject("iloscWydanych",rejestrItemList.size());
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("error");
        return modelAndView;
    }
    @GetMapping("/item/archive/{id}")
    public ModelAndView archiveItem(Authentication authentication,@PathVariable long id, RedirectAttributes atts) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {
                User user = userService.findByUsername(sourcePrincipal.getUsername());
                ItemStorage itemStorage =  itemStorageService.archiveItemStorage(id);

                rejestrItemService.addArchiveLog(itemStorage,user);
                modelAndView = new ModelAndView("redirect:/item/list");
                atts.addFlashAttribute("SuccessMessage","Pomyślnie zarchiwizowano przedmiot o id: "+ id);
                return modelAndView;
            }
            modelAndView = new ModelAndView("redirect:/item/list");
            atts.addFlashAttribute("ErrorMessage","Brak uprawienień! Nie można zarchiwizować przedmiotu o id: "+ id);
            return modelAndView;
        }

        modelAndView = new ModelAndView("error");
        return modelAndView;
    }
    @GetMapping("/item/delete/{id}")
    public ModelAndView deleteItem(Authentication authentication,@PathVariable long id, RedirectAttributes atts) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;

        if (sourcePrincipal != null) {
            ItemStorage itemStorage = itemStorageService.getItemStorage(id);
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {
                //ItemStorage itemStorage = itemStorageService.getItemStorage(id);
                rejestrItemService.deleteRejestrItem(itemStorage);
                itemStorageService.deleteItemStorage(id);
                modelAndView = new ModelAndView("redirect:/item/list");
                atts.addFlashAttribute("SuccessMessage","Pomyślnie usunięto przedmiot o nazwie: "+ itemStorage.getItem().getName());
                return modelAndView;
            }
            modelAndView = new ModelAndView("redirect:/item/list");
            atts.addFlashAttribute("ErrorMessage","Brak uprawienień! Nie można usunąć przedmiotu o nazwie: "+ itemStorage.getItem().getName());
            return modelAndView;
        }

        modelAndView = new ModelAndView("error");
        return modelAndView;
        /*
        modelAndView = new ModelAndView("redirect:/item/list");
        atts.addFlashAttribute("ErrorMessage","Usuwanie jest zablokowane! ");


        return modelAndView;

         */
    }
    @GetMapping("/item/info/{id}")
    public ModelAndView showInfo(@PathVariable long id, Authentication authentication, RedirectAttributes atts) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();

            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {
                ItemStorage itemStorage = itemStorageService.getItemStorage(id);

                if (itemStorage == null) {
                    modelAndView = new ModelAndView("redirect:/item/list");
                    atts.addFlashAttribute("ErrorMessage","Nie ma takiego przedmiotu w bazie danych!");
                    return modelAndView;
                }
                List<RejestrItem> rejestrItemList = rejestrItemService.getAllByItemStorage(itemStorage);
                modelAndView = new ModelAndView("user/showItem","mode",mode.name());
                modelAndView.addObject("itemStorage",itemStorage);
                modelAndView.addObject("rejestrItemList",rejestrItemList);
                modelAndView.addObject("qDto",new QuantityDto());
                return modelAndView;
            }
            modelAndView = new ModelAndView("redirect:/item/list");
            atts.addFlashAttribute("ErrorMessage","Nie masz uprawnień, aby zobaczyć ten przedmiot!");
            return modelAndView;
        }
        modelAndView = new ModelAndView("error");
        return modelAndView;

    }

    @PostMapping("/item/add/save")
    public ModelAndView postAdd(@ModelAttribute @Valid ItemDto itemDto, BindingResult bindingResult, Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {
                if (bindingResult.hasErrors()) {
                    modelAndView = new ModelAndView("user/dodajNowyItem","mode",mode.name());
                    modelAndView.addObject("itemDto",itemDto);
                    modelAndView.addObject("ErrorMessage","Wypełnij wymagane pola!");
                    return modelAndView;
                }
                Item item = null;
                User user = userService.findByUsername(sourcePrincipal.getUsername());
                try{
                    item = itemService.createItem(user,itemDto);
                }catch (DataIntegrityViolationException e) {
                    modelAndView = new ModelAndView("user/dodajNowyItem","mode",mode.name());
                    modelAndView.addObject("itemDto",itemDto);
                    modelAndView.addObject("ErrorMessage","Nazwa jest już w użyciu! Sprawdź bazę wszystkich upominków.");
                    return modelAndView;
                }

                ItemStorage itemStorage = itemStorageService.addItem(item,itemDto.getQuantity());
                //itemStorageService.updateZetony();
                rejestrItemService.addCreateLog(itemStorage,user);
                if(itemStorage == null || item == null || user == null) {
                    modelAndView = new ModelAndView("user/dodajNowyItem","mode",mode.name());
                    modelAndView.addObject("itemDto",itemDto);
                    modelAndView.addObject("ErrorMessage","Błąd zapisu danych!");
                    return modelAndView;
                }
                modelAndView = new ModelAndView("user/dodajNowyItem","mode",mode.name());
                modelAndView.addObject("SuccessMessage","Pomyślnie dodano nowy przedmiot");
                modelAndView.addObject("itemDto",new ItemDto());
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("error");
        return modelAndView;

    }
    @PostMapping("/item/give/save")
    public ModelAndView postGive(@ModelAttribute @Valid WydajItemDto wydajItemDto, BindingResult bindingResult, Authentication authentication,RedirectAttributes atts) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC || mode == AccountType.DIAGNOSTYKA) {
                if (bindingResult.hasErrors()) {

                    List<ItemStorage> itemStorageList = itemStorageService.getAllActualItemStorage();

                    modelAndView = new ModelAndView("user/wydajItem","mode",mode.name());

                    modelAndView.addObject("wydajItemDto",wydajItemDto);
                    modelAndView.addObject("itemStorageList",itemStorageList);
                    modelAndView.addObject("ErrorMessage","Błędnie wprowadzone dane!");
                    return modelAndView;
                }
                User user = userService.findByUsername(sourcePrincipal.getUsername());
                ItemStorage itemStorage = null;
                if(wydajItemDto.isInternal() == true && !wydajItemDto.getNumerBadania().isEmpty() )
                {
                    List<ItemStorage> itemStorageList = itemStorageService.getAllActualItemStorage();

                    modelAndView = new ModelAndView("user/wydajItem","mode",mode.name());

                    modelAndView.addObject("wydajItemDto",wydajItemDto);
                    modelAndView.addObject("itemStorageList",itemStorageList);
                    modelAndView.addObject("ErrorMessage","Opcja 'Wewnętrzne' nie może być kojarzona z numerem badania! Pole to musi być puste przy transakcjach wewnętrznych!");
                    return modelAndView;
                }
                try {
                    itemStorage = itemStorageService.wydajItem(wydajItemDto,user);
                    //itemStorageService.updateZetony();
                } catch (QuantityTooSmallException e) {
                    modelAndView = new ModelAndView("redirect:/item/give");
                    atts.addFlashAttribute("ErrorMessage",e.getMessage());
                    return modelAndView;
                }
                if(wydajItemDto.isInternal())
                    rejestrItemService.addGiveInternLog(itemStorage,user,wydajItemDto.getQuantity(), wydajItemDto.getNumerBadania());
                else
                    rejestrItemService.addGiveLog(itemStorage,user,wydajItemDto.getQuantity(), wydajItemDto.getNumerBadania());
                modelAndView = new ModelAndView("redirect:/item/give");
                atts.addFlashAttribute("SuccessMessage","Pomyślnie wydano upominek! "+ itemStorage.getItem().getName()+" sztuk: "+wydajItemDto.getQuantity());
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("error");
        return modelAndView;

    }
    @PostMapping("/item/edit/save/{id}")
    public ModelAndView postEdit(@PathVariable long id, @ModelAttribute @Valid ItemDto itemDto, BindingResult bindingResult, Authentication authentication,RedirectAttributes atts) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {
                ItemStorage itemStorage = itemStorageService.getItemStorage(id);
                if (bindingResult.hasErrors()) {
                    modelAndView = new ModelAndView("user/editItem","mode",mode.name());
                    modelAndView.addObject("itemDto",itemDto);
                    modelAndView.addObject("itemFoto",itemStorage);
                    modelAndView.addObject("idTemp",id);
                    modelAndView.addObject("ErrorMessage","Błędnie wprowadzone dane!");
                    return modelAndView;
                }
                User user = userService.findByUsername(sourcePrincipal.getUsername());
                Item item = null;
                try{
                    item = itemService.updateItem(itemStorage.getItem().getId(),itemDto);
                } catch (DataIntegrityViolationException e) {
                    modelAndView = new ModelAndView("user/editItem","mode",mode.name());
                    modelAndView.addObject("itemDto",itemDto);
                    modelAndView.addObject("itemFoto",itemStorage);
                    modelAndView.addObject("idTemp",id);
                    modelAndView.addObject("ErrorMessage","Nazwa lub tag jest już w użyciu! Sprawdź bazę wszystkich upominków.");
                    return modelAndView;
                }
                ItemStorage newItemStorage = itemStorageService.updateItemStorage(itemStorage);
                rejestrItemService.addEditLog(itemStorage,user);
                if(newItemStorage == null || item == null || user == null) {
                    modelAndView = new ModelAndView("user/editItem","mode",mode.name());
                    modelAndView.addObject("itemDto",itemDto);
                    modelAndView.addObject("itemFoto",itemStorage);
                    modelAndView.addObject("idTemp",id);
                    modelAndView.addObject("ErrorMessage","Błąd 999!");
                    return modelAndView;
                }
                modelAndView = new ModelAndView("redirect:/item/list");
                atts.addFlashAttribute("SuccessMessage","Pomyślnie edytowano przedmiot: "+ itemDto.getName());
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("error");
        return modelAndView;

    }
    @PostMapping("/item/addQuantity/save/{id}")
    public ModelAndView postAddQuantity(@PathVariable long id,@ModelAttribute @Valid QuantityDto quantityDto,BindingResult bindingResult, Authentication authentication,RedirectAttributes atts) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {

                if (bindingResult.hasErrors()) {
                    modelAndView = new ModelAndView("redirect:/item/info/"+id);
                    atts.addFlashAttribute("ErrorMessage","Nie wprowadzono liczby!");
                    return modelAndView;
                }
                User user = userService.findByUsername(sourcePrincipal.getUsername());
                ItemStorage itemStorage = itemStorageService.updateQuantity(id,quantityDto);
               // itemStorageService.updateZetony();
                rejestrItemService.addAddQuantityLog(itemStorage,user,quantityDto.getQuantity());
                if(itemStorage == null || user == null) {
                    modelAndView = new ModelAndView("redirect:/item/info/"+ id);
                    atts.addFlashAttribute("ErrorMessage","Nie udało się ustawić nowej ilości!");
                    return modelAndView;
                }
                modelAndView = new ModelAndView("redirect:/item/info/"+ id);
                atts.addFlashAttribute("SuccessMessage","Pomyślnie ustawiono nową ilość!");
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("error");
        return modelAndView;

    }


}
