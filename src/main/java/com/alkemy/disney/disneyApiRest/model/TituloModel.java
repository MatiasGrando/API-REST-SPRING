package com.alkemy.disney.disneyApiRest.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "titulo")
@SQLDelete(sql = "UPDATE titulo SET deleted = true WHERE titulo_id=? ")
@Where(clause = "deleted=false")


public class TituloModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "titulo_id")
    private Integer tituloId;
    private String nombre;
    private String imagen;
    private Integer calificacion;
    private Boolean deleted = Boolean.FALSE;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id", insertable=false, updatable = false)
    private GeneroModel genero;

    @Column(name ="genero_id")
    private Integer generoId;

    @ManyToMany(cascade = { CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name= "titulo_personaje",
            joinColumns = @JoinColumn(name = "titulo_id"),
            inverseJoinColumns = @JoinColumn(name="personaje_id"))
     private List<PersonajeModel> personajes = new ArrayList<>();

    public void addPersonaje(PersonajeModel personaje){
        this.personajes.add(personaje);
    }
    public void deletePersonaje(PersonajeModel personaje){this.personajes.remove(personaje);}
}
