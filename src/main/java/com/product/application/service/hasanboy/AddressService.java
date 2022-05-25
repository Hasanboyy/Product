package com.product.application.service.hasanboy;

import com.product.application.dto.hasanboy.AddressDto;
import com.product.application.exception.ProductException;
import com.product.application.model.hasanboy.Address;
import com.product.application.repository.hasanboy.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        convertDtotoEntity(dto,address);
        addressRepository.save(address);
        return true;
    }

    public AddressDto get(Integer id) {
        Address address = getEntity(id);
        AddressDto dto = new AddressDto();
        convertEntityToDto(address,dto);
        return dto;
    }

    public boolean update(Integer id, AddressDto dto) {
        Address address = getEntity(id);
        convertDtotoEntity(dto,address);
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

    private Address getEntity(Integer id) {
        Optional<Address> optional = addressRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
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
