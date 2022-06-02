package com.product.application.controller.hasanboy;

import com.product.application.dto.hasanboy.UserTypeDto;
import com.product.application.service.hasanboy.UserTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/user-type")
public class UserTypeController {
    private UserTypeService userTypeService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid UserTypeDto dto){
        boolean result = userTypeService.create(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        UserTypeDto result = userTypeService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UserTypeDto dto,
                                    @PathVariable("id") Integer id){
        boolean result = userTypeService.update(id,dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = userTypeService.delete(id);
        return ResponseEntity.ok(result);
    }
}
