package com.alkemy.disney.disneyApiRest.service;

import com.alkemy.disney.disneyApiRest.dto.TituloBasicDTO;
import com.alkemy.disney.disneyApiRest.dto.TituloDTO;

import java.util.List;

public interface TituloService {
    TituloDTO save(TituloDTO dto);
    void addCharacter(Integer id, Integer idCharacter);

    List<TituloDTO> getAllTitulos();
    TituloDTO update(Integer id,TituloDTO titulo);

    void delete(Integer id);

    List<TituloBasicDTO> getAllTitulosBasic();
    TituloDTO getById(Integer id);

    void deleteCharacter(Integer id, Integer idCharacter);

    List<TituloDTO> getByFilters(String name, String gender, String order);
}
