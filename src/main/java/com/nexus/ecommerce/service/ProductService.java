package com.nexus.ecommerce.service;

import com.nexus.ecommerce.model.Category;
import com.nexus.ecommerce.model.Product;
import com.nexus.ecommerce.repository.CategoryRepository;
import com.nexus.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll() {
        return productRepository.findByActiveTrue();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByCategory(String slug) {
        Category cat = categoryRepository.findBySlug(slug).orElse(null);
        if (cat == null) return List.of();
        return productRepository.findByCategoryAndActiveTrue(cat);
    }

    public List<Product> search(String query) {
        return productRepository.searchProducts(query);
    }

    public List<Product> findByTag(String tag) {
        return productRepository.findByTagAndActiveTrue(tag);
    }

    public List<Product> findTopRated() {
        return productRepository.findTopRated();
    }

    public List<Product> findNewest() {
        return productRepository.findNewest();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.findById(id).ifPresent(p -> {
            p.setActive(false);
            productRepository.save(p);
        });
    }

    public long count() {
        return productRepository.count();
    }
}
