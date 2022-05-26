package com.product.application.service.hasanboy;

import com.product.application.dto.hasanboy.UserDto;
import com.product.application.exception.ProductException;
import com.product.application.model.hasanboy.User;
import com.product.application.repository.hasanboy.UserRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    private ImageService imageService;

    private AddressService addressService;

    private UserRoleService userRoleService;

    public boolean create(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        imageService.getEntity(dto.getImageId());
        addressService.getEntity(dto.getAddressId());
        userRoleService.getEntity(dto.getUserRoleId());
        convertEntityToDto(dto,user);
        return true;
    }

    public UserDto get(Integer id) {
        User user = getEntity(id);
        return null;
    }

    private User getEntity(Integer id) {
        Optional<User> optional = userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new ProductException("User not found");
        }
        return optional.get();
    }

    public boolean update(Integer id) {

        return false;
    }

    public boolean delete(Integer id) {
        return false;
    }

    private void convertEntityToDto(UserDto dto, User user) {
        user.setImageId(dto.getImageId());
        user.setAddressId(dto.getAddressId());
        user.setUserRoleId(dto.getUserRoleId());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setContact(dto.getContact());
    }
}
