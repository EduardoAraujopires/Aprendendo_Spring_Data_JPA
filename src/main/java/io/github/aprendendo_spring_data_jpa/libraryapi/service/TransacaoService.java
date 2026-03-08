package io.github.aprendendo_spring_data_jpa.libraryapi.service;

import io.github.aprendendo_spring_data_jpa.libraryapi.model.Autor;
import io.github.aprendendo_spring_data_jpa.libraryapi.model.GeneroLivro;
import io.github.aprendendo_spring_data_jpa.libraryapi.model.Livro;
import io.github.aprendendo_spring_data_jpa.libraryapi.repository.AutorRepository;
import io.github.aprendendo_spring_data_jpa.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizarSemAtualizar(){
        var livro = livroRepository
                .findById(UUID.fromString("a4cadd4b-2445-4b4f-85b9-2789dee4d514"))
                .orElse(null);
        livro.setDataPublicacao(LocalDate.of(2019,7,2));

    }


    @Transactional
    public void executar(){
        Autor autor = new Autor();
        autor.setNome("Francisca");
        autor.setNacionalidade("Brasileira");
        autor.setDataNacimento(LocalDate.of(2008,3,16));
        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setTitulo("Livro da Francisca");
        livro.setIsbn("12442-14455");
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setPreco(BigDecimal.valueOf(50));
        livro.setDataPublicacao(LocalDate.of(2016,4,15));
        livro.setAutor(autor);
        livroRepository.save(livro);
         if (autor.getNome().equals("Jose")){
             throw new RuntimeException("ROLLBACK");
         }
    }
}
