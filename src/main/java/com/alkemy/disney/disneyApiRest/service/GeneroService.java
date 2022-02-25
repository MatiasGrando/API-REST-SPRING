package com.alkemy.disney.disneyApiRest.service;

import com.alkemy.disney.disneyApiRest.dto.GeneroDTO;

import java.util.List;

public interface GeneroService {
    GeneroDTO save(GeneroDTO dto);
    List<GeneroDTO> getAllGeneros();
    GeneroDTO getById(Integer id);
    GeneroDTO update(Integer id, GeneroDTO genero);
    void delete(Integer id);
}
