package com.danilo.tarefa.repository;

import com.danilo.tarefa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Danilo Micaías on 22/07/2025
 */
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByLogin(String login);
}
