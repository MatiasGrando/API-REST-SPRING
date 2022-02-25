package com.alkemy.disney.disneyApiRest.dto;

import com.alkemy.disney.disneyApiRest.model.TituloModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class PersonajeDTO {

    private Integer id;
    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;
    private String imagen;
    private List<TituloDTO> titulos = new ArrayList<>();


}
