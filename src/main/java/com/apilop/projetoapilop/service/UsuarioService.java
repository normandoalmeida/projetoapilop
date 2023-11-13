package com.apilop.projetoapilop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apilop.projetoapilop.model.Usuario;
import com.apilop.projetoapilop.repository.Repository;

@Service
public class UsuarioService{
    
    @Autowired
    private Repository repository;

    //Metodo save
    public Usuario criarUsuario(Usuario usuario){
        return repository.save(usuario);
    }

    //Metodo list
    public List<Usuario> listar(){
        return repository.findAll();
    }

    //Metodo search
    public Usuario buscar(Long id){
        Optional<Usuario> search = repository.findById(id);
         return search.get();
    }

    //Metodo delete
    public void deletUser(Long id){
        Usuario usuario = buscar(id);
        repository.delete(usuario);
    }

    //Metodo edit
    public Usuario editarUsuario(Usuario usuario){
        return repository.save(usuario);
    }

    //Metodo search
    public List<Usuario> search(String name){
        return repository.findByNameContainingIgnoreCase(name);
    }



    }
