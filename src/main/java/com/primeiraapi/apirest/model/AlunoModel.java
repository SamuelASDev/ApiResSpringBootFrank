package com.primeiraapi.apirest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern; 

@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class AlunoModel extends PessoaModel {
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

    private String turno;

    @Pattern(regexp = "\\d{8}", message = "CEP inválido. Deve ter 8 dígitos.") // Validação básica do CEP
    private String cep;

    public AlunoModel() {
    }

    public AlunoModel(String campus, String polo, String email_institucional,
    String coordenacao, String curso, String situacao, String periodo_entrada,
    String turno, String cep) { 
        this.campus = campus;
        this.polo = polo;
        this.email_institucional = email_institucional;
        this.coordenacao = coordenacao;
        this.curso = curso;
        this.situacao = situacao;
        this.periodo_entrada = periodo_entrada;
        this.turno = turno;
        this.cep = cep;
    }

    public AlunoModel(String nome, int idade, String sexo,
    String campus, String polo, String email_institucional,
    String coordenacao, String curso, String situacao, String periodo_entrada,
    String turno, String cep) { 
        super(nome, idade, sexo);
        this.campus = campus;
        this.polo = polo;
        this.email_institucional = email_institucional;
        this.coordenacao = coordenacao;
        this.curso = curso;
        this.situacao = situacao;
        this.periodo_entrada = periodo_entrada;
        this.turno = turno;
        this.cep = cep;
    }


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

    public String getTurno() {  
        return turno;
    }

    public void setTurno(String turno) { 
        this.turno = turno;
    }

    public String getCep() {  
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}