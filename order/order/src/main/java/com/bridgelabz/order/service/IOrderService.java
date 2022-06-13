package com.bridgelabz.order.service;



import com.bridgelabz.order.dto.OrderDTO;
import com.bridgelabz.order.entity.Order;

import java.util.List;

public interface IOrderService {

    Order addOrder(OrderDTO dto);

    Order getByID(Integer orderId);

    List<Order> getAll();

    Order updateById(Integer orderId,OrderDTO dto);

    Order deleteById(Integer orderId);

}
