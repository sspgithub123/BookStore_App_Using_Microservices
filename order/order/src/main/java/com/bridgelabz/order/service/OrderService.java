package com.bridgelabz.order.service;


import com.bridgelabz.order.dto.OrderDTO;
import com.bridgelabz.order.entity.Book;
import com.bridgelabz.order.entity.Order;
import com.bridgelabz.order.exception.BookException;
import com.bridgelabz.order.exception.OrderException;
import com.bridgelabz.order.exception.UserException;
import com.bridgelabz.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository repo;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Order addOrder(OrderDTO dto) {
        Object user = restTemplate.getForObject("http://localhost:9004/user/findById/" + dto.getUserId(), Object.class);
        if (user.equals(null)) {
            throw new UserException("Invalid user id...");
        } else {
            Book book = restTemplate.getForObject("http://localhost:9001/book/getBook/" + dto.getBookId(), Book.class);
            if (book.equals(null)) {
                throw new BookException("Invalid book id...");
            } else {
                if (dto.getQuantity() > (int) book.getQuantity()) {
                    throw new BookException("Currently we dont have that much books available");
                } else {
                    Order order = new Order(dto);
                    order.setPrice(dto.getQuantity() * book.getPrice());
                    order.setDate(LocalDate.now());
                    repo.save(order);
                    return order;
                }
            }
        }
    }


    @Override
    public List<Order> getAll() {
        List<Order> orders = repo.findAll();
        return orders;
    }

    @Override
    public Order getByID(Integer orderId) {
        Optional<Order> order = repo.findById(orderId);
        if (order.isPresent()) {
            throw new OrderException("Invalid Order Id...");
        }
        return order.get();
    }

    @Override
    public Order updateById(Integer orderId, OrderDTO dto) {
        Optional<Order> order = repo.findById(orderId);
        if (order.isPresent()) {
            throw new OrderException("Invalid Order Id...");
        }
        Object user = restTemplate.getForObject("http://localhost:9004/user/findById/" + dto.getUserId(), Object.class);
        if (user.equals(null)) {
            throw new UserException("Invalid user id...please provide valid user id");
        }
        Book book = restTemplate.getForObject("http://localhost:9001/Book/getBook/" + dto.getBookId(), Book.class);
        if (book.equals(null)) {
            throw new BookException("Invalid book id...please provide valid book id");
        }
        Order newOrder = new Order(dto);
        newOrder.setPrice(dto.getQuantity() * book.getPrice());
        repo.save(newOrder);
        return newOrder;
    }


    @Override
    public Order deleteById(Integer orderId) {
        Optional<Order> order = repo.findById(orderId);
        if (order.isPresent()) {
            throw new OrderException("Invalid Order Id...");
        }
        repo.delete(order.get());
        return order.get();
    }
}
