package io.github.aprendendo_spring_data_jpa.libraryapi.controller;

import io.github.aprendendo_spring_data_jpa.libraryapi.controller.dto.AutorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @PostMapping
    public ResponseEntity salve(@RequestBody AutorDTO autor) {
        return new ResponseEntity("Autor Salvo com Sucesso " + autor, HttpStatus.CREATED);
    }
}
