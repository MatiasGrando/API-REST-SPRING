package com.alkemy.disney.disneyApiRest.controller;


import com.alkemy.disney.disneyApiRest.dto.TituloBasicDTO;
import com.alkemy.disney.disneyApiRest.dto.TituloDTO;
import com.alkemy.disney.disneyApiRest.model.TituloModel;
import com.alkemy.disney.disneyApiRest.service.TituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("movies")
public class TituloController {
    @Autowired
    TituloService tituloService;


    @GetMapping(path = "/{id}")
    public ResponseEntity<TituloDTO> getById(@PathVariable Integer id){
        TituloDTO title = tituloService.getById(id);
        return ResponseEntity.ok().body(title);
    }
    @GetMapping("/allTitles")
    public ResponseEntity<List<TituloDTO>> getAll(){
        List<TituloDTO> titulos = tituloService.getAllTitulos();
        return ResponseEntity.ok().body(titulos);
    }
    @GetMapping(path = "/filters")
    public ResponseEntity<List<TituloDTO>> getByFilters(
            @RequestParam (required = true) String name,
            @RequestParam (required = false) String gender,
            @RequestParam (required = false) String order){
        List<TituloDTO> dtos =tituloService.getByFilters(name,gender,order);
        return ResponseEntity.ok().body(dtos);
    }
    @GetMapping
    public ResponseEntity<List<TituloBasicDTO>> getAllTitulosBasic(){
        List<TituloBasicDTO> titulosBasic = tituloService.getAllTitulosBasic();
        return ResponseEntity.ok().body(titulosBasic);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.tituloService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path ="/{id}")
    public ResponseEntity<TituloDTO> update(@PathVariable Integer id, @RequestBody TituloDTO tituloNuevo){
        TituloDTO tituloActualizado = this.tituloService.update(id,tituloNuevo);
        return ResponseEntity.ok().body(tituloActualizado);
    }

    @DeleteMapping(path = "/{id}/personaje/{idCharacter}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Integer id,@PathVariable Integer idCharacter ){
        tituloService.deleteCharacter(id,idCharacter);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/{id}/character/{idCharacter}")
    public ResponseEntity<Void> addCharacter(@PathVariable Integer id, @PathVariable Integer idCharacter){
        tituloService.addCharacter(id,idCharacter);
        return ResponseEntity.ok().build();

    }
    @PostMapping
    public ResponseEntity<TituloDTO> save(@RequestBody TituloDTO titulo){

        TituloDTO tituloGuardado =tituloService.save(titulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(tituloGuardado);
    }
}
