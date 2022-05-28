package com.product.application.service.azamat;

import com.product.application.dto.azamat.DivigatelDto;
import com.product.application.exception.ProductException;
import com.product.application.filter.azamat.DivigatelFilter;
import com.product.application.model.azamat.Divigatel;
import com.product.application.repository.azamat.DivigatelRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DivigatelService {
    private final DivigatelRepository divigatelRepository;

    public boolean create(DivigatelDto divigatelDto) {
        Divigatel divigatel = new Divigatel();
        divigatelDto.setId(divigatel.getId());
        convertDtoToEntity(divigatelDto, divigatel);
        divigatelRepository.save(divigatel);
        return true;
    }

    public DivigatelDto get(Integer id) {
        Divigatel divigatel = getEntity(id);
        DivigatelDto divigatelDto = new DivigatelDto();
        convertEntityToDto(divigatelDto, divigatel);
        return divigatelDto;
    }

    public boolean update(Integer id, DivigatelDto divigatelDto) {
        Divigatel update = getEntity(id);
        update.setSize(divigatelDto.getSize());
        update.setStatus(true);
        update.setUpdatedAt(LocalDateTime.now());
        divigatelRepository.save(update);
        return true;
    }

    public boolean delete(Integer id) {
        Divigatel divigatel = getEntity(id);
        divigatel.setDeletedAt(LocalDateTime.now());
        divigatelRepository.save(divigatel);
        return true;
    }

    public List<DivigatelDto> findAllByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Divigatel> resultPage = divigatelRepository.findAll(pageable);
        List<DivigatelDto> response = new ArrayList<>();
        for (Divigatel divigatel : resultPage) {
            if (divigatel.getDeletedAt() == null){
                DivigatelDto dto = new DivigatelDto();
                convertEntityToDto(dto, divigatel);
                response.add(dto);
            }
        }
        return response;
    }

    public List<DivigatelDto> filter(DivigatelFilter dto) {
       String sortBy = dto.getSortBy();
       if (sortBy == null || sortBy.isEmpty()){
           sortBy = "created_at";
       }

        List<Predicate> predicateList = new ArrayList<>();
        Specification<Divigatel> specifications = (((root, query, criteriaBuilder) -> {
            if (dto.getName() != null) {
                predicateList.add(criteriaBuilder.like(root.get("name"), ("%" + dto.getName() + "%")));
            }
            if (dto.getSurname() != null) {
                predicateList.add(criteriaBuilder.like(root.get("surname"), ("%" + dto.getSurname() + "%")));
            }
            if (dto.getDirection() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("direction"), dto.getDirec()));
            }
            return criteriaBuilder.and(predicateList.toArray(new javax.persistence.criteria.Predicate[0]));
        }));

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize(), dto.getDirection(), sortBy);
        List<DivigatelDto> resultList = new ArrayList<>();
        Page<Divigatel> divigatelPage = divigatelRepository.findAll(specifications, pageable);
        for (Divigatel divigatel : divigatelPage) {
            if (divigatel.getDeletedAt() == null) {
                DivigatelDto divigatelDto = new DivigatelDto();
                convertEntityToDto(divigatelDto, divigatel);
                resultList.add(divigatelDto);
            }
        }
        return resultList;
    }

    private Divigatel getEntity(Integer id) {
        Optional<Divigatel> optional = divigatelRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new ProductException("Dvigatel not found");
        }
        return optional.get();
    }

    private void convertDtoToEntity(DivigatelDto dto, Divigatel entity) {
        entity.setSize(dto.getSize());
        entity.setStatus(true);
        entity.setCreatedAt(LocalDateTime.now());
    }

    private void convertEntityToDto(DivigatelDto dto, Divigatel entity) {
        dto.setId(entity.getId());
        dto.setSize(entity.getSize());
    }
}
