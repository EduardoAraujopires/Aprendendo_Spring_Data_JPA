package io.github.aprendendo_spring_data_jpa.libraryapi.repository;

import io.github.aprendendo_spring_data_jpa.libraryapi.model.Autor;
import io.github.aprendendo_spring_data_jpa.libraryapi.model.GeneroLivro;
import io.github.aprendendo_spring_data_jpa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {

        Autor autor = new Autor();
        autor.setNome("Eduardo");
        autor.setNacionalidade("Brasileiro");
        autor.setDataPublicacao(LocalDate.of(2008, 3, 16));

        var autorNovo = repository.save(autor);
        System.out.println("Autor Novo: " + autorNovo);
    }

    @Test
    public void atualizar(){
        var id = UUID.fromString("37b30db5-c366-4a77-9ab1-354fd220c874");
        Optional<Autor> possivelAutor =  repository.findById(id);
        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);
            autorEncontrado.setNacionalidade("Brasileira");
            repository.save(autorEncontrado);
        }
    }

    @Test
    public void findAll(){
       List<Autor> lista = repository.findAll();
       lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autor: " + repository.count());
    }

    @Test
    public void deleteByIdTest(){
        var id = UUID.fromString("2563f5c6-50e0-4e96-ba8f-0d1914dce72c");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("37b30db5-c366-4a77-9ab1-354fd220c874");
        var neuma = repository.findById(id).get();
        repository.delete(neuma);
    }

    @Test
    void salvarAutorTest(){
        Autor autor = new Autor();
        autor.setNome("Paulo");
        autor.setNacionalidade("Brasileira");
        autor.setDataPublicacao(LocalDate.of(1972, 9, 20));

        Livro livro = new Livro();
        livro.setTitulo("Revolução");
        livro.setIsbn("178412-2222");
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setPreco(BigDecimal.valueOf(600));
        livro.setDataPublicacao(LocalDate.of(2088, 3, 16));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setTitulo("Mente de milhoes");
        livro2.setIsbn("877-2222");
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setPreco(BigDecimal.valueOf(300));
        livro2.setDataPublicacao(LocalDate.of(2005, 6, 21));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);
        repository.save(autor);


    }
}
