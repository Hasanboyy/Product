package com.product.application.controller.azamat;

import com.product.application.dto.azamat.ProductDto;
import com.product.application.filter.azamat.ProductFilter;
import com.product.application.service.azamat.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ProductDto productDto){
        boolean result = productService.create(productDto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        ProductDto result = productService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid ProductDto productDto){
        boolean result = productService.update(id, productDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = productService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> getAll(@RequestParam("s") Integer size,
                                    @RequestParam("p") Integer page){
        List<ProductDto> result = productService.findAllByPage(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody ProductFilter productFilter){
        List<ProductDto> result = productService.filter(productFilter);
        return ResponseEntity.ok(result);
    }
}
