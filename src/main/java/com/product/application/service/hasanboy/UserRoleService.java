package com.product.application.service.hasanboy;

import com.product.application.dto.hasanboy.UserRoleDto;
import com.product.application.exception.ProductException;
import com.product.application.filter.hasanboy.UserRoleFilter;
import com.product.application.model.hasanboy.UserRole;
import com.product.application.repository.hasanboy.UserRoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRoleService {
    UserRoleRepository userRoleRepository;

    public boolean create(UserRoleDto dto) {
        UserRole userRole = new UserRole();
        userRole.setId(dto.getId());
        userRole.setName(dto.getName());
        userRole.setCreatedAt(LocalDateTime.now());
        userRoleRepository.save(userRole);
        return true;
    }

    public UserRoleDto get(Integer id) {
        UserRole userRole = getEntity(id);
        UserRoleDto dto = new UserRoleDto();
        dto.setId(userRole.getId());
        dto.setName(userRole.getName());
        return dto;
    }

    public boolean update(Integer id, UserRoleDto dto) {
        UserRole userRole = getEntity(id);
        userRole.setUpdatedAt(LocalDateTime.now());
        userRole.setName(dto.getName());
        userRole.setUpdatedAt(LocalDateTime.now());
        userRoleRepository.save(userRole);
        return true;
    }

    public boolean delete(Integer id) {
        UserRole userRole = getEntity(id);
        userRole.setDeletedAt(LocalDateTime.now());
        userRoleRepository.save(userRole);
        return true;
    }

    public UserRole getEntity(Integer id) {
        Optional<UserRole> optional = userRoleRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            throw new ProductException("User Role not found");
        }
        return optional.get();
    }

    public List<UserRoleDto> fildAllByPage(Integer page, Integer size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<UserRole> resultPage = userRoleRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        List<UserRoleDto> response = new ArrayList<>();
        for (UserRole userRole : resultPage) {
            UserRoleDto userRoleDto = new UserRoleDto();
            userRoleDto.setId(userRole.getId());
            userRoleDto.setName(userRole.getName());
            response.add(userRoleDto);
        }
        return response;
    }

    public List<UserRoleDto> filter(UserRoleFilter filter) {
        String sortBy = filter.getSortBy();
        if (sortBy.isEmpty()){
            sortBy = "createdAt";
        }
        List<Predicate> predicateList = new ArrayList<>();
        Specification<UserRole> specification = (((root, query, criteriaBuilder) -> {
            if (filter.getName() != null){
                predicateList.add(criteriaBuilder.like(root.get("name"),"%" + filter.getName() + "%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new javax.persistence.criteria.Predicate[0]));
        }));
        Pageable pageable = (Pageable) PageRequest.of(filter.getPage(),filter.getSize(),filter.getDirection(),sortBy);
        List<UserRoleDto> resultList = new ArrayList<>();
        Page<UserRole> userRolePage = userRoleRepository.findAll(specification,(org.springframework.data.domain.Pageable) pageable);
        for (UserRole userRole : userRolePage) {
            if (userRole.getDeletedAt() == null){
                UserRoleDto dto = new UserRoleDto();
                dto.setId(userRole.getId());
                dto.setName(userRole.getName());
                resultList.add(dto);
            }
        }
        return resultList;
    }
}
