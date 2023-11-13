package com.apilop.projetoapilop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apilop.projetoapilop.model.Usuario;
import java.util.List;


public interface Repository extends JpaRepository<Usuario, Long> {
    
    List<Usuario> findByNameContainingIgnoreCase(String name);
    
}
