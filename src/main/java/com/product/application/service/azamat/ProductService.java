package com.product.application.service.azamat;

import com.product.application.dto.azamat.ProductDto;
import com.product.application.filter.azamat.ProductFilter;
import com.product.application.repository.azamat.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public boolean create(ProductDto productDto) {
        return false;
    }

    public ProductDto get(Integer id) {
        return null;
    }

    public boolean update(Integer id, ProductDto productDto) {
        return false;
    }

    public boolean delete(Integer id) {
        return false;
    }

    public List<ProductDto> findAllByPage(Integer page, Integer size) {
        return null;
    }

    public List<ProductDto> filter(ProductFilter productFilter) {
        return null;
    }
}
