package com.primeiraapi.apirest.controller;

import com.primeiraapi.apirest.model.AlunoModel;
import com.primeiraapi.apirest.service.CarregaDadosService; // Importe o novo serviço
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils;
import java.util.List;

@RestController
@RequestMapping("/carregar-dados")
public class CarregaDadosAPIController {

    @Autowired
    private CarregaDadosService carregaDadosService; // Injetando o serviço refatorado

    @PostMapping
    public ResponseEntity<List<AlunoModel>> uploadAndSaveData(@RequestParam(required = false) String filePath) {
        // Verifica se o filePath fornecido na requisição está vazio ou nulo
        if (!StringUtils.hasText(filePath)) {
            // Se estiver vazio, usa o caminho padrao.
            filePath = "src\\main\\resources\\alunostads.csv";
            System.out.println("Nenhum caminho de arquivo fornecido. Usando o arquivo padrão: " + filePath);
        } else {
            // Caso contrário, usa o caminho fornecido
            System.out.println("Usando o caminho de arquivo fornecido: " + filePath);
        }
        
        try {
            // Carrega e pre-valida os dados do arquivo
            List<AlunoModel> alunosCarregados = carregaDadosService.carregarEValidarAlunosDoArquivo(filePath);

            // Insere os alunos no banco de dados
            List<AlunoModel> alunosSalvos = carregaDadosService.inserirNoBanco(alunosCarregados);

            return ResponseEntity.status(HttpStatus.CREATED).body(alunosSalvos);
        } catch (IllegalArgumentException e) {
            // Erro no formato do arquivo ou campos ausentes
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            // Outros erros
            System.err.println("Erro ao processar o upload: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}