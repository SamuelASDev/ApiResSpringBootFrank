package com.primeiraapi.apirest.service;

import com.primeiraapi.apirest.model.AlunoModel;
import com.primeiraapi.apirest.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Service
public class CarregaDadosService {

    // Instância do AlunoRepository injetado pelo Spring
    @Autowired
    private AlunoRepository alunoRepository;

    // Responsável por ler o arquivo CSV e converter cada linha em um objeto AlunoModel
    public List<AlunoModel> carregarEValidarAlunosDoArquivo(String filePath) throws Exception {
        List<AlunoModel> alunosCarregados = new ArrayList<>();
        try (Reader reader = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
             BufferedReader bf = new BufferedReader(reader)) {

            String headerLine = bf.readLine();
            if (headerLine == null) {
                throw new IllegalArgumentException("Arquivo vazio ou sem cabeçalho.");
            }
            // Pega os campos do cabeçalho pelas vírgulas
            String[] headers = headerLine.split(",");

            HashMap<String, Integer> camposEsperadosMap = new HashMap<>();
            // Mapeando os campos e armazenando o índice de cada coluna esperada no arquivo CSV
            camposEsperadosMap.put("campus", -1);
            camposEsperadosMap.put("polo", -1);
            camposEsperadosMap.put("coordenacao", -1);
            camposEsperadosMap.put("curso", -1);
            camposEsperadosMap.put("nome_estudante", -1);
            camposEsperadosMap.put("situacao", -1);
            camposEsperadosMap.put("idade", -1);
            camposEsperadosMap.put("sexo", -1);
            camposEsperadosMap.put("email_institucional", -1);
            camposEsperadosMap.put("periodo_entrada", -1);
            camposEsperadosMap.put("turno", -1);
            camposEsperadosMap.put("cep", -1);


            // Percorre o array para analisar os campos esperados e identificar campos não encontrados
            List<String> camposNaoEncontrados = new ArrayList<>();
            for (String campoEsperado : camposEsperadosMap.keySet()) {
                int index = Arrays.asList(headers).indexOf(campoEsperado);
                if (index == -1) {
                    camposNaoEncontrados.add(campoEsperado);
                } else {
                    camposEsperadosMap.put(campoEsperado, index);
                }
            }

            if (!camposNaoEncontrados.isEmpty()) {
                String camposFaltandoMsg = String.join(", ", camposNaoEncontrados);
                System.err.println("Atenção: Os campos abaixo não foram encontrados no arquivo: " + camposFaltandoMsg);
            }

            String linha;
            while ((linha = bf.readLine()) != null) {
                // Divide os dados da linha por ',' em um array
                String[] data = linha.split(",");
                AlunoModel aluno = new AlunoModel();


                // Mapeando os dados para o AlunoModel
                if (camposEsperadosMap.get("campus") != -1) aluno.setCampus(data[camposEsperadosMap.get("campus")]);
                if (camposEsperadosMap.get("polo") != -1) aluno.setPolo(data[camposEsperadosMap.get("polo")]);
                if (camposEsperadosMap.get("coordenacao") != -1) aluno.setCoordenacao(data[camposEsperadosMap.get("coordenacao")]);
                if (camposEsperadosMap.get("curso") != -1) aluno.setCurso(data[camposEsperadosMap.get("curso")]);
                if (camposEsperadosMap.get("nome_estudante") != -1) aluno.setNome(data[camposEsperadosMap.get("nome_estudante")]);
                if (camposEsperadosMap.get("situacao") != -1) aluno.setSituacao(data[camposEsperadosMap.get("situacao")]);
                if (camposEsperadosMap.get("idade") != -1) {
                    String idadeStr = data[camposEsperadosMap.get("idade")];
                    aluno.setIdade(!idadeStr.isBlank() && !idadeStr.isEmpty() ? Integer.parseInt(idadeStr) : 0);
                }
                if (camposEsperadosMap.get("sexo") != -1) aluno.setSexo(data[camposEsperadosMap.get("sexo")]);
                if (camposEsperadosMap.get("email_institucional") != -1) aluno.setEmail_institucional(data[camposEsperadosMap.get("email_institucional")]);
                if (camposEsperadosMap.get("periodo_entrada") != -1) aluno.setPeriodo_entrada(data[camposEsperadosMap.get("periodo_entrada")]);
                if (camposEsperadosMap.get("turno") != -1) aluno.setTurno(data[camposEsperadosMap.get("turno")]);
                if (camposEsperadosMap.get("cep") != -1) aluno.setCep(data[camposEsperadosMap.get("cep")]);


                alunosCarregados.add(aluno);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar o arquivo: " + filePath + " - " + e.getMessage());
            throw new RuntimeException("Falha ao carregar dados do arquivo: " + e.getMessage(), e);
        }
        return alunosCarregados;
    }

    @Transactional
    public List<AlunoModel> inserirNoBanco(List<AlunoModel> listaDeAlunos) {
        // Salvando todos os alunos
        return alunoRepository.saveAll(listaDeAlunos);
    }
}