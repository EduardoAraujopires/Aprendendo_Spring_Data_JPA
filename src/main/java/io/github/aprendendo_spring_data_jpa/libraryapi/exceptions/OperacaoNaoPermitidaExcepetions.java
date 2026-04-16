package io.github.aprendendo_spring_data_jpa.libraryapi.exceptions;

public class OperacaoNaoPermitidaExcepetions extends RuntimeException{
    public OperacaoNaoPermitidaExcepetions(String message) {
        super(message);
    }
}
