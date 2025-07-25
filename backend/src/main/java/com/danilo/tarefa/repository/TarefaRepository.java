package com.danilo.tarefa.repository;

import com.danilo.tarefa.model.*;
import com.danilo.tarefa.model.enums.Prioridade;
import com.danilo.tarefa.model.enums.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Danilo Micaías on 21/07/2025
 */
@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findBySituacao(Situacao situacao);
    List<Tarefa> findByPrioridade(Prioridade prioridade);
    List<Tarefa> findByResponsavelContainingIgnoreCase(String responsavel);
    List<Tarefa> findByDeadlineBefore(LocalDate data);
    List<Tarefa> findByTituloContainingIgnoreCase(String titulo);
    List<Tarefa> findBySituacaoAndPrioridade(Situacao situacao, Prioridade prioridade);

}
