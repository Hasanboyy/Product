package com.product.application.controller.azamat;

import com.product.application.dto.azamat.BrendDto;
import com.product.application.filter.azamat.BrendFilter;
import com.product.application.service.azamat.BrendService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/brends")
@AllArgsConstructor
public class BrendContoller {
    private final BrendService brendService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid BrendDto brendDto){
        boolean result = brendService.create(brendDto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        BrendDto result = brendService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid BrendDto brendDto){
        boolean result = brendService.update(id, brendDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = brendService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> getAll(@RequestParam("s") Integer size,
                                    @RequestParam("p") Integer page){
        List<BrendDto> result = brendService.findAllByPage(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody BrendFilter brendFilter){
        List<BrendDto> result = brendService.filter(brendFilter);
        return ResponseEntity.ok(result);
    }
}
