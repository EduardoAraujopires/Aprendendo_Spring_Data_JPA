package io.github.aprendendo_spring_data_jpa.libraryapi.controller;

import io.github.aprendendo_spring_data_jpa.libraryapi.controller.dto.AutorDTO;
import io.github.aprendendo_spring_data_jpa.libraryapi.model.Autor;
import io.github.aprendendo_spring_data_jpa.libraryapi.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("autores")
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> salve(@RequestBody AutorDTO autor) {
        Autor autorEntity = autor.saveComDTO();
        service.save(autorEntity);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntity.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id){
        var obterId = UUID.fromString(id);
        Optional <Autor> autorOptional =  service.obterPorId(obterId);
        if (autorOptional.isPresent()){
            Autor autor = autorOptional.get();
            AutorDTO dto = new AutorDTO(
                    autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade());
            return  ResponseEntity.ok(dto);
        }
        return  ResponseEntity.notFound().build();
    }
}
