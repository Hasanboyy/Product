package com.product.application.controller.hasanboy;

import com.product.application.dto.hasanboy.AddressDto;
import com.product.application.filter.hasanboy.AddressFilter;
import com.product.application.service.hasanboy.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {

    AddressService addressService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid AddressDto dto){
        boolean result = addressService.create(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        AddressDto result = addressService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid AddressDto dto){
        boolean result = addressService.update(id,dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = addressService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pegenation")
    public ResponseEntity<?> getAll(@RequestParam("p") Integer page,
                                    @RequestParam("s") Integer size){
        List<AddressDto> result = addressService.findAllByPage(page,size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody AddressFilter addressFilter){
        List<AddressDto> result = addressService.filter(addressFilter);
        return ResponseEntity.ok(result);
    }


}
