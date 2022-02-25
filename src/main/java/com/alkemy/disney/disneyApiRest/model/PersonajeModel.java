package com.alkemy.disney.disneyApiRest.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "personaje")
@SQLDelete(sql = "UPDATE personaje SET deleted = true WHERE id=? ")
@Where(clause = "deleted=false")

public class PersonajeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;
    private String imagen;
    private Boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<TituloModel> titulos = new ArrayList<>();

}
