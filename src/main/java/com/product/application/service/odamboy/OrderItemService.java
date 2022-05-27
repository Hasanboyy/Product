package com.product.application.service.odamboy;

import com.product.application.dto.odamboy.OrderItemDto;
import com.product.application.exception.OrderException;
import com.product.application.model.odamboy.OrderItem;
import com.product.application.repository.odamboy.OrderItemRepository;
import com.product.application.service.azamat.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderItemService {

    private OrderItemRepository orderItemRepository;

    private OrderService orderService;

    private ProductService productService;

    public OrderItem create(OrderItemDto orderItemDto){
        OrderItem orderItem=new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setCreatedAt(LocalDateTime.now());
        orderService.getEntity(orderItemDto.getOrderId());
        productService.get(orderItem.getProductId());
        orderItemRepository.save(orderItem);
        convertEntityToDto(orderItemDto, orderItem);
        return orderItem;
    }

    public OrderItemDto get(Integer id){
        OrderItem orderItem=getEntity(id);
        OrderItemDto orderItemDto=new OrderItemDto();
        convertDtoToEntity(orderItemDto, orderItem);
        return orderItemDto;
    }

    public boolean update(Integer id, OrderItemDto orderItemDto){
        OrderItem update=getEntity(id);
        update.setOrderId(orderItemDto.getOrderId());
        update.setProductId(orderItemDto.getProductId());
        update.setStatus(true);
        update.setUpdateAt(LocalDateTime.now());
        orderItemRepository.save(update);
        return true;
    }

    public boolean delete(Integer id){
        OrderItem orderItem=getEntity(id);
        orderItem.setDeletedAt(LocalDateTime.now());
        orderItemRepository.save(orderItem);
        return true;
    }


    public OrderItem getEntity(Integer id){
        Optional<OrderItem>optional= orderItemRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new OrderException("Order item not found");
        }
        return optional.get();
    }

    public void convertDtoToEntity(OrderItemDto orderItemDto, OrderItem orderItem){
        orderItem.setOrderId(orderItemDto.getOrderId());
        orderItem.setProductId(orderItemDto.getProductId());
    }

    public void convertEntityToDto(OrderItemDto orderItemDto, OrderItem orderItem){
        orderItemDto.setOrderId(orderItem.getOrderId());
        orderItemDto.setProductId(orderItem.getProductId());
    }

}
