package com.danilo.tarefa.exception;

/**
 * @author Danilo Micaías on 23/07/2025
 */
public class TarefaNaoEncontradaException extends RuntimeException{
    public TarefaNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}
