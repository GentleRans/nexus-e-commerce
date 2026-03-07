package com.nexus.ecommerce.controller;

import com.nexus.ecommerce.model.Product;
import com.nexus.ecommerce.repository.CategoryRepository;
import com.nexus.ecommerce.service.CartService;
import com.nexus.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired private CartService cartService;
    @Autowired private ProductService productService;
    @Autowired private CategoryRepository categoryRepository;

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("total", cartService.getTotalPrice());
        model.addAttribute("cartCount", cartService.getTotalItems());
        model.addAttribute("categories", categoryRepository.findAll());
        return "shop/cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            @RequestParam(defaultValue = "/shop") String redirect) {
        Product product = productService.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        cartService.addItem(product, quantity);
        return "redirect:" + redirect;
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.updateQuantity(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long productId) {
        cartService.removeItem(productId);
        return "redirect:/cart";
    }
}
