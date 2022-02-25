package com.alkemy.disney.disneyApiRest.service.impl;

import com.alkemy.disney.disneyApiRest.dto.CharacterFiltersDTO;
import com.alkemy.disney.disneyApiRest.dto.PersonajeBasicDTO;
import com.alkemy.disney.disneyApiRest.dto.PersonajeDTO;
import com.alkemy.disney.disneyApiRest.mapper.PersonajeMapper;
import com.alkemy.disney.disneyApiRest.model.PersonajeModel;
import com.alkemy.disney.disneyApiRest.repository.PersonajeRepository;
import com.alkemy.disney.disneyApiRest.repository.Specifications.CharacterSpecification;
import com.alkemy.disney.disneyApiRest.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    PersonajeMapper personajeMapper;
    @Autowired
    PersonajeRepository personajeRepository;
    @Autowired
    CharacterSpecification characterSpecification;

    public PersonajeDTO update(Integer id, PersonajeDTO personajeActualizadoDto){
        PersonajeModel newCharacterModel = personajeMapper.personajeDTO2Model(personajeActualizadoDto);
        PersonajeModel characterModel = personajeRepository.findById(id).orElseThrow();
        characterModel = personajeMapper.updateCharacter(newCharacterModel,characterModel);
        personajeRepository.save(characterModel);
        PersonajeDTO result = personajeMapper.personajeModel2DTO(characterModel,false);
        return result;
    }

    public void delete(Integer id){
        personajeRepository.deleteById(id);
    }

    public List<PersonajeDTO> getByFilters(String name, String age, String weight, List<Integer> titles) {
        CharacterFiltersDTO charactersFilterDTO= new CharacterFiltersDTO(name,age,weight,titles);
        List<PersonajeModel> models = personajeRepository.findAll(characterSpecification.getByFilters(charactersFilterDTO));
        List<PersonajeDTO> dtos = personajeMapper.personajeModelList2DTOList(models,true);
        return dtos;
    }


    public PersonajeDTO save(PersonajeDTO dto) {

        PersonajeModel model = personajeMapper.personajeDTO2Model(dto);
        PersonajeModel modelGuardado = personajeRepository.save(model);
        PersonajeDTO result = personajeMapper.personajeModel2DTO(modelGuardado,true);

        return result;
    }

    public PersonajeDTO getCharacterById(Integer id){
        PersonajeModel characterModel = personajeRepository.findById(id).orElseThrow();
        PersonajeDTO characterDTO = personajeMapper.personajeModel2DTO(characterModel, true);
        return characterDTO;
    }
    public List<PersonajeDTO> getAllPersonajes() {
        List<PersonajeModel> models = personajeRepository.findAll();
        List<PersonajeDTO> dtos = personajeMapper.personajeModelList2DTOList(models,false);
        return dtos;
    }

    public List<PersonajeBasicDTO> getAllPersonajesBasic(){

        List<PersonajeBasicDTO> dtos=personajeMapper.personajeDTOList2DTOBasicList(this.getAllPersonajes());
        return dtos;
    }
}
