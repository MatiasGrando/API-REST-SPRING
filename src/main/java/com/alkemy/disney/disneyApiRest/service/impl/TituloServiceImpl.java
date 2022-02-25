package com.alkemy.disney.disneyApiRest.service.impl;

import com.alkemy.disney.disneyApiRest.dto.PersonajeDTO;
import com.alkemy.disney.disneyApiRest.dto.TitleFiltersDTO;
import com.alkemy.disney.disneyApiRest.dto.TituloBasicDTO;
import com.alkemy.disney.disneyApiRest.dto.TituloDTO;
import com.alkemy.disney.disneyApiRest.mapper.PersonajeMapper;
import com.alkemy.disney.disneyApiRest.mapper.TituloMapper;
import com.alkemy.disney.disneyApiRest.model.PersonajeModel;
import com.alkemy.disney.disneyApiRest.model.TituloModel;
import com.alkemy.disney.disneyApiRest.repository.PersonajeRepository;
import com.alkemy.disney.disneyApiRest.repository.Specifications.TitleSpecification;
import com.alkemy.disney.disneyApiRest.repository.TituloRepository;
import com.alkemy.disney.disneyApiRest.service.TituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class TituloServiceImpl implements TituloService {

    @Autowired
    private TituloMapper tituloMapper;
    @Autowired
    private TituloRepository tituloRepository;
    @Autowired
    private PersonajeRepository personajeRepository;
    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private TitleSpecification titleSpecification;



    public TituloDTO save(TituloDTO tdo) {
        TituloModel model = tituloMapper.tituloTDO2Model(tdo);
        //for(PersonajeDTO personajeDTO : tdo.getPersonajes()){
        //    model.getPersonajes().add(personajeRepository.save(personajeMapper.personajeDTO2Model(personajeDTO)));
       // };
        TituloModel modelSave = tituloRepository.save(model);
        TituloDTO result = tituloMapper.tituloModel2DTO(modelSave,true);
        return result;
    }


    public void addCharacter(Integer id, Integer idCharacter) {
        TituloModel titulo = tituloRepository.findById(id).orElseThrow();
        PersonajeModel personaje = personajeRepository.findById(idCharacter).orElseThrow();

        titulo.addPersonaje(personaje);
        tituloRepository.save(titulo);
    }


    public List<TituloDTO> getAllTitulos() {
        List<TituloModel> models = tituloRepository.findAll();
        List<TituloDTO> dtos = tituloMapper.tituloModelList2DTOList(models,false);
        return dtos;
    }

    public TituloDTO update(Integer id,TituloDTO tituloDto) {

        TituloModel newModel= tituloMapper.tituloTDO2Model(tituloDto);
        TituloModel model =  tituloRepository.findById(id).orElseThrow();
        model = tituloMapper.modelUpdate(newModel,model);
        tituloRepository.save(model);
        TituloDTO result = tituloMapper.tituloModel2DTO(model,false);
        return result;
    }

    @Transactional
    public void  delete(Integer id) {

        if(tituloRepository.existsById(id)){
            tituloRepository.deleteById(id);

        }else{}

    }

    public List<TituloBasicDTO> getAllTitulosBasic() {
        List<TituloBasicDTO> modelsBasic = tituloMapper.tituloDTOList2DTOBasicList(this.getAllTitulos());
        return modelsBasic;
    }


    public TituloDTO getById(Integer id) {
        TituloModel model = tituloRepository.findById(id).orElseThrow();
        TituloDTO result = tituloMapper.tituloModel2DTO(model,true);
        return result;
    }


    public void deleteCharacter(Integer id, Integer idCharacter) {
        TituloModel title = tituloRepository.findById(id).orElseThrow();
        PersonajeModel character = personajeRepository.findById(idCharacter).orElseThrow();
        title.deletePersonaje(character);
        tituloRepository.save(title);

    }


    public List<TituloDTO> getByFilters(String name, String gender, String order) {
        TitleFiltersDTO titleFiltersDTO = new TitleFiltersDTO(name,gender,order);
        List<TituloModel> models = tituloRepository.findAll(titleSpecification.getByFilters(titleFiltersDTO));
        List<TituloDTO> dtos = tituloMapper.tituloModelList2DTOList(models,true);
        return dtos;
    }
}
