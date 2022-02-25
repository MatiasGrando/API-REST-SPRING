package com.alkemy.disney.disneyApiRest.mapper;

import com.alkemy.disney.disneyApiRest.dto.PersonajeBasicDTO;
import com.alkemy.disney.disneyApiRest.dto.PersonajeDTO;
import com.alkemy.disney.disneyApiRest.model.PersonajeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {
    @Autowired
    private TituloMapper tituloMapper;

    public PersonajeModel personajeDTO2Model(PersonajeDTO dto){
        PersonajeModel model = new PersonajeModel();
        model.setImagen(dto.getImagen());
        model.setNombre(dto.getNombre());
        model.setEdad(dto.getEdad());
        model.setHistoria(dto.getHistoria());
        model.setPeso(dto.getPeso());

        return model;
    }
    public PersonajeModel updateCharacter(PersonajeModel newModel, PersonajeModel model){
        model.setNombre(newModel.getNombre());
        model.setImagen(newModel.getImagen());
        model.setPeso(newModel.getPeso());
        model.setHistoria(newModel.getHistoria());
        model.setEdad(newModel.getEdad());
        return model;
    }
    public PersonajeDTO personajeModel2DTO(PersonajeModel model, Boolean loadTitle){
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(model.getId());
        dto.setImagen(model.getImagen());
        dto.setNombre(model.getNombre());
        dto.setEdad(model.getEdad());
        dto.setHistoria(model.getHistoria());
        dto.setPeso(model.getPeso());
        if(loadTitle){
            dto.setTitulos(tituloMapper.tituloModelList2DTOList(model.getTitulos(),false));
        }

        return dto;
    }
    public List<PersonajeDTO> personajeModelList2DTOList(List<PersonajeModel> models,Boolean loadTitle){

        List<PersonajeDTO> dtos = new ArrayList<>();
        for(PersonajeModel model: models){
            dtos.add(this.personajeModel2DTO(model,loadTitle));
        }
        return dtos;
    }
    public PersonajeBasicDTO personajeDTO2BasicDTO(PersonajeDTO dto) {

        PersonajeBasicDTO basicDto = new PersonajeBasicDTO();
        basicDto.setNombre(dto.getNombre());
        basicDto.setImagen(dto.getImagen());
        return basicDto;

    }

    public List<PersonajeBasicDTO> personajeDTOList2DTOBasicList(List<PersonajeDTO> dtos) {
        List<PersonajeBasicDTO> basicDtos = new ArrayList<>();
        for(PersonajeDTO dto : dtos){
            basicDtos.add(this.personajeDTO2BasicDTO(dto));
        }
        return basicDtos;

    }

    public List<PersonajeModel> personajeDTOList2ModelList(List<PersonajeDTO> dtos) {

        List<PersonajeModel> models = new ArrayList<>();
        for (PersonajeDTO dto : dtos) {
            models.add(this.personajeDTO2Model(dto));
        }
        return models;
    }
}
