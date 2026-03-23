package com.prova.pizzaStore.web;

import com.prova.pizzaStore.dto.ClienteDTO;
import com.prova.pizzaStore.dto.OrdineDTO;
import com.prova.pizzaStore.dto.PizzaDTO;
import com.prova.pizzaStore.model.Ordine;
import com.prova.pizzaStore.model.Pizza;
import com.prova.pizzaStore.service.ClienteService;
import com.prova.pizzaStore.service.OrdineService;
import com.prova.pizzaStore.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/ordine")
public class OrdineController {

    @Autowired
    OrdineService ordineService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    PizzaService pizzaService;

    @GetMapping
    public ModelAndView listAllOrdini(){
        ModelAndView mv = new ModelAndView();
        List<Ordine> ordine = ordineService.listAllElements();
        mv.addObject("ordine_list_attribute", OrdineDTO.createOrdineDTOListFromModelList(ordine, true));
        mv.setViewName("ordine/list");
        return mv;
    }

    @GetMapping("/insert")
    public String createOrdine(Model model) {
        model.addAttribute("insert_ordine_attr", new OrdineDTO());
        List<ClienteDTO> listaClienti = ClienteDTO.createClienteDTOListFromModelList(clienteService.listAllElements());
        List<PizzaDTO> listaPizze = PizzaDTO.createPizzaDTOListFromModelList(pizzaService.listAllElements());

        model.addAttribute("clienti_list_attribute", listaClienti);
        model.addAttribute("pizze_list_attribute", listaPizze);

        return "ordine/insert";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("insert_ordine_attr") OrdineDTO ordineDTO, BindingResult result,
                       Model model, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("clienti_list_attribute", ClienteDTO.createClienteDTOListFromModelList(clienteService.listAllElements()));
            model.addAttribute("pizze_list_attribute", PizzaDTO.createPizzaDTOListFromModelList(pizzaService.listAllElements()));

            return "ordine/insert";
        }
        ordineDTO.setClosed(false);
        ordineDTO.setCodice("GOP" + LocalDateTime.now());
        Ordine ordine = ordineDTO.buildOrdineFromDTO();
        ordine.getPizze().clear();
        if (ordineDTO.getPizzeIds() != null && ordineDTO.getPizzeIds().length > 0){
            for (Long idPizza: ordineDTO.getPizzeIds()){
                Pizza pizzaVera = pizzaService.caricaSingoloPizza(idPizza);
                ordine.getPizze().add(pizzaVera);
            }
        }
        ordineService.inserisciNuovo(ordine);
        try {
            ordineService.calcolaPrezzoOrdine(ordine.getId());
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
        }
        redirectAttrs.addFlashAttribute("successMessage", "Ordine creato con successo");
        return "redirect:/ordine";
    }

    @PostMapping("/list")
    public ModelAndView listOridni(OrdineDTO example) {
        ModelAndView mv = new ModelAndView();
        List<Ordine> ordine = ordineService.findByExample(example.buildOrdineFromDTO());
        mv.addObject("ordine_list_attribute", OrdineDTO.createOrdineDTOListFromModelList(ordine, true));
        mv.setViewName("ordine/list");
        return mv;
    }

    @GetMapping("/search")
    public String search() {
        return "ordine/search";
    }



}
