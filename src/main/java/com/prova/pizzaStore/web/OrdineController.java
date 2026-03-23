package com.prova.pizzaStore.web;

import com.prova.pizzaStore.dto.ClienteDTO;
import com.prova.pizzaStore.dto.OrdineDTO;
import com.prova.pizzaStore.model.Cliente;
import com.prova.pizzaStore.model.Ordine;
import com.prova.pizzaStore.repository.ordine.OrdineRepository;
import com.prova.pizzaStore.service.OrdineService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/ordine")
public class OrdineController {

    @Autowired
    OrdineService ordineService;

//    @GetMapping
//    public ModelAndView listAllOrdini(){
//        ModelAndView mv = new ModelAndView();
//        List<Ordine> ordine = ordineService.listAllElements();
//        mv.addObject("ordine_list_attribute", OrdineDTO.createOrdineDTOListFromModelList(ordine, true));
//        mv.setViewName("ordine/list");
//        return mv;
//    }
//
//    @PostMapping("/list")
//    public ModelAndView listOridni(OrdineDTO example) {
//        ModelAndView mv = new ModelAndView();
//        List<Ordine> ordine = ordineService.findByExample(example.buildOrdineFromDTO());
//        mv.addObject("cliente_list_attribute", OrdineDTO.createOrdineDTOListFromModelList(ordine, true));
//        mv.setViewName("cliente/list");
//        return mv;
//    }

}
