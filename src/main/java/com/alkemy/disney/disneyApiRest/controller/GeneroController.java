package com.alkemy.disney.disneyApiRest.controller;

import com.alkemy.disney.disneyApiRest.dto.GeneroDTO;
import com.alkemy.disney.disneyApiRest.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll(){
        List<GeneroDTO> generos = generoService.getAllGeneros();
        return ResponseEntity.ok().body(generos);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<GeneroDTO> getById(@PathVariable Integer id){
        GeneroDTO genero = generoService.getById(id);
        return ResponseEntity.ok().body(genero);
    }


    @PostMapping
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO genero){

       GeneroDTO generoGuardado = generoService.save(genero);
       return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<GeneroDTO> update(@RequestBody GeneroDTO genero,@PathVariable Integer id){

        GeneroDTO generoActualizado = generoService.update(id, genero);
        return ResponseEntity.ok().body(generoActualizado);
    }

    @DeleteMapping(path= "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        generoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
