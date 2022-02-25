package com.alkemy.disney.disneyApiRest.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genero")

@Getter
@Setter
@SQLDelete(sql = "UPDATE genero SET deleted = true WHERE id=? ")
@Where(clause = "deleted=false")

public class GeneroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private String nombre;
    private String imagen;
    private Boolean deleted = Boolean.FALSE;
    //@OneToMany(mappedBy = "generoId",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private List<TituloModel> titulos;
}
