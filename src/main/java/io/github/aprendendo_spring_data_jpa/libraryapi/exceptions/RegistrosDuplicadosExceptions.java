package io.github.aprendendo_spring_data_jpa.libraryapi.exceptions;

public class RegistrosDuplicadosExceptions extends RuntimeException{
    public RegistrosDuplicadosExceptions(String message) {
        super(message);
    }
}
