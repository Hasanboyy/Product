package com.product.application.service.odamboy;


import com.product.application.dto.odamboy.OrderDto;
import com.product.application.exception.OrderException;
import com.product.application.model.odamboy.Order;
import com.product.application.repository.odamboy.OrderRepository;
import com.product.application.service.hasanboy.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor

public class OrderService {

    private OrderRepository orderRepository;

    private UserService userService;

    public Order create (OrderDto orderDto){
        Order order=new Order();
        order.setId(orderDto.getId());
        userService.getEntity(orderDto.getUserId());
        convertEntityToDto(orderDto,  order);
        order.setCreatedAt(LocalDateTime.now());
        orderRepository.save(order);
        return order;
    }

    public OrderDto get(Integer id){
        Order order=getEntity(id);
        OrderDto orderDto= new OrderDto();
        convertDtoToEntity(orderDto, order);
        return orderDto;
    }

    public boolean update(Integer id, OrderDto orderDto){
        Order update= getEntity(id);
        update.setId(orderDto.getId());
        userService.getEntity(orderDto.getUserId());
        update.setAddress(orderDto.getAddress());
        update.setRequirement(orderDto.getRequirement());
        update.setTotalPayment(orderDto.getTotalPayment());
        update.setUserId(orderDto.getUserId());
        update.setStatus(true);
        update.setUpdateAt(LocalDateTime.now());
        orderRepository.save(update);
        return true;
    }

    public boolean delete(Integer id){
        Order order =getEntity(id);
        order.setDeletedAt(LocalDateTime.now());
        orderRepository.save(order);
        return true;
    }

    public Order getEntity(Integer id){
        Optional<Order>optional =orderRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw  new OrderException("User not found");
        }
        return optional.get();
    }
    public void convertDtoToEntity(OrderDto orderDto, Order order){
        order.setContact(orderDto.getContact());
        order.setAddress(orderDto.getAddress());
        order.setRequirement(orderDto.getRequirement());
        order.setTotalPayment(orderDto.getTotalPayment());
        order.setStatus(true);


    }
    public void convertEntityToDto(OrderDto orderDto, Order order){
        orderDto.setContact(order.getContact());
        orderDto.setAddress(order.getAddress());
        orderDto.setRequirement(order.getRequirement());
        orderDto.setTotalPayment(order.getTotalPayment());
    }


}
