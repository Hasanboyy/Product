package com.product.application.service.hasanboy;

import com.product.application.dto.hasanboy.ImageDto;
import com.product.application.exception.ProductException;
import com.product.application.filter.hasanboy.ImageFilter;
import com.product.application.model.hasanboy.Image;
import com.product.application.repository.hasanboy.ImageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import javax.persistence.criteria.Predicate;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageService {

    ImageRepository imageRepository;

    public boolean create(ImageDto dto) {
        Image image = new Image();
        image.setId(dto.getId());
        image.setCreatedAt(LocalDateTime.now());
        convertDtoToEntity(dto, image);
        imageRepository.save(image);
        return true;
    }

    public ImageDto get(Integer id) {
        Image image = getEntity(id);
        ImageDto dto = new ImageDto();
        convertEntityToDto(dto, image);
        return dto;
    }

    public boolean update(Integer id, ImageDto dto) {
        Image image = getEntity(id);
        image.setUpdatedAt(LocalDateTime.now());
        convertDtoToEntity(dto, image);
        imageRepository.save(image);
        return true;
    }


    public boolean delete(Integer id) {
        Image image = getEntity(id);
        image.setDeletedAt(LocalDateTime.now());
        imageRepository.save(image);
        return true;
    }

    public Image getEntity(Integer id) {
        Optional<Image> optional = imageRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            throw new ProductException("Image not found");
        }
        return optional.get();
    }

    public List<ImageDto> findAllByPage(Integer page, Integer size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<Image> resultPage = imageRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        List<ImageDto> response = new ArrayList<>();
        for (Image image : resultPage) {
            if (image.getDeletedAt() == null) {
                ImageDto imageDto = new ImageDto();
                convertEntityToDto(imageDto, image);
                response.add(imageDto);
            }
        }
        return response;
    }

    public List<ImageDto> filter(ImageFilter imageFilter) {
        String sortBy = imageFilter.getSortBy();
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "createdAt";
        }

        List<Predicate> predicateList = new ArrayList<>();
        Specification<Image> specification = (((root, query, criteriaBuilder) -> {
            if (imageFilter.getPath() != null) {
                predicateList.add(criteriaBuilder.like(root.get("path"), "%" + imageFilter.getPath() + "%"));
            }
            if (imageFilter.getType() != null) {
                predicateList.add(criteriaBuilder.like(root.get("type"), "%" + imageFilter.getType() + "%"));
            }
            if (imageFilter.getSize() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("size"), "%" + imageFilter.getSize() + "%"));
            }
            if (imageFilter.getToken() != null) {
                predicateList.add(criteriaBuilder.like(root.get("token"), "%" + imageFilter.getToken() + "%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new javax.persistence.criteria.Predicate[0]));
        }));

        Pageable pageable = (Pageable) PageRequest.of(imageFilter.getPage(), imageFilter.getSize(), imageFilter.getDirection(), sortBy);
        List<ImageDto> resultList = new ArrayList<>();
        Page<Image> imagePage = imageRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        for (Image image : imagePage) {
            if (image.getDeletedAt() == null){
                ImageDto dto = new ImageDto();
                convertEntityToDto(dto,image);
                resultList.add(dto);
            }
        }
        return resultList;
    }

    private void convertEntityToDto(ImageDto dto, Image image) {
        dto.setPath(image.getPath());
        dto.setType(image.getType());
        dto.setSize(image.getSize());
        dto.setToken(image.getToken());
    }

    private void convertDtoToEntity(ImageDto dto, Image image) {
        image.setPath(dto.getPath());
        image.setType(dto.getType());
        image.setSize(dto.getSize());
        image.setToken(dto.getToken());
    }
}
