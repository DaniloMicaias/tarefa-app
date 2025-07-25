package com.danilo.tarefa.services;

import com.danilo.tarefa.exception.TarefaNaoEncontradaException;
import com.danilo.tarefa.model.Tarefa;
import com.danilo.tarefa.model.enums.Prioridade;
import com.danilo.tarefa.model.enums.Situacao;
import com.danilo.tarefa.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Danilo Micaías on 23/07/2025
 */
@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefas(Long id, Situacao situacao, Prioridade prioridade,
                                      String responsavel, String titulo, String deadlineAntes) {

        List<Tarefa> tarefas = tarefaRepository.findAll();

        if (id != null)
            tarefas.removeIf(t -> !t.getId().equals(id));

        if (situacao != null)
            tarefas.removeIf(t -> !t.getSituacao().equals(situacao));

        if (prioridade != null)
            tarefas.removeIf(t -> !t.getPrioridade().equals(prioridade));

        if (responsavel != null)
            tarefas.removeIf(t -> t.getResponsavel() == null ||
                    !t.getResponsavel().toLowerCase().contains(responsavel.toLowerCase()));

        if (titulo != null)
            tarefas.removeIf(t -> t.getTitulo() == null ||
                    !t.getTitulo().toLowerCase().contains(titulo.toLowerCase()));

        if (deadlineAntes != null)
            tarefas.removeIf(t -> t.getDeadline() == null ||
                    !t.getDeadline().isBefore(LocalDate.parse(deadlineAntes)));

        return tarefas;
    }

    public Tarefa atualizarTarefa(Long id, Tarefa novaTarefa) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setTitulo(novaTarefa.getTitulo());
            tarefa.setDescricao(novaTarefa.getDescricao());
            tarefa.setResponsavel(novaTarefa.getResponsavel());
            tarefa.setPrioridade(novaTarefa.getPrioridade());
            tarefa.setDeadline(novaTarefa.getDeadline());
            tarefa.setSituacao(novaTarefa.getSituacao());
            return tarefaRepository.save(tarefa);
        }).orElseThrow(() -> new TarefaNaoEncontradaException("Tarefa não encontrada"));
    }

    public void removerTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

    public Tarefa concluirTarefa(Long id) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setSituacao(Situacao.CONCLUIDA);
            return tarefaRepository.save(tarefa);
        }).orElseThrow(() -> new TarefaNaoEncontradaException("Tarefa não encontrada"));
    }
}

