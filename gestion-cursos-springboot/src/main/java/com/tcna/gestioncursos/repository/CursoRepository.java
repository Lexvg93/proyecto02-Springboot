package com.tcna.gestioncursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcna.gestioncursos.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Integer> {
    
}
