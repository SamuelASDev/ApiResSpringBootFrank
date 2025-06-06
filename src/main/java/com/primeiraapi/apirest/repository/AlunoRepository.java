package com.primeiraapi.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.primeiraapi.apirest.model.AlunoModel;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Long>{

    List<AlunoModel> findByNomeContainingIgnoreCase(String nome);
    List<AlunoModel> findByCursoContainingIgnoreCase(String curso);
    List<AlunoModel> findByTurnoContainingIgnoreCase(String turno);
}
