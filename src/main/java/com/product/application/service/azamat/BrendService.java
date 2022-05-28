package com.product.application.service.azamat;

import com.product.application.dto.azamat.BrendDto;
import com.product.application.exception.ProductException;
import com.product.application.filter.azamat.BrendFilter;
import com.product.application.model.azamat.Brend;
import com.product.application.repository.azamat.BrendRepository;
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
public class BrendService {

    private  BrendRepository brendRepository;
    public boolean create(BrendDto brendDto) {
        Brend brend = new Brend();
        brendDto.setId(brend.getId());
        convertDtoToEntity(brendDto, brend);
        brendRepository.save(brend);
        return true;
    }

    public BrendDto get(Integer id) {
        Brend brend = getEntity(id);
        BrendDto brendDto = new BrendDto();
        convertEntityToDto(brendDto, brend);
        return brendDto;
    }

    public boolean update(Integer id, BrendDto brendDto) {
        Brend update = getEntity(id);
        update.setName(brendDto.getName());
        update.setStatus(true);
        update.setUpdatedAt(LocalDateTime.now());
        brendRepository.save(update);
        return true;
    }

    public boolean delete(Integer id) {
        Brend brend = getEntity(id);
        brend.setDeletedAt(LocalDateTime.now());
        brendRepository.save(brend);
        return true;
    }

    public List<BrendDto> findAllByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Brend> resultPage = brendRepository.findAll(pageable);
        List<BrendDto> response = new ArrayList<>();
        for (Brend brend : resultPage) {
            if (brend.getDeletedAt() == null){
                BrendDto dto = new BrendDto();
                convertEntityToDto(dto, brend);
                response.add(dto);
            }
        }
        return response;
    }

    public List<BrendDto> filter(BrendFilter dto) {
        String sortBy = dto.getSortBy();
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "createdAt";
        }

        List<Predicate> predicateList = new ArrayList<>();
        Specification<Brend> specifications = (((root, query, criteriaBuilder) -> {
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
        List<BrendDto> resultList = new ArrayList<>();
        Page<Brend> brendPage = brendRepository.findAll(specifications, pageable);
        for (Brend brend : brendPage) {
            if (brend.getDeletedAt() == null) {
                BrendDto brendDto = new BrendDto();
                convertEntityToDto(brendDto, brend);
                resultList.add(brendDto);
            }
        }
        return resultList;
    }

    private Brend getEntity(Integer id) {
        Optional<Brend> optional = brendRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new ProductException("Brend not found");
        }
        return optional.get();
    }

    private void convertDtoToEntity(BrendDto dto, Brend entity) {
        entity.setName(dto.getName());
        entity.setStatus(true);
        entity.setCreatedAt(LocalDateTime.now());
    }

    private void convertEntityToDto(BrendDto dto, Brend entity) {
        dto.setId(entity.getId());
        dto.setName(entity.getName());
    }
}
