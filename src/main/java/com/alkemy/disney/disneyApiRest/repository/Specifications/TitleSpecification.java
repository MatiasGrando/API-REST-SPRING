package com.alkemy.disney.disneyApiRest.repository.Specifications;

import com.alkemy.disney.disneyApiRest.dto.CharacterFiltersDTO;
import com.alkemy.disney.disneyApiRest.dto.TitleFiltersDTO;
import com.alkemy.disney.disneyApiRest.model.TituloModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class TitleSpecification {
        public Specification<TituloModel> getByFilters(TitleFiltersDTO titleFiltersDTO){
            return (root,query,criteriaBuilder) ->{

                List<Predicate> predicates = new ArrayList<>();

                if (StringUtils.hasLength(titleFiltersDTO.getName())) {
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("nombre")),
                            "%" + titleFiltersDTO.getName().toLowerCase() + "%"));
                }

                if (StringUtils.hasLength(titleFiltersDTO.getGender())){
                    predicates.add(criteriaBuilder.equal(
                            root.get("generoId"),titleFiltersDTO.getGender()));
                }

                // Remove duplucates
                query.distinct(true);

                if(StringUtils.hasLength(titleFiltersDTO.getOrder())){
                    String orderByField = "fechaCreacion";
                    query.orderBy(
                            titleFiltersDTO.isASC() ?
                                    criteriaBuilder.asc(root.get(orderByField)) :
                                    criteriaBuilder.desc(root.get(orderByField))
                    );
                }


                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
    }
}
