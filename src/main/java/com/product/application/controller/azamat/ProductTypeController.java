package com.product.application.controller.azamat;

import com.product.application.dto.azamat.ProductTypeDto;
import com.product.application.filter.azamat.ProductTypeFilter;
import com.product.application.service.azamat.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/productTypes")
@AllArgsConstructor
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ProductTypeDto productTypeDto){
        boolean result = productTypeService.create(productTypeDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        ProductTypeDto result = productTypeService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid ProductTypeDto productTypeDto){
        boolean result = productTypeService.update(id, productTypeDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = productTypeService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> getAll(@RequestParam("s") Integer size,
                                    @RequestParam("p") Integer page){
        List<ProductTypeDto> result = productTypeService.findAllByPage(page, size);
        return ResponseEntity.ok(result);
    }
}
