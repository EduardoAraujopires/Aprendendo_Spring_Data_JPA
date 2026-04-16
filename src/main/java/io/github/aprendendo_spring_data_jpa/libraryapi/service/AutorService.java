package io.github.aprendendo_spring_data_jpa.libraryapi.service;

import io.github.aprendendo_spring_data_jpa.libraryapi.exceptions.OperacaoNaoPermitidaExcepetions;
import io.github.aprendendo_spring_data_jpa.libraryapi.model.Autor;
import io.github.aprendendo_spring_data_jpa.libraryapi.repository.AutorRepository;
import io.github.aprendendo_spring_data_jpa.libraryapi.repository.LivroRepository;
import io.github.aprendendo_spring_data_jpa.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorValidator validator;
    private final LivroRepository livroRepository;


    public Autor salvar(Autor autor) {
        validator.validar(autor);
        return autorRepository.save(autor);
    }

    public void atualizar(Autor autor) {
        if (autor.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessario que o autor esteje cadastrado na base de dados.");
        }
        validator.validar(autor);
        autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return autorRepository.findById(id);
    }

    public void delete(Autor autor) {
        if (possuiAlgumLivro(autor)) {
            throw new OperacaoNaoPermitidaExcepetions("Nao é permitido excluir autor que tem livros cadastrados");
        }
        autorRepository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade) {
        if (nome != null && nacionalidade != null) {
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        }
        if (nome != null) {
            return autorRepository.findByNome(nome);
        }

        if (nacionalidade != null) {
            return autorRepository.findByNacionalidade(nacionalidade);
        }
        return autorRepository.findAll();
    }

    public boolean possuiAlgumLivro(Autor autor) {
        return livroRepository.existsByAutor(autor);
    }

}
