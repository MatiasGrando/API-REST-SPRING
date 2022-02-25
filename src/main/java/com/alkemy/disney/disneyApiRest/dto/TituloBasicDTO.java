package com.alkemy.disney.disneyApiRest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TituloBasicDTO {
    private String nombre;
    private String imagen;
    private LocalDate fechaCreacion;
}
