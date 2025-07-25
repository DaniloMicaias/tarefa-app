package com.danilo.tarefa.repository;

import com.danilo.tarefa.model.enums.Prioridade;
import com.danilo.tarefa.model.enums.Situacao;
import com.danilo.tarefa.model.Tarefa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Danilo Micaías on 22/07/2025
 */
@DataJpaTest
@ActiveProfiles("test")
class TarefaRepositoryTest {
    @Autowired
    TarefaRepository tarefaRepository;

    @Test
    void testSalvarTarefa() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Exemplo");
        tarefa.setDescricao("Descrição de exemplo");
        tarefa.setResponsavel("Maria");
        tarefa.setPrioridade(Prioridade.ALTA);
        tarefa.setSituacao(Situacao.EM_ANDAMENTO);
        tarefa.setDeadline(LocalDate.now().plusDays(7));

        Tarefa salva = tarefaRepository.save(tarefa);
        assertNotNull(salva.getId());
    }

}