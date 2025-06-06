package com.primeiraapi.apirest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.primeiraapi.apirest.model.AlunoModel;
import com.primeiraapi.apirest.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<String> inserir(@Valid @RequestBody AlunoModel aluno){
        String retorno = alunoService.inserir(aluno);
        try {
            return new ResponseEntity<>(retorno, HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<AlunoModel>> buscarTodos() {
        List<AlunoModel> alunos = alunoService.buscarTodos();
        if (alunos.isEmpty()) {
            //retorna 204 No Content se estiver vazia
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // retorna 200 OK se tiver alunos na lista
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean excluido = alunoService.excluir(id);
        if (excluido) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // exclusão bem-sucedida
        } else {
            // aluno não encontrado ou qualquer outro erro
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoModel> atualizar(@PathVariable Long id, @Valid @RequestBody AlunoModel alunoDetalhes) {

        // caso tenha um id no corpo do request e ele for diferente do id informado na url vai dar erro
        if (alunoDetalhes.getIdPessoa() != null && !alunoDetalhes.getIdPessoa().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }

        try {
            AlunoModel alunoAtualizado = alunoService.atualizar(id, alunoDetalhes);
            if (alunoAtualizado != null) {
                return new ResponseEntity<>(alunoAtualizado, HttpStatus.OK); // 200 OK
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar aluno com ID " + id + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    @GetMapping("/nome/{nome}") 
    public ResponseEntity<List<AlunoModel>> buscarPorNome(@PathVariable String nome) {
        List<AlunoModel> alunosEncontrados = alunoService.buscarPorNome(nome);

        if (alunosEncontrados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } else {
            return new ResponseEntity<>(alunosEncontrados, HttpStatus.OK); 
        }
    }

    @GetMapping("/curso/{curso}") 
    public ResponseEntity<List<AlunoModel>> buscarPorCurso(@PathVariable String curso) {
        List<AlunoModel> alunosEncontrados = alunoService.buscarPorCurso(curso);

        if (alunosEncontrados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } else {
            return new ResponseEntity<>(alunosEncontrados, HttpStatus.OK); 
        }
    }

    
    @GetMapping("/turno/{turno}") 
    public ResponseEntity<List<AlunoModel>> buscarPorTurno(@PathVariable String turno) {
        List<AlunoModel> alunosEncontrados = alunoService.buscarPorTurno(turno);

        if (alunosEncontrados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } else {
            return new ResponseEntity<>(alunosEncontrados, HttpStatus.OK); 
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoModel> buscarPorId(@PathVariable Long id){
        Optional<AlunoModel> aluno = alunoService.buscarPorId(id);
        if (aluno.isPresent()) {
            return new ResponseEntity<>(aluno.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AlunoModel(), HttpStatus.NOT_FOUND);
    }
}
