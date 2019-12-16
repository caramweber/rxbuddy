package org.launchcode.controllers;

import org.launchcode.models.Rx;
import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.RxDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable int id) {
        Rx rx = rxDao.findOne(id);
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("edit", rx);
        return "rx/edit";

    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(
            @ModelAttribute  @Valid Rx newRx,
            Errors errors,
            @RequestParam int categoryId,
            @RequestParam(value="editId") int editId,
            Model model) {
        Rx rx = rxDao.findOne(editId);
        Category cat = categoryDao.findOne(categoryId);
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Prescription");
            model.addAttribute("categories", categoryDao.findAll());
            model.addAttribute("edit", rx);
            return "rx/edit";
        }
        rx.setName(newRx.getName());
        rx.setRxnum(newRx.getRxnum());
        rx.setCategory(cat);
        rx.setDescription(newRx.getDescription());
        rx.setDocname(newRx.getDocname());
        rx.setDocweb(newRx.getDocweb());
        rx.setDocnum(newRx.getDocnum());
        rx.setPharmname(newRx.getPharmname());
        rx.setPharmweb(newRx.getPharmweb());
        rx.setPharmnum(newRx.getPharmnum());
        rxDao.save(rx);
        return "redirect:";

    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(@RequestParam(value = "search") String q, Model model) {
        List<Rx> searchResults = new ArrayList<>();
        /*try {
            searchResults = searchservice.fuzzySearch(q);

        } catch (Exception ex) {
            System.out.println("No Results");
        }*/

        for (Rx rx : rxDao.findAll()) {
            if(rx.getName().toLowerCase().contains(q.toLowerCase()) ||
                rx.getDocname().toLowerCase().contains(q.toLowerCase()) ||
                rx.getPharmname().toLowerCase().contains(q.toLowerCase())){
                searchResults.add(rx);
            }
        }

        model.addAttribute("rxes", searchResults);
        return "rx/search";

    }



}