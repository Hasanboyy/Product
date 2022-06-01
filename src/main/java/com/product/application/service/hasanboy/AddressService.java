package com.product.application.service.hasanboy;

import com.product.application.dto.hasanboy.AddressDto;
import com.product.application.exception.ProductException;
import com.product.application.filter.hasanboy.AddressFilter;
import com.product.application.model.hasanboy.Address;
import com.product.application.repository.hasanboy.AddressRepository;
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
public class AddressService {
    AddressRepository addressRepository;

    public boolean create(AddressDto dto) {
        Address address = new Address();
        address.setId(dto.getId());
        address.setStatus(true);
        address.setCreatedAt(LocalDateTime.now());
        convertDtotoEntity(dto, address);
        addressRepository.save(address);
        return true;
    }

    public AddressDto get(Integer id) {
        Address address = getEntity(id);
        AddressDto dto = new AddressDto();
        convertEntityToDto(address, dto);
        return dto;
    }

    public boolean update(Integer id, AddressDto dto) {
        Address address = getEntity(id);
        convertDtotoEntity(dto, address);
        address.setStatus(true);
        address.setUpdatedAt(LocalDateTime.now());
        addressRepository.save(address);
        return true;
    }

    public boolean delete(Integer id) {
        Address address = getEntity(id);
        address.setDeletedAt(LocalDateTime.now());
        addressRepository.save(address);
        return true;
    }

    public List<AddressDto> findAllByPage(Integer page, Integer size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<Address> resultPage = addressRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        List<AddressDto> response = new ArrayList<>();
        for (Address address : resultPage) {
            if (address.getDeletedAt() == null) {
                AddressDto addressDto = new AddressDto();
                convertEntityToDto(address, addressDto);
                response.add(addressDto);
            }
        }
        return response;
    }

    public List<AddressDto> filter(AddressFilter addressFilter) {
        String sortBy = addressFilter.getSortBy();
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "createdAt";
        }

        List<Predicate> predicateList = new ArrayList<>();
        Specification<Address> specification = (((root, query, criteriaBuilder) -> {
            if (addressFilter.getRegion() != null) {
                predicateList.add(criteriaBuilder.like(root.get("region"), "" + addressFilter.getRegion() + ""));
            }
            if (addressFilter.getCity() != null) {
                predicateList.add(criteriaBuilder.like(root.get("city"), "" + addressFilter.getRegion() + ""));
            }
            if (addressFilter.getDistrict() != null) {
                predicateList.add(criteriaBuilder.like(root.get("district"), "" + addressFilter.getRegion() + ""));
            }
            if (addressFilter.getStreet() != null) {
                predicateList.add(criteriaBuilder.like(root.get("street"), "" + addressFilter.getRegion() + ""));
            }
            if (addressFilter.getHome() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("home"), addressFilter.getHome()));
            }
            return criteriaBuilder.and(predicateList.toArray(new javax.persistence.criteria.Predicate[0]));
        }));

        Pageable pageable = (Pageable) PageRequest.of(
                addressFilter.getPage(),
                addressFilter.getSize(),
                addressFilter.getDirection(), sortBy);
        List<AddressDto> resultList = new ArrayList<>();
        Page<Address> addressPage = addressRepository.findAll(specification, (org.springframework.data.domain.Pageable) pageable);
        for (Address address : addressPage) {
            if (address.getDeletedAt() == null) {
                AddressDto addressDto = new AddressDto();
                convertEntityToDto(address, addressDto);
                resultList.add(addressDto);
            }
        }
        return resultList;
    }

    public Address getEntity(Integer id) {
        Optional<Address> optional = addressRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            throw new ProductException("Address not found");
        }
        return optional.get();
    }

    private void convertEntityToDto(Address address, AddressDto dto) {
        dto.setId(address.getId());
        dto.setRegion(address.getRegion());
        dto.setCity(address.getCity());
        dto.setDistrict(address.getDistrict());
        dto.setStreet(address.getStreet());
    }

    private void convertDtotoEntity(AddressDto dto, Address address) {
        address.setRegion(dto.getRegion());
        address.setCity(dto.getCity());
        address.setDistrict(dto.getDistrict());
        address.setStreet(dto.getStreet());
    }
}