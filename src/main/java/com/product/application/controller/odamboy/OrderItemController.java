package com.product.application.controller.odamboy;

import com.product.application.dto.odamboy.OrderItemDto;
import com.product.application.model.odamboy.OrderItem;
import com.product.application.service.odamboy.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orderItems")
@AllArgsConstructor
public class OrderItemController {

    private OrderItemService orderItemService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid OrderItemDto orderItemDto){
        OrderItem result =orderItemService.create(orderItemDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        OrderItem result= orderItemService.getEntity(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid OrderItemDto orderItemDto){
        boolean result = orderItemService.update(id, orderItemDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result= orderItemService.delete(id);
        return ResponseEntity.ok(result);
    }
}
