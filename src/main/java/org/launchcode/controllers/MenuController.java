package org.launchcode.controllers;

import org.launchcode.models.Rx;
import org.launchcode.models.Menu;
import org.launchcode.models.data.RxDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private RxDao rxDao;

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("title", "Menus");
        model.addAttribute("items", menuDao.findAll());
        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMenu(Model model){
        model.addAttribute("title", "Create Menu");
        model.addAttribute(new Menu());
        model.addAttribute("items", menuDao.findAll());
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMenu(Model model, @ModelAttribute @Valid Menu menu, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Menu");
            model.addAttribute(new Error());
            model.addAttribute("menus", menuDao.findAll());
            return "menu/add";
        }
        menuDao.save(menu);
        return "redirect:view/" + menu.getId();

    }

    @RequestMapping(value = "view/{idMenu}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int idMenu){
        Menu choiceMenu = menuDao.findOne(idMenu);

        model.addAttribute("title", "Menu: " + choiceMenu.getName());

        model.addAttribute("items", choiceMenu.getRxes());

        Iterable<Menu> test = menuDao.findAll();
        model.addAttribute("num", test);
        return "menu/view";
    }

    @RequestMapping(value = "add-item/{idMenu}", method = RequestMethod.GET)
    public  String addItem(Model model, @PathVariable int idMenu){
        Menu menu = menuDao.findOne(idMenu);
        Iterable<Rx> rxes = rxDao.findAll();

        AddMenuItemForm addMenuItemForm = new AddMenuItemForm(rxes, menu);

        model.addAttribute("title", "Add item to menu: " + menuDao.findOne(idMenu).getName());
        model.addAttribute("rxes", rxDao.findAll());
        return "menu/add-item";
    }

    @RequestMapping(value = "add-item/{idMenu}", method = RequestMethod.POST)
    public  String processAddItem(Model model, @PathVariable int idMenu,
                                  @ModelAttribute AddMenuItemForm addMenuItemForm, Errors errors,
                                  @RequestParam int rxId){

        if (errors.hasErrors()) {
            model.addAttribute("");
            return "menu/add-item";
        }

        Menu choiceMenu = menuDao.findOne(idMenu);
        Rx choiceRx = rxDao.findOne(rxId);
        choiceMenu.addItem(choiceRx);
        menuDao.save(choiceMenu);

        return "redirect:/menu/view/"+idMenu;
    }
}