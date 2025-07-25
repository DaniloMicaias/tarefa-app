package com.danilo.tarefa.model.dto;


import com.danilo.tarefa.model.enums.Prioridade;
import com.danilo.tarefa.model.enums.Situacao;

import java.time.LocalDate;

public record TarefaDTO(String titulo, String descricao, String responsavel,
                        Prioridade prioridade, LocalDate deadline, Situacao situacao) {
}
