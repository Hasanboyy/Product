package com.product.application.service.azamat;

import com.product.application.dto.azamat.MerchandDto;
import com.product.application.exception.ProductException;
import com.product.application.filter.azamat.MarchantFilter;
import com.product.application.model.azamat.Merchant;
import com.product.application.repository.azamat.MarchentRepository;
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
public class MenrchantService {

    private final MarchentRepository marchentRepository;
    public boolean create(MerchandDto merchandDto) {
        Merchant merchant = new Merchant();
        merchandDto.setId(merchant.getId());
        convertDtoToEntity(merchandDto, merchant);
        marchentRepository.save(merchant);
        return true;
    }

    public MerchandDto get(Integer id) {
        Merchant merchant = getEntity(id);
        MerchandDto merchandDto = new MerchandDto();
        convertEntityToDto(merchandDto, merchant);
        return merchandDto;
    }

    public boolean update(Integer id, MerchandDto merchandDto) {
        Merchant update = getEntity(id);
        update.setName(merchandDto.getName());
        update.setUpdatedAt(LocalDateTime.now());
        update.setStatus(true);
        marchentRepository.save(update);
        return true;
    }

    public boolean delete(Integer id) {
        Merchant merchant = getEntity(id);
        merchant.setDeletedAt(LocalDateTime.now());
        marchentRepository.save(merchant);
        return true;
    }

    public List<MerchandDto> findAllByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Merchant> resultPage = marchentRepository.findAll(pageable);
        List<MerchandDto> response = new ArrayList<>();
        for (Merchant merchant : resultPage) {
            if (merchant.getDeletedAt() == null){
                MerchandDto dto = new MerchandDto();
                convertEntityToDto(dto, merchant);
                response.add(dto);
            }
        }
        return response;
    }

    public List<MerchandDto> filter(MarchantFilter dto) {
        String sortBy = dto.getSortBy();
        if (sortBy == null || sortBy.isEmpty()){
            sortBy = "createdAt";
        }

        List<Predicate> predicateList = new ArrayList<>();
        Specification<Merchant> specifications = (((root, query, criteriaBuilder) -> {
            if (dto.getName() != null){
                predicateList.add(criteriaBuilder.like(root.get("name"), ("%" + dto.getName() + "%")));
            }
            if (dto.getSurname() != null){
                predicateList.add(criteriaBuilder.like(root.get("surname"), ("%" + dto.getSurname() + "%")));
            }
            if (dto.getDirection() != null){
                predicateList.add(criteriaBuilder.equal(root.get("direction"), dto.getDirec()));
            }
            return criteriaBuilder.and(predicateList.toArray(new javax.persistence.criteria.Predicate[0]));
        }));

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize(), dto.getDirection(), sortBy);
        List<MerchandDto> resultList = new ArrayList<>();
        Page<Merchant> merchantPage = marchentRepository.findAll(specifications, pageable);
        for (Merchant merchant : merchantPage) {
            if (merchant.getDeletedAt() == null){
                MerchandDto merchandDto = new MerchandDto();
                convertEntityToDto(merchandDto, merchant);
                resultList.add(merchandDto);
            }
        }
        return resultList;
    }

    public Merchant getEntity(Integer id){
        Optional<Merchant> optional = marchentRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new ProductException("Merchant not found");
        }
        return optional.get();
    }

    private void convertDtoToEntity(MerchandDto dto, Merchant entity) {
        entity.setName(dto.getName());
        entity.setStatus(true);
        entity.setCreatedAt(LocalDateTime.now());
    }

    private void convertEntityToDto(MerchandDto dto, Merchant entity) {
        dto.setId(entity.getId());
        dto.setName(entity.getName());
    }
}
