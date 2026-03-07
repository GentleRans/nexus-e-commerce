package com.nexus.ecommerce.controller;

import com.nexus.ecommerce.model.Order;
import com.nexus.ecommerce.model.User;
import com.nexus.ecommerce.repository.CategoryRepository;
import com.nexus.ecommerce.service.CartService;
import com.nexus.ecommerce.service.OrderService;
import com.nexus.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private UserService userService;
    @Autowired private CartService cartService;
    @Autowired private CategoryRepository categoryRepository;

    @GetMapping("/checkout")
    public String checkoutPage(Model model,
                               @AuthenticationPrincipal UserDetails userDetails) {
        if (cartService.isEmpty()) return "redirect:/cart";
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("total", cartService.getTotalPrice());
        model.addAttribute("cartCount", cartService.getTotalItems());
        model.addAttribute("categories", categoryRepository.findAll());
        return "shop/checkout";
    }

    @PostMapping("/checkout")
    public String placeOrder(@RequestParam String address,
                             @RequestParam String city,
                             @RequestParam String country,
                             @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = orderService.placeOrder(user, cartService.getItems(), address, city, country);
        cartService.clear();
        return "redirect:/orders/" + order.getId() + "?success=true";
    }

    @GetMapping("/orders")
    public String myOrders(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("orders", orderService.findByUser(user));
        model.addAttribute("cartCount", cartService.getTotalItems());
        model.addAttribute("categories", categoryRepository.findAll());
        return "shop/orders";
    }

    @GetMapping("/orders/{id}")
    public String orderDetail(@PathVariable Long id,
                              @RequestParam(required = false) String success,
                              Model model) {
        Order order = orderService.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        model.addAttribute("order", order);
        model.addAttribute("success", success != null);
        model.addAttribute("cartCount", cartService.getTotalItems());
        model.addAttribute("categories", categoryRepository.findAll());
        return "shop/order-detail";
    }
}
