package com.alkemy.disney.disneyApiRest.dto;

import com.alkemy.disney.disneyApiRest.model.GeneroModel;
import com.alkemy.disney.disneyApiRest.model.PersonajeModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class TituloDTO {
    private Integer id;
    private String nombre;
    private String imagen;
    private Integer calificacion;
    private LocalDate fechaCreacion;
    private Integer generoId;
    private List<PersonajeDTO> personajes;
}
