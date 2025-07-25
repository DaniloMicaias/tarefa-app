package com.danilo.tarefa.controller;


import com.danilo.tarefa.model.*;
import com.danilo.tarefa.model.dto.TarefaDTO;
import com.danilo.tarefa.model.enums.Prioridade;
import com.danilo.tarefa.model.enums.Situacao;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TarefaControllerTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should change the status of a task")
    @Transactional
    void concluirTarefa() throws Exception {
        String encryptedPassword = new BCryptPasswordEncoder().encode("123456");
        var user = new User("danilomicaias", encryptedPassword);
        this.entityManager.persist(user);

        String loginJson = """
            {
              "login": "danilomicaias",
              "password": "123456"
            }
        """;

        String response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String token = mapper.readTree(response).get("token").asText();

        TarefaDTO data = new TarefaDTO(
                "Titulo Teste", "Descricao teste", "João",
                Prioridade.BAIXA, LocalDate.now().plusDays(2), Situacao.EM_ANDAMENTO);
        Tarefa tarefa = this.createTarefa(data);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/tarefas/{id}/concluir", tarefa.getId())
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Tarefa tarefaAtualizada = entityManager.find(Tarefa.class, tarefa.getId());
        Assertions.assertEquals(Situacao.CONCLUIDA, tarefaAtualizada.getSituacao());
    }


    public Tarefa createTarefa(TarefaDTO data){
        Tarefa newTarefa = new Tarefa(data);
        this.entityManager.persist(newTarefa);
        return newTarefa;
    }
}