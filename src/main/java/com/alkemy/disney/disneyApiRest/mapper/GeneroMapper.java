package com.alkemy.disney.disneyApiRest.mapper;

import com.alkemy.disney.disneyApiRest.dto.GeneroDTO;
import com.alkemy.disney.disneyApiRest.model.GeneroModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneroMapper {

    public GeneroModel generoDTO2Model(GeneroDTO dto){

        GeneroModel model = new GeneroModel();
        model.setNombre(dto.getNombre());
        model.setImagen(dto.getImagen());
        return model;

    }
    public GeneroDTO generoModel2DTO(GeneroModel model){

        GeneroDTO dto = new GeneroDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setImagen(model.getImagen());
        return dto;
    }
    public List<GeneroDTO> generoModelList2DTOList(List<GeneroModel> models){
        List<GeneroDTO> dtos = new ArrayList<>();
        for (GeneroModel model : models){
            dtos.add(this.generoModel2DTO(model));
        }
        return dtos;
    }
}
