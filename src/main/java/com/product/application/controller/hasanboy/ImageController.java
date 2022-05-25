package com.product.application.controller.hasanboy;

import com.product.application.dto.hasanboy.ImageDto;
import com.product.application.filter.hasanboy.ImageFilter;
import com.product.application.service.hasanboy.ImageService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class ImageController {
    ImageService imageService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ImageDto dto){
        boolean result = imageService.create(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        ImageDto result = imageService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid ImageDto dto){
        boolean result = imageService.update(id,dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = imageService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> pagenation(@RequestParam("p") Integer page,
                                        @RequestParam("s") Integer size){
        List<ImageDto> result = imageService.findAllByPage(page,size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody ImageFilter imageFilter){
        List<ImageDto> result = imageService.filter(imageFilter);
        return ResponseEntity.ok(result);
    }
}

