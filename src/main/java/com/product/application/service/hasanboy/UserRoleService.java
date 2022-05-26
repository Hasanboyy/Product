package com.product.application.service.hasanboy;

import com.product.application.dto.hasanboy.UserRoleDto;
import com.product.application.exception.ProductException;
import com.product.application.filter.hasanboy.UserRoleFilter;
import com.product.application.model.hasanboy.UserRole;
import com.product.application.repository.hasanboy.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
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
        covertDtoToEntity(dto, userRole);
        return true;
    }

    public UserRoleDto get(Integer id) {
        UserRole userRole = getEntity(id);
        UserRoleDto dto = new UserRoleDto();
        convertEntityToDto(dto, userRole);
        return dto;
    }

    public boolean update(Integer id, UserRoleDto dto) {
        UserRole userRole = getEntity(id);
        userRole.setUpdatedAt(LocalDateTime.now());
        covertDtoToEntity(dto, userRole);
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

    private void covertDtoToEntity(UserRoleDto dto, UserRole userRole) {
        userRole.setName(dto.getName());
    }

    private void convertEntityToDto(UserRoleDto dto, UserRole userRole) {
        dto.setName(userRole.getName());
    }

    public List<UserRoleDto> fildAllByPage(Integer page, Integer size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<UserRole> resultPage = userRoleRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        List<UserRoleDto> response = new ArrayList<>();
        for (UserRole userRole : resultPage) {
            UserRoleDto userRoleDto = new UserRoleDto();
            convertEntityToDto(userRoleDto,userRole);
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
                convertEntityToDto(dto,userRole);
                resultList.add(dto);
            }
        }
        return resultList;
    }
}
