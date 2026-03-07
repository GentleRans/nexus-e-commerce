package com.nexus.ecommerce.service;

import com.nexus.ecommerce.dto.CartItem;
import com.nexus.ecommerce.model.*;
import com.nexus.ecommerce.repository.OrderRepository;
import com.nexus.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order placeOrder(User user, List<CartItem> cartItems,
                            String address, String city, String country) {
        Order order = new Order();
        order.setOrderNumber("NXS-" + System.currentTimeMillis());
        order.setUser(user);
        order.setShippingAddress(address);
        order.setCity(city);
        order.setCountry(country);
        order.setStatus(Order.Status.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            Product product = productRepository.findById(cartItem.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + cartItem.getProductId()));

            OrderItem item = new OrderItem(savedOrder, product,
                cartItem.getQuantity(), product.getPrice());
            items.add(item);
            total = total.add(item.getSubtotal());
        }

        savedOrder.setItems(items);
        savedOrder.setTotalAmount(total);
        return orderRepository.save(savedOrder);
    }

    public List<Order> findByUser(User user) {
        return orderRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public List<Order> findAll() {
        return orderRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Order updateStatus(Long id, Order.Status status) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public long count() {
        return orderRepository.count();
    }

    public BigDecimal totalRevenue() {
        return orderRepository.findAll().stream()
            .map(Order::getTotalAmount)
            .filter(Objects::nonNull)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
