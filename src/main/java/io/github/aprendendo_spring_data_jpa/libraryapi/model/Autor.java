package io.github.aprendendo_spring_data_jpa.libraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor")
@Data
@ToString(exclude = {"livros"})
public class Autor {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "data_nascimento", nullable = false )
    private LocalDate dataNacimento;

    @Column(name = "nacionalidade", nullable = false, length = 50)
    private String nacionalidade;

   @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
   private List<Livro> livros;
}
