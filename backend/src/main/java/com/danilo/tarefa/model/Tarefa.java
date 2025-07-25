package com.danilo.tarefa.model;

import com.danilo.tarefa.model.dto.TarefaDTO;
import com.danilo.tarefa.model.enums.Prioridade;
import com.danilo.tarefa.model.enums.Situacao;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * @author Danilo Micaías on 21/07/2025
 */
@Entity
@Table(name = "tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private String responsavel;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    // Construtores
    public Tarefa() {}

    public Tarefa(String titulo, String descricao, String responsavel, Prioridade prioridade, LocalDate deadline) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.prioridade = prioridade;
        this.deadline = deadline;
        this.situacao = Situacao.EM_ANDAMENTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Tarefa(TarefaDTO data){
        this.titulo = data.titulo();
        this.descricao = data.descricao();
        this.prioridade = data.prioridade();
        this.responsavel = data.responsavel();
        this.situacao = data.situacao();
        this.deadline = data.deadline();
    }
}
