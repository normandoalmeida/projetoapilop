package com.apilop.projetoapilop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.apilop.projetoapilop.model.Usuario;
import com.apilop.projetoapilop.service.UsuarioService;

import jakarta.validation.Valid;


@Controller
public class UsuarioController {
    
    @Autowired
    UsuarioService service;

    //Pagina inicial

    @GetMapping("/")
    public String lista(Model model){
        List<Usuario> listaUsuario = service.listar();
        model.addAttribute("userList", listaUsuario);
        return "/lista";
    }


    //Pagina de cadastro
    @GetMapping("/home")
    public String newUsuario(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute("cadastroUsuario", usuario);
        return "/cadastro";
    }


    //Método save
    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute("cadastroUsuario") @Valid Usuario usuario, BindingResult validacao){ 
        if(validacao.hasErrors()){return "/cadastro";}
        service.criarUsuario(usuario);
        return "redirect:/home";
    }

    //Método delete
    @GetMapping("/delet/{id}")
    public String removeUser(@PathVariable long id){
        service.deletUser(id);
        return "redirect:/";
    }

    //Método edit
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable long id, Model model){
        Usuario usuario = service.buscar(id);
        model.addAttribute("user", usuario);
        return "/editar";
    }


    @PostMapping("/edit/{id}")
    public String editarUsuario(@ModelAttribute("user") @Valid Usuario usuario, BindingResult validacao){ 
        if(validacao.hasErrors()){return "/editar";}
        service.editarUsuario(usuario);
        return "redirect:/";
    }

    //Método search
    @PostMapping("/search")
    public String search(Model model, @Param("name") String name){
        List<Usuario> usuarios = service.search(name);
        model.addAttribute("userList", usuarios);

        return "lista";
    }

}
