package com.nexus.ecommerce.controller;

import com.nexus.ecommerce.model.Category;
import com.nexus.ecommerce.model.Product;
import com.nexus.ecommerce.repository.CategoryRepository;
import com.nexus.ecommerce.service.CartService;
import com.nexus.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ShopController {

    @Autowired private ProductService productService;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private CartService cartService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("featuredProducts", productService.findTopRated().stream().limit(8).toList());
        model.addAttribute("newProducts", productService.findNewest().stream().limit(8).toList());
        model.addAttribute("cartCount", cartService.getTotalItems());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(@RequestParam(required = false) String category,
                       @RequestParam(required = false) String tag,
                       @RequestParam(required = false) String q,
                       @RequestParam(required = false) String sort,
                       Model model) {

        List<Product> products;
        String pageTitle = "All Products";

        if (q != null && !q.isBlank()) {
            products = productService.search(q);
            pageTitle = "Search: " + q;
        } else if (category != null && !category.isBlank()) {
            products = productService.findByCategory(category);
            Category cat = categoryRepository.findBySlug(category).orElse(null);
            pageTitle = cat != null ? cat.getName() : "Category";
        } else if (tag != null && !tag.isBlank()) {
            products = productService.findByTag(tag);
            pageTitle = tag.substring(0,1).toUpperCase() + tag.substring(1);
        } else {
            products = productService.findAll();
        }

        // Sort
        if ("price-asc".equals(sort)) {
            products = products.stream().sorted((a, b) -> a.getPrice().compareTo(b.getPrice())).toList();
        } else if ("price-desc".equals(sort)) {
            products = products.stream().sorted((a, b) -> b.getPrice().compareTo(a.getPrice())).toList();
        } else if ("rating".equals(sort)) {
            products = products.stream().sorted((a, b) -> Double.compare(b.getRating(), a.getRating())).toList();
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("searchQuery", q);
        model.addAttribute("currentSort", sort);
        model.addAttribute("cartCount", cartService.getTotalItems());
        return "shop/shop";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        List<Product> related = productService.findByCategory(product.getCategory().getSlug())
            .stream().filter(p -> !p.getId().equals(id)).limit(4).toList();

        model.addAttribute("product", product);
        model.addAttribute("related", related);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cartCount", cartService.getTotalItems());
        return "shop/product-detail";
    }
}
