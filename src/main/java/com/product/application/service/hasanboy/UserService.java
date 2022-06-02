package com.product.application.service.hasanboy;

import com.product.application.dto.hasanboy.UserDto;
import com.product.application.exception.ProductException;
import com.product.application.model.hasanboy.User;
import com.product.application.repository.hasanboy.UserRepository;
import com.product.application.repository.hasanboy.UserTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private ImageService imageService;
    private AddressService addressService;
    private UserRoleService userRoleService;
    private UserTypeRepository userTypeRepository;

    public boolean create(UserDto dto) {
        Optional<User> optional = userRepository.
                findByEmailOrContactAndDeletedAtIsNull
                        (dto.getEmail(),dto.getContact());
        if (optional.isPresent()){
            throw new ProductException("User already token");
        }
        User user = new User();
        user.setId(dto.getId());
        user.setCreatedAt(LocalDateTime.now());
        imageService.getEntity(dto.getImageId());
        addressService.getEntity(dto.getAddressId());
        userRoleService.getEntity(dto.getUserRoleId());
        convertEntityToDto(user, dto);
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
        update.setDeletedAt(LocalDateTime.now());
        imageService.getEntity(dto.getImageId());
        addressService.getEntity(dto.getAddressId());
        userRoleService.getEntity(dto.getUserRoleId());
        convertEntityToDto(update, dto);
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
        user.setAddressId(dto.getAddressId());
        user.setUserRoleId(dto.getUserRoleId());
        user.setImageId(dto.getImageId());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setContact(dto.getContact());
        user.setStatus(true);
    }

    public void convertEntityToDto(User user, UserDto dto) {
        dto.setImageId(user.getImageId());
        dto.setAddressId(user.getAddressId());
        dto.setUserRoleId(user.getUserRoleId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setContact(user.getContact());
        dto.setStatus(true);
    }

    public List<UserDto> getAllForAdmin(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(pageable);
        //return users.stream().map(user -> convertEntityToDto(user, new UserDto())).collect(Collectors.toList());
        return null;
    }

    public boolean changeUserToAdmin(Integer id) {
        User user = getEntity(id);
        user.setUserTypeId(1);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return true;
    }
}
