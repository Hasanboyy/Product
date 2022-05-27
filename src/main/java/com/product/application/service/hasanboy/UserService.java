package com.product.application.service.hasanboy;

import com.product.application.dto.hasanboy.UserDto;
import com.product.application.exception.ProductException;
import com.product.application.model.hasanboy.User;
import com.product.application.repository.hasanboy.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
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
        convertEntityToDto(dto, user);
        return true;
    }

    public UserDto get(Integer id) {
        User user = getEntity(id);
        UserDto userDto = new UserDto();
        convertDtoToEntity(userDto, user);
        return userDto;
    }

    public boolean update(Integer id, UserDto dto) {
        User update = getEntity(id);
        update.setId(dto.getId());
        imageService.getEntity(dto.getImageId());
        addressService.getEntity(dto.getAddressId());
        userRoleService.getEntity(dto.getUserRoleId());
        convertEntityToDto(dto, update);
        return true;
    }

    public boolean delete(Integer id) {
        User user = getEntity(id);
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
        return true;
    }

    public User getEntity(Integer id) {
        Optional<User> optional = userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            throw new ProductException("User not found");
        }
        return optional.get();
    }

    public void convertDtoToEntity(UserDto dto, User user) {
        dto.setImageId(user.getImageId());
        dto.setAddressId(user.getAddressId());
        dto.setUserRoleId(user.getUserRoleId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setContact(user.getContact());
    }

    public void convertEntityToDto(UserDto dto, User user) {
        user.setAddressId(dto.getAddressId());
        user.setUserRoleId(dto.getUserRoleId());
        user.setImageId(dto.getImageId());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setContact(dto.getContact());
    }
}
