package com.product.application.service.azamat;

import com.product.application.dto.azamat.ProductTypeDto;
import com.product.application.filter.azamat.ProductTypeFilter;
import com.product.application.model.azamat.ProductType;
import com.product.application.repository.azamat.ProductTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductTypeService {

    private ProductTypeRepository productTypeRepository;

    private BrendService brendService;

    private MenrchantService menrchantService;

    private DivigatelService divigatelService;

    public boolean create(ProductTypeDto productTypeDto) {
        ProductType productType = new ProductType();
        brendService.getEntity(productTypeDto.getBrendId());
        productType.setBrendId(productType.getBrendId());

        menrchantService.get(productTypeDto.getMerchantID());
        productType.setMerchantId(productTypeDto.getMerchantID());

        divigatelService.get(productTypeDto.getDvigatelId());
        productType.setDvigatelId(productTypeDto.getDvigatelId());

        productTypeDto.setId(productType.getId());

        convertDtoToEntity(productTypeDto, productType);
        productTypeRepository.save(productType);
        return true;
    }

    public ProductTypeDto get(Integer id) {
        ProductType productType = getEntity(id);
        ProductTypeDto productTypeDto = new ProductTypeDto();
        convertEntityToDto(productTypeDto, productType);
        return productTypeDto;
    }

    public boolean update(Integer id, ProductTypeDto productTypeDto) {
        ProductType update = getEntity(id);
        brendService.getEntity(update.getBrendId());
        update.setBrendId(productTypeDto.getBrendId());


        update.setName(productTypeDto.getName());
        update.setUpdatedAt(LocalDateTime.now());
        return false;
    }

    public boolean delete(Integer id) {
        ProductType productType = getEntity(id);
        productType.setDeletedAt(LocalDateTime.now());
        productTypeRepository.save(productType);
        return true;
    }

    public List<ProductTypeDto> findAllByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<ProductType> resultPage = ProductTypeRepository.findAll(pageable);
        List<ProductTypeDto> response = new ArrayList<>();
        for (ProductType productType : resultPage) {
            if (productType.getDeletedAt() == null){
                ProductTypeDto dto = new ProductTypeDto();
                dto.setId(productType.getId());
                response.add(dto);
            }
        }
        return response;
    }

    public List<ProductTypeDto> filter(ProductTypeFilter productTypeFilter) {
        return null;
    }

    private void convertDtoToEntity(ProductTypeDto productTypeDto, ProductType productType) {

    }

    private void convertEntityToDto(ProductTypeDto productTypeDto, ProductType productType) {

    }

    private ProductType getEntity(Integer id) {
        return null;
    }
}
