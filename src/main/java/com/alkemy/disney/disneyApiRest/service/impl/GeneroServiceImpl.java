package com.alkemy.disney.disneyApiRest.service.impl;

import com.alkemy.disney.disneyApiRest.dto.GeneroDTO;
import com.alkemy.disney.disneyApiRest.mapper.GeneroMapper;
import com.alkemy.disney.disneyApiRest.model.GeneroModel;
import com.alkemy.disney.disneyApiRest.repository.GeneroRepository;
import com.alkemy.disney.disneyApiRest.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroMapper generoMapper;
    @Autowired
    private GeneroRepository generoRepository;

    public GeneroDTO save(GeneroDTO dto){

        GeneroModel model = generoMapper.generoDTO2Model(dto);
        GeneroModel modelSaved = generoRepository.save(model);
        GeneroDTO result = generoMapper.generoModel2DTO(modelSaved);

        return result;
    }

    public List<GeneroDTO> getAllGeneros() {
        List<GeneroModel> models = this.generoRepository.findAll();
        List<GeneroDTO> result = generoMapper.generoModelList2DTOList(models);
        return result;
    }

    @Override
    public GeneroDTO getById(Integer id) {
        GeneroModel model = generoRepository.findById(id).orElseThrow();
        GeneroDTO result = generoMapper.generoModel2DTO(model);
        return result;
    }

    public GeneroDTO update(Integer id, GeneroDTO genero) {
        GeneroModel generoNuevo = generoMapper.generoDTO2Model(genero);
        GeneroModel generoActual= generoRepository.findById(id).orElseThrow();

        generoActual.setImagen(generoNuevo.getImagen());
        generoActual.setNombre(generoNuevo.getNombre());
        generoRepository.save(generoActual);

        GeneroDTO result = generoMapper.generoModel2DTO(generoActual);

        return result;

    }

    public void delete(Integer id){
        GeneroModel genero=generoRepository.findById(id).orElseThrow();
        generoRepository.delete(genero);
    }

}
