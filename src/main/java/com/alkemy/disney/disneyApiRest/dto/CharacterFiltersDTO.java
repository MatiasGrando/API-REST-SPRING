package com.alkemy.disney.disneyApiRest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
 @Getter
 @Setter

public class CharacterFiltersDTO {
    private String name;
    private String age;
    private String weight;
    private List<Integer> titles;

    public CharacterFiltersDTO(String name, String age, String weight, List<Integer> titles){
        this.name = name;
        this.age = age ;
        this.weight = weight;
        this.titles = titles;
    }
}
