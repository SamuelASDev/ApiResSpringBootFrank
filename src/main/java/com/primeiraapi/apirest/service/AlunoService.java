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

    @Transactional 
    public AlunoModel atualizar(Long id, AlunoModel alunoDetalhes) {
        // Busca o aluno
        Optional<AlunoModel> alunoExistente = alunoRepository.findById(id);

        if (alunoExistente.isPresent()) {
            AlunoModel alunoParaAtualizar = alunoExistente.get();

            // Atualiza os campos do aluno existente com os novos detalhes
            // verifica se os campos dos detalhes não são nulos antes de atualizar
            
            //PessoaModel (herdado)
            if (alunoDetalhes.getNome() != null) {
                alunoParaAtualizar.setNome(alunoDetalhes.getNome());
            }
            if (alunoDetalhes.getIdade() != 0) { 
                alunoParaAtualizar.setIdade(alunoDetalhes.getIdade());
            }
            if (alunoDetalhes.getSexo() != null) {
                alunoParaAtualizar.setSexo(alunoDetalhes.getSexo());
            }

            //AlunoModel
            if (alunoDetalhes.getCampus() != null) {
                alunoParaAtualizar.setCampus(alunoDetalhes.getCampus());
            }
            if (alunoDetalhes.getPolo() != null) {
                alunoParaAtualizar.setPolo(alunoDetalhes.getPolo());
            }
            if (alunoDetalhes.getEmail_institucional() != null) {
                alunoParaAtualizar.setEmail_institucional(alunoDetalhes.getEmail_institucional());
            }
            if (alunoDetalhes.getCoordenacao() != null) {
                alunoParaAtualizar.setCoordenacao(alunoDetalhes.getCoordenacao());
            }
            if (alunoDetalhes.getCurso() != null) {
                alunoParaAtualizar.setCurso(alunoDetalhes.getCurso());
            }
            if (alunoDetalhes.getSituacao() != null) {
                alunoParaAtualizar.setSituacao(alunoDetalhes.getSituacao());
            }
            if (alunoDetalhes.getPeriodo_entrada() != null) {
                alunoParaAtualizar.setPeriodo_entrada(alunoDetalhes.getPeriodo_entrada());
            }
            if (alunoDetalhes.getTurno() != null) { 
                alunoParaAtualizar.setTurno(alunoDetalhes.getTurno());
            }
            if (alunoDetalhes.getCep() != null) { 
                alunoParaAtualizar.setCep(alunoDetalhes.getCep());
            }

            // atualiza o aluno no banco de dados
            return alunoRepository.saveAndFlush(alunoParaAtualizar);
        } else {
            return null; // aluno não encontrado
        }
    }


    public List<AlunoModel> buscarPorNome(String nome) {
        return alunoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Optional<AlunoModel> buscarPorId(Long id){
        return alunoRepository.findById(id);
    }

    public List<AlunoModel> buscarPorCurso(String curso) {
        return alunoRepository.findByCursoContainingIgnoreCase(curso);
    }

    public List<AlunoModel> buscarPorTurno(String turno) {
        return alunoRepository.findByTurnoContainingIgnoreCase(turno);
    }
}
