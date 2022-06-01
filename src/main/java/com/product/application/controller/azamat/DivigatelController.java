package com.product.application.controller.azamat;

import com.product.application.dto.azamat.DivigatelDto;
import com.product.application.filter.azamat.BrendFilter;
import com.product.application.filter.azamat.DivigatelFilter;
import com.product.application.service.azamat.DivigatelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/divigatels")
@AllArgsConstructor
public class DivigatelController {

    private final DivigatelService divigatelService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid DivigatelDto divigatelDto){
        boolean result = divigatelService.create(divigatelDto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        DivigatelDto result = divigatelService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid DivigatelDto divigatelDto){
        boolean result = divigatelService.update(id, divigatelDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = divigatelService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> getAll(@RequestParam("s") Integer size,
                                    @RequestParam("p") Integer page){
        List<DivigatelDto> result = divigatelService.findAllByPage(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody DivigatelFilter divigatelFilter){
        List<DivigatelDto> result = divigatelService.filter(divigatelFilter);
        return ResponseEntity.ok(result);
    }
}
