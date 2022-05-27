package com.product.application.controller.odamboy;

import com.product.application.dto.odamboy.OrderDto;
import com.product.application.model.odamboy.Order;
import com.product.application.service.odamboy.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid OrderDto orderDto){
        Order result =orderService.create(orderDto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        OrderDto result =orderService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                @RequestBody @Valid OrderDto orderDto){
        boolean result =orderService.update(id, orderDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result= orderService.delete(id);
        return ResponseEntity.ok(result);
    }
}
