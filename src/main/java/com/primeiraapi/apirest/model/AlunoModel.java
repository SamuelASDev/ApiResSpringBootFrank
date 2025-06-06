package com.primeiraapi.apirest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank; 
import jakarta.validation.constraints.Email; 
import java.io.Serializable;

@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class AlunoModel extends PessoaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O campus não pode estar em branco.")
    private String campus;

    private String polo; 

    @NotBlank(message = "O e-mail institucional não pode estar em branco.")
    @Email(message = "Formato de e-mail inválido.") 
    private String email_institucional;

    private String coordenacao; 

    @NotBlank(message = "O curso não pode estar em branco.")
    private String curso;

    private String situacao; 

    @NotBlank(message = "O período de entrada não pode estar em branco.")
    private String periodo_entrada;

    public AlunoModel() {
    }

    public AlunoModel(String campus, String polo, String email_institucional,
                      String coordenacao, String curso, String situacao, String periodo_entrada) {
        this.campus = campus;
        this.polo = polo;
        this.email_institucional = email_institucional;
        this.coordenacao = coordenacao;
        this.curso = curso;
        this.situacao = situacao;
        this.periodo_entrada = periodo_entrada;
    }

    public AlunoModel(String nome, int idade, String sexo,
                      String campus, String polo, String email_institucional,
                      String coordenacao, String curso, String situacao, String periodo_entrada) {
        super(nome, idade, sexo);
        this.campus = campus;
        this.polo = polo;
        this.email_institucional = email_institucional;
        this.coordenacao = coordenacao;
        this.curso = curso;
        this.situacao = situacao;
        this.periodo_entrada = periodo_entrada;
    }

    // Getters e Setters
    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getPolo() {
        return polo;
    }

    public void setPolo(String polo) {
        this.polo = polo;
    }

    public String getEmail_institucional() {
        return email_institucional;
    }

    public void setEmail_institucional(String email_institucional) {
        this.email_institucional = email_institucional;
    }

    public String getCoordenacao() {
        return coordenacao;
    }

    public void setCoordenacao(String coordenacao) {
        this.coordenacao = coordenacao;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getPeriodo_entrada() {
        return periodo_entrada;
    }

    public void setPeriodo_entrada(String periodo_entrada) {
        this.periodo_entrada = periodo_entrada;
    }
}