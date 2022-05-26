package com.product.application.service.azamat;

import com.product.application.dto.azamat.ProductDto;
import com.product.application.dto.azamat.ProductTypeDto;
import com.product.application.filter.azamat.ProductTypeFilter;
import com.product.application.repository.azamat.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductTypeService {

    private ProductRepository productRepository;
    public boolean create(ProductTypeDto productTypeDto) {
        return false;
    }

    public ProductDto get(Integer id) {
        return null;
    }

    public boolean update(Integer id, ProductTypeDto productTypeDto) {
        return false;
    }

    public boolean delete(Integer id) {
        return false;
    }

    public List<ProductTypeDto> findAllByPage(Integer page, Integer size) {
        return null;
    }

    public List<ProductTypeDto> filter(ProductTypeFilter productTypeFilter) {
        return null;
    }
}
