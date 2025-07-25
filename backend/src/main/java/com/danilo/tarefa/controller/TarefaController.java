package com.danilo.tarefa.controller;

import com.danilo.tarefa.model.*;
import com.danilo.tarefa.model.enums.Prioridade;
import com.danilo.tarefa.model.enums.Situacao;
import com.danilo.tarefa.repository.TarefaRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Danilo Micaías on 21/07/2025
 */
@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @PostMapping()
    @Operation(summary = "Cria uma nova tarefa.")
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    @GetMapping
    @Operation(summary = "Busca uma tarefa.")
    public List<Tarefa> listarTarefas(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Situacao situacao,
            @RequestParam(required = false) Prioridade prioridade,
            @RequestParam(required = false) String responsavel,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String deadlineAntes // yyyy-MM-dd
    ) {
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

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma tarefa existente.")
    public Tarefa atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa novaTarefa) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setTitulo(novaTarefa.getTitulo());
            tarefa.setDescricao(novaTarefa.getDescricao());
            tarefa.setResponsavel(novaTarefa.getResponsavel());
            tarefa.setPrioridade(novaTarefa.getPrioridade());
            tarefa.setDeadline(novaTarefa.getDeadline());
            tarefa.setSituacao(novaTarefa.getSituacao());
            return tarefaRepository.save(tarefa);
        }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    @Operation(summary = "Remove uma tarefa.")
    @DeleteMapping("/{id}")
    public void removerTarefa(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
    }

    @Operation(summary = "Troca o status da tarefa.")
    @PutMapping("/{id}/concluir")
    public Tarefa concluirTarefa(@PathVariable Long id) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setSituacao(Situacao.CONCLUIDA);
            return tarefaRepository.save(tarefa);
        }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }
}
