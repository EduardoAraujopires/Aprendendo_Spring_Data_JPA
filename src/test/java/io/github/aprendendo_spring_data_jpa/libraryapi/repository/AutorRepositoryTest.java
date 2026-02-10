package io.github.aprendendo_spring_data_jpa.libraryapi.repository;

import io.github.aprendendo_spring_data_jpa.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {

        Autor autor = new Autor();
        autor.setNome("Neuma");
        autor.setNacionalidade("Brasileiro");
        autor.setDataPublicacao(LocalDate.of(2008, 3, 16));

        var autorNovo = repository.save(autor);
        System.out.println("Autor Novo: " + autorNovo);
    }

    @Test
    public void atualizar(){
        var id = UUID.fromString("d6932fc8-3a57-47e3-a9a4-d8629754f507");
        Optional<Autor> possivelAutor =  repository.findById(id);
        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);
            autorEncontrado.setNome("Neuma Araujo");
            repository.save(autorEncontrado);
        }
    }


}
