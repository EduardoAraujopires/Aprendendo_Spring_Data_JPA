package io.github.aprendendo_spring_data_jpa.libraryapi.repository;

import io.github.aprendendo_spring_data_jpa.libraryapi.model.Autor;
import io.github.aprendendo_spring_data_jpa.libraryapi.model.GeneroLivro;
import io.github.aprendendo_spring_data_jpa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        autor.setNome("Gustavo");
        autor.setNacionalidade("brasileiro");
        autor.setDataNacimento(LocalDate.of(2008, 3,16));
        Autor autorNovo = repository.save(autor);
        System.out.println("Novo Autor: " + autorNovo);

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
        autor.setNome("Cloves");
        autor.setNacionalidade("Brasileira");
        autor.setDataNacimento(LocalDate.of(1968, 11, 26));

        Livro livro = new Livro();
        livro.setTitulo("Eu Vou Conseguir");
        livro.setIsbn("1542-2215");
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setPreco(BigDecimal.valueOf(170));
        livro.setDataPublicacao(LocalDate.of(2021, 3,5));
        livro.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);

        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivroTest(){
       UUID id = UUID.fromString("429fc161-35f2-4735-8230-07b8d1526ccf");
       Autor autor = repository.findById(id).get();
        List<Livro> listaLivros = livroRepository.findByAutor(autor);
        autor.setLivros(listaLivros);
        autor.getLivros().forEach(System.out::println);
    }
}
