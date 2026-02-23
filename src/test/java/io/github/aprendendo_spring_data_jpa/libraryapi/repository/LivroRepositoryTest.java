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
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvar(){
        Livro livro = new Livro();
        livro.setIsbn("15452-2142");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setTitulo("OOP");
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setDataPublicacao(LocalDate.of(1980,2,11));

        Autor autor = autorRepository.findById(UUID.fromString("13b02710-3c91-4d0a-9367-5f426e6a2dba")).orElse(null);
        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void salvarUsandoCascade(){
        Livro livro = new Livro();
        livro.setIsbn("15452-2142");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setTitulo("Segundo Livro");
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setDataPublicacao(LocalDate.of(1980,2,11));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileiro");
        autor.setDataPublicacao(LocalDate.of(2008, 3, 16));
        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void salvarSemCascade() {
        Livro livro = new Livro();
        livro.setIsbn("15452-2142");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setTitulo("Terceiro Livro");
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setDataPublicacao(LocalDate.of(1980, 2, 11));

        Autor autor = new Autor();
        autor.setNome("Neuma");
        autor.setNacionalidade("Brasileiro");
        autor.setDataPublicacao(LocalDate.of(2008, 3, 16));
        autorRepository.save(autor);

        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorDeLivro(){
        UUID id = UUID.fromString("1086af75-fa7f-45a1-b67d-75ebd94fd4de");
        var atualizacaoLivro = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("e7a2c958-5ead-4ca1-b1a7-9e88ee292166");
        var Eduardo =  autorRepository.findById(idAutor).orElse(null);

        atualizacaoLivro.setAutor(Eduardo);
        atualizacaoLivro.setTitulo("Livro dá vida");
        livroRepository.save(atualizacaoLivro);
    }

    @Test
    void deleteById(){
        UUID id = UUID.fromString("b9062da7-1488-404f-a852-8e1641db771e");
        livroRepository.deleteById(id);
    }

    @Test
    void deleteCascate(){
        UUID id = UUID.fromString("13b02710-3c91-4d0a-9367-5f426e6a2dba");
        livroRepository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("e3207d4c-0ec5-4a2f-975e-77a56be70519");
        Livro livro = livroRepository.findById(id).orElse(null);

        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());
        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }
}