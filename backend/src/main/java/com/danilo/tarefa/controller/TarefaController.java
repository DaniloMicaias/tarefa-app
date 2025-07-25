package com.danilo.tarefa.controller;

import com.danilo.tarefa.model.*;
import com.danilo.tarefa.model.enums.Prioridade;
import com.danilo.tarefa.model.enums.Situacao;
import com.danilo.tarefa.services.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Danilo Micaías on 21/07/2025
 */
@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping()
    @Operation(summary = "Cria uma nova tarefa.")
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.criarTarefa(tarefa);
    }

    @GetMapping
    @Operation(summary = "Busca uma tarefa.")
    public List<Tarefa> listarTarefas(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Situacao situacao,
            @RequestParam(required = false) Prioridade prioridade,
            @RequestParam(required = false) String responsavel,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String deadlineAntes
    ) {
        return tarefaService.listarTarefas(id, situacao, prioridade, responsavel, titulo, deadlineAntes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma tarefa existente.")
    public Tarefa atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa novaTarefa) {
        return tarefaService.atualizarTarefa(id, novaTarefa);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma tarefa.")
    public void removerTarefa(@PathVariable Long id) {
        tarefaService.removerTarefa(id);
    }

    @PutMapping("/{id}/concluir")
    @Operation(summary = "Troca o status da tarefa.")
    public Tarefa concluirTarefa(@PathVariable Long id) {
        return tarefaService.concluirTarefa(id);
    }
}

