package com.alkemy.disney.disneyApiRest.service;


import com.alkemy.disney.disneyApiRest.dto.PersonajeBasicDTO;
import com.alkemy.disney.disneyApiRest.dto.PersonajeDTO;
import com.alkemy.disney.disneyApiRest.model.TituloModel;

import java.util.List;
import java.util.Set;

public interface PersonajeService {

     PersonajeDTO save(PersonajeDTO personajeDto);
     PersonajeDTO getCharacterById(Integer id);
     List<PersonajeDTO> getAllPersonajes();
     List<PersonajeBasicDTO> getAllPersonajesBasic();
     PersonajeDTO update(Integer id,PersonajeDTO personajeDto);
     void delete(Integer id);

     List<PersonajeDTO> getByFilters(String name, String age, String weight, List<Integer> titles);
}
