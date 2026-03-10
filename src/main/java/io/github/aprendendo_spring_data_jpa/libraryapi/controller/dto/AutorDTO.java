package io.github.aprendendo_spring_data_jpa.libraryapi.controller.dto;


import io.github.aprendendo_spring_data_jpa.libraryapi.model.Autor;

import java.time.LocalDate;

public record AutorDTO(String nome,
                       LocalDate dataNascimento,
                       String nacionalidade) {

    public Autor saveComDTO(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNacimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
