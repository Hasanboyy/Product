package com.product.application.controller.azamat;

import com.product.application.dto.azamat.MerchandDto;
import com.product.application.service.azamat.MenrchantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/merchants")
@AllArgsConstructor
public class MerchantController {

    private final MenrchantService menrchantService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid MerchandDto merchandDto){
        boolean result = menrchantService.create(merchandDto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        MerchandDto result = menrchantService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid MerchandDto merchandDto){
        boolean result = menrchantService.update(id, merchandDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = menrchantService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> getAll(@RequestParam("s") Integer size,
                                    @RequestParam("p") Integer page){
        List<MerchandDto> result = menrchantService.findAllByPage(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody MerchandDto merchandDto){
        List<MerchandDto> result = menrchantService.filter(merchandDto);
        return ResponseEntity.ok(result);
    }
}
