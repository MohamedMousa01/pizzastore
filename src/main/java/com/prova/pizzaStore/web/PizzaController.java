package com.prova.pizzaStore.web;

import com.prova.pizzaStore.dto.ClienteDTO;
import com.prova.pizzaStore.dto.OrdineDTO;
import com.prova.pizzaStore.dto.PizzaDTO;
import com.prova.pizzaStore.model.Cliente;
import com.prova.pizzaStore.model.Ordine;
import com.prova.pizzaStore.model.Pizza;
import com.prova.pizzaStore.service.PizzaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/pizza")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @GetMapping
    public ModelAndView listAllPizze(){
        ModelAndView mv = new ModelAndView();
        List<Pizza> pizze = pizzaService.listAllElements();
        mv.addObject("pizza_list_attribute", PizzaDTO.createPizzaDTOListFromModelList(pizze));
        mv.setViewName("pizza/list");
        return mv;
    }

    @GetMapping("/search")
    public String search() {
        return "pizza/search";
    }

    @PostMapping("/list")
    public ModelAndView listPizze(Pizza example) {
        ModelAndView mv = new ModelAndView();
        List<Pizza> pizze = pizzaService.findByExample(example);
        mv.addObject("pizza_list_attribute", PizzaDTO.createPizzaDTOListFromModelList(pizze));
        mv.setViewName("pizza/list");
        return mv;
    }

    @GetMapping("/insert")
    public String createPizza(Model model) {
        model.addAttribute("insert_pizza_attr", new PizzaDTO());
        return "pizza/insert";
    }

    @PostMapping("/save")
    public String savePizza(@Valid @ModelAttribute("insert_pizza_attr") PizzaDTO pizzaDTO, BindingResult result,
                              RedirectAttributes redirectAttrs) {
        pizzaDTO.setAttivo(Boolean.TRUE);
        if (result.hasErrors()) {
            System.out.println("aiutoooooo");
            return "pizza/insert";
        }
        pizzaDTO.setAttivo(true);
        pizzaService.inserisciNuovo(pizzaDTO.buildModelFromDTO());
        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/pizza";
    }

    @GetMapping("/edit/{idPizza}")
    public String editPizza(@PathVariable(required = true) Long idPizza, Model model) {
        model.addAttribute("edit_pizza_attr",
                PizzaDTO.buildPizzaDTOFromModel(pizzaService.caricaSingoloPizza(idPizza)) );
        return "pizza/edit";
    }

    @PostMapping("/update")
    public String updatePizza(@Valid @ModelAttribute("edit_pizza_attr") PizzaDTO pizzaDTO, BindingResult result,
                                RedirectAttributes redirectAttrs, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "pizza/edit";
        }
        pizzaService.aggiorna(pizzaDTO.buildModelFromDTO());

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/pizza";
    }


    @GetMapping("/delete/{id}")
    public String rimuovi(@PathVariable(required = true) Long id, RedirectAttributes redirectAttributes){
        try {
            pizzaService.disattivaPizza(id);
            redirectAttributes.addFlashAttribute("successMessage", "Pizza disattivata con successo.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

        }
        return "redirect:/pizza";
    }

    @GetMapping("/show/{idPizza}")
    public String showPizza(@PathVariable(required = true) Long idPizza, Model model) {

        model.addAttribute("show_pizza_attr",
              PizzaDTO.buildPizzaDTOFromModel(pizzaService.caricaSingoloPizza(idPizza)));
        return "pizza/show";
    }



}
