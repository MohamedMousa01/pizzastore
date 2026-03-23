package com.prova.pizzaStore.web;

import com.prova.pizzaStore.dto.ClienteDTO;
import com.prova.pizzaStore.model.Cliente;
import com.prova.pizzaStore.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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




}
