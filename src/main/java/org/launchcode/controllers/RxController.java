package org.launchcode.controllers;

import org.launchcode.models.Rx;
import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.RxDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("rx")
public class RxController {

    @Autowired
    private RxDao rxDao;
    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("rxes", rxDao.findAll());
        model.addAttribute("title", "My Prescriptions");
        return "rx/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddRxForm(Model model) {
        model.addAttribute("title", "Add Prescription");
        model.addAttribute(new Rx());
        model.addAttribute("categories", categoryDao.findAll());
        return "rx/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddRxForm(
            @ModelAttribute  @Valid Rx newRx,
            Errors errors,
            @RequestParam int categoryId,
            Model model) {

        Category cat = categoryDao.findOne(categoryId);
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Prescription");
            model.addAttribute("categories", categoryDao.findAll());
            return "rx/add";
        }
        newRx.setCategory(cat);
        rxDao.save(newRx);
        return "redirect:";

    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveRxForm(Model model) {
        model.addAttribute("rxes", rxDao.findAll());
        model.addAttribute("title", "Remove Prescription");
        return "rx/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveRxForm(@RequestParam int[] rxIds) {

        for (int rxId : rxIds) {
            rxDao.delete(rxId);
        }

        return "redirect:";
    }



}