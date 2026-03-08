package io.github.aprendendo_spring_data_jpa.libraryapi.controller.dto;


import java.time.LocalDate;

public record AutorDTO(String nome,
                       LocalDate dataNascimento,
                       String nacionalidade) {
}
