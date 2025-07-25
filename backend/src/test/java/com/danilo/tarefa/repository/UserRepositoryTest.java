package com.danilo.tarefa.repository;

import com.danilo.tarefa.model.dto.AuthenticationDTO;
import com.danilo.tarefa.model.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Danilo Micaías on 22/07/2025
 */
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Should get user successfully from DB.")
    void findByLoginCase1() {
        AuthenticationDTO data = new AuthenticationDTO("logintest", "123456");
        this.createUser(data);

        Optional<User> foundedUser = this.userRepository.findByLogin(data.login());

        assertThat(foundedUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get user from DB when not exists.")
    void findByLoginCase2() {
        String falseLogin = "aqswdefrgt";

        Optional<User> foundedUser = this.userRepository.findByLogin(falseLogin);

        assertThat(foundedUser.isEmpty()).isTrue();
    }

    public User createUser(AuthenticationDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }
}