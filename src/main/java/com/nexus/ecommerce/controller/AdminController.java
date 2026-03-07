package com.nexus.ecommerce.controller;

import com.nexus.ecommerce.model.Order;
import com.nexus.ecommerce.model.Product;
import com.nexus.ecommerce.repository.CategoryRepository;
import com.nexus.ecommerce.service.CartService;
import com.nexus.ecommerce.service.OrderService;
import com.nexus.ecommerce.service.ProductService;
import com.nexus.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private ProductService productService;
    @Autowired private OrderService orderService;
    @Autowired private UserService userService;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private CartService cartService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("productCount", productService.count());
        model.addAttribute("orderCount", orderService.count());
        model.addAttribute("userCount", userService.count());
        model.addAttribute("revenue", orderService.totalRevenue());
        model.addAttribute("recentOrders", orderService.findAll().stream().limit(10).toList());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cartCount", cartService.getTotalItems());
        return "admin/dashboard";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cartCount", cartService.getTotalItems());
        return "admin/products";
    }

    @GetMapping("/products/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cartCount", cartService.getTotalItems());
        return "admin/product-form";
    }

    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam Long categoryId) {
        categoryRepository.findById(categoryId).ifPresent(product::setCategory);
        productService.save(product);
        return "redirect:/admin/products?saved=true";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cartCount", cartService.getTotalItems());
        return "admin/product-form";
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/admin/products?deleted=true";
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cartCount", cartService.getTotalItems());
        return "admin/orders";
    }

    @PostMapping("/orders/{id}/status")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        orderService.updateStatus(id, Order.Status.valueOf(status));
        return "redirect:/admin/orders?updated=true";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cartCount", cartService.getTotalItems());
        return "admin/users";
    }
}
