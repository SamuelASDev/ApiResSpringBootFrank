package com.primeiraapi.apirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.primeiraapi.apirest.model.AlunoModel;
import com.primeiraapi.apirest.repository.AlunoRepository;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public String inserir(AlunoModel aluno){
        try {
            alunoRepository.saveAndFlush(aluno);
            return "Salvo com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public Optional<AlunoModel> buscarPorId(Long id){
        return alunoRepository.findById(id);
    }

    public List<AlunoModel> buscarTodos() {
        return alunoRepository.findAll(); 
    }

    @Transactional 
    public boolean excluir(Long id) {
        // verifica se o aluno existe
        if (alunoRepository.existsById(id)) {
            try {
                alunoRepository.deleteById(id);
                return true; 
            } catch (Exception e) {
                System.err.println("Erro no serviço ao excluir aluno com ID " + id + ": " + e.getMessage());
                return false; 
            }
        }
        return false;
    }
}
