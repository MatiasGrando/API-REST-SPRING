package com.alkemy.disney.disneyApiRest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TitleFiltersDTO {
    private String name;
    private String gender;
    private String order;


    public TitleFiltersDTO(String name, String gender, String order){
        this.name=name;
        this.gender=gender;
        this.order=order;
    }

    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC") == 0;
    }
    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
