package com.nexus.ecommerce.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(precision = 10, scale = 2)
    private BigDecimal oldPrice;

    @Column(nullable = false)
    private Integer stock = 100;

    private String emoji;
    private String badge;     // "New", "Sale", "Hot"
    private String tag;       // "new", "sale", "popular"

    @Column(nullable = false)
    private Double rating = 4.5;

    @Column(nullable = false)
    private Integer reviewCount = 0;

    @Column(nullable = false)
    private boolean active = true;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    // ---- Constructors ----
    public Product() {}

    public Product(String name, String description, BigDecimal price, BigDecimal oldPrice,
                   Integer stock, String emoji, String badge, String tag,
                   Double rating, Integer reviewCount, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.oldPrice = oldPrice;
        this.stock = stock;
        this.emoji = emoji;
        this.badge = badge;
        this.tag = tag;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.category = category;
    }

    // ---- Getters & Setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getOldPrice() { return oldPrice; }
    public void setOldPrice(BigDecimal oldPrice) { this.oldPrice = oldPrice; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }

    public String getBadge() { return badge; }
    public void setBadge(String badge) { this.badge = badge; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Integer getReviewCount() { return reviewCount; }
    public void setReviewCount(Integer reviewCount) { this.reviewCount = reviewCount; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    // Utility: calculate discount %
    public Integer getDiscountPercent() {
        if (oldPrice != null && oldPrice.compareTo(BigDecimal.ZERO) > 0) {
            return (int) Math.round((1 - price.doubleValue() / oldPrice.doubleValue()) * 100);
        }
        return null;
    }
}
