package com.alkemy.disney.disneyApiRest.mapper;

import com.alkemy.disney.disneyApiRest.dto.PersonajeDTO;
import com.alkemy.disney.disneyApiRest.dto.TituloBasicDTO;
import com.alkemy.disney.disneyApiRest.dto.TituloDTO;
import com.alkemy.disney.disneyApiRest.model.PersonajeModel;
import com.alkemy.disney.disneyApiRest.model.TituloModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class TituloMapper {

    @Autowired
    private PersonajeMapper personajeMapper;

    public TituloModel  modelUpdate(TituloModel newModel, TituloModel model){
        model.setNombre(newModel.getNombre());
        model.setImagen(newModel.getImagen());
        model.setGeneroId(newModel.getGeneroId());
        model.setFechaCreacion(newModel.getFechaCreacion());
        model.setCalificacion(newModel.getCalificacion());
        model.setPersonajes(newModel.getPersonajes());
        return model;
    }

    public TituloModel tituloTDO2Model(TituloDTO tdo){

        TituloModel model = new TituloModel();
        model.setNombre(tdo.getNombre());
        model.setImagen(tdo.getImagen());
        model.setCalificacion(tdo.getCalificacion());
        model.setFechaCreacion(tdo.getFechaCreacion());
        model.setGeneroId(tdo.getGeneroId());
        //for(PersonajeDTO personajeDTO : tdo.getPersonajes()){
         //   model.getPersonajes().add(personajeMapper.personajeDTO2Model(personajeDTO));
        //}
        //model.setPersonajes(personajeMapper.personajeDTOList2ModelList(tdo.getPersonajes()));
        return model;
    }

    public TituloDTO tituloModel2DTO(TituloModel model, Boolean loadCharacter){

        TituloDTO tdo = new TituloDTO();
        tdo.setId(model.getTituloId());
        tdo.setNombre(model.getNombre());
        tdo.setImagen(model.getImagen());
        tdo.setCalificacion(model.getCalificacion());
        tdo.setFechaCreacion(model.getFechaCreacion());
        tdo.setGeneroId(model.getGeneroId());
        if(loadCharacter) {
            tdo.setPersonajes(personajeMapper.personajeModelList2DTOList(model.getPersonajes(),false));
        }
        return tdo;
    }
    public List<TituloDTO> tituloModelList2DTOList(List<TituloModel> models,Boolean loadCharacter) {
        List<TituloDTO> dtos = new ArrayList<>();
        for (TituloModel model : models) {
            dtos.add(this.tituloModel2DTO(model,loadCharacter));
        }
        return dtos;
    }
    public TituloBasicDTO tituloDTO2BasicDto(TituloDTO dto){
        TituloBasicDTO basicDto = new TituloBasicDTO();
        basicDto.setNombre(dto.getNombre());
        basicDto.setImagen(dto.getImagen());
        basicDto.setFechaCreacion(dto.getFechaCreacion());
        return basicDto;
    }

    public List<TituloBasicDTO> tituloDTOList2DTOBasicList(List<TituloDTO> dtos) {

        List<TituloBasicDTO> basicDtos = new ArrayList<>();
        for(TituloDTO dto : dtos){
            basicDtos.add(this.tituloDTO2BasicDto(dto));
        }
        return basicDtos;
    }
}
