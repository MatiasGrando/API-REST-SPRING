package com.alkemy.disney.disneyApiRest.controller;


import com.alkemy.disney.disneyApiRest.dto.PersonajeBasicDTO;
import com.alkemy.disney.disneyApiRest.dto.PersonajeDTO;
import com.alkemy.disney.disneyApiRest.dto.TituloDTO;
import com.alkemy.disney.disneyApiRest.model.TituloModel;
import com.alkemy.disney.disneyApiRest.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("characters")
public class PersonajeController {

    @Autowired
    PersonajeService personajeService;


    @PutMapping(path= "/update/{id}")
    public ResponseEntity<PersonajeDTO> update(@PathVariable Integer id,@RequestBody PersonajeDTO personaje){
        PersonajeDTO personajeActualizado= personajeService.update(id, personaje);
        return ResponseEntity.ok().body(personajeActualizado);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PersonajeDTO> getCharacterById(@PathVariable Integer id){
        PersonajeDTO character = personajeService.getCharacterById(id);
        return ResponseEntity.ok().body(character);
    }
    @GetMapping
    public ResponseEntity<List<PersonajeBasicDTO>> getAllPersonajesBasic(){
        List<PersonajeBasicDTO> personajes = personajeService.getAllPersonajesBasic();
        return ResponseEntity.ok().body(personajes);
    }

    @GetMapping(path = "filters")
    public ResponseEntity<List<PersonajeDTO>> getCharacterByFilters (
            @RequestParam(required = true) String name,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String weight,
            @RequestParam(required = false) List<Integer> titles){
        List<PersonajeDTO> characters = personajeService.getByFilters(name,age,weight,titles);
        return ResponseEntity.ok().body(characters);
    }

    @GetMapping("/personajes")
    public ResponseEntity<List<PersonajeDTO>> getAllPersonajes(){
        List<PersonajeDTO> personajes = personajeService.getAllPersonajes();
        return ResponseEntity.ok().body(personajes);
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personaje){

        PersonajeDTO personajeGuardado = personajeService.save(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
