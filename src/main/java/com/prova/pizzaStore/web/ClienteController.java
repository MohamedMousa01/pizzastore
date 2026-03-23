package com.prova.pizzaStore.web;

import com.prova.pizzaStore.dto.ClienteDTO;
import com.prova.pizzaStore.model.Cliente;
import com.prova.pizzaStore.service.ClienteService;
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
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ModelAndView listAllClienti(){
        ModelAndView mv = new ModelAndView();
        List<Cliente> clienti = clienteService.listAllElements();
        mv.addObject("cliente_list_attribute", ClienteDTO.createClienteDTOListFromModelList(clienti));
        mv.setViewName("cliente/list");
        return mv;
    }

    @PostMapping("/list")
    public ModelAndView listClienti(ClienteDTO example) {
        ModelAndView mv = new ModelAndView();
        List<Cliente> clienti = clienteService.findByExample(example.buildClienteModel());
        mv.addObject("cliente_list_attribute", ClienteDTO.createClienteDTOListFromModelList(clienti));
        mv.setViewName("cliente/list");
        return mv;
    }

    @GetMapping("/search")
    public String search() {
        return "cliente/search";
    }

    @GetMapping("/insert")
    public String createCliente(Model model) {
        model.addAttribute("insert_cliente_attr", new ClienteDTO());
        return "cliente/insert";
    }

    @PostMapping("/save")
    public String saveCliente(@Valid @ModelAttribute("insert_cliente_attr") ClienteDTO clienteDTO, BindingResult result,
                              RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "cliente/insert";
        }
        clienteDTO.setAttivo(true);
        clienteService.inserisciNuovo(clienteDTO.buildClienteModel());
        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/cliente";
    }

    @GetMapping("/show/{idCliente}")
    public String showCliente(@PathVariable(required = true) Long idCliente, Model model) {
        model.addAttribute("show_cliente_attr",
                ClienteDTO.buildClienteDTOFromModel(clienteService.caricaSingoloElementoEager(idCliente)));
        Cliente cliente = clienteService.caricaSingoloElementoEager(idCliente);
        return "cliente/show";
    }


    @GetMapping("/delete/{id}")
    public String rimuovi(@PathVariable(required = true) Long id, RedirectAttributes redirectAttributes){
        try {
            clienteService.disattivaCliente(id);
            redirectAttributes.addFlashAttribute("successMessage", "Cliente disattivato con successo.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

        }
        return "redirect:/cliente";
    }


    @GetMapping("/edit/{idCliente}")
    public String editCliente(@PathVariable(required = true) Long idCliente, Model model) {
        model.addAttribute("edit_cliente_attr",
                ClienteDTO.buildClienteDTOFromModel(clienteService.caricaSingoloCliente(idCliente)));
        return "cliente/edit";
    }

    @PostMapping("/update")
    public String updateCliente(@Valid @ModelAttribute("edit_cliente_attr") ClienteDTO clienteDTO, BindingResult result,
                                RedirectAttributes redirectAttrs, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "cliente/edit";
        }
        clienteService.aggiorna(clienteDTO.buildClienteModel());

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/cliente";
    }







}
