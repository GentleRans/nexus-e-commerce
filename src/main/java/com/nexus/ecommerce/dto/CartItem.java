package com.nexus.ecommerce.dto;

import java.math.BigDecimal;

public class CartItem {
    private Long productId;
    private String name;
    private String emoji;
    private BigDecimal price;
    private Integer quantity;

    public CartItem() {}

    public CartItem(Long productId, String name, String emoji, BigDecimal price, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.emoji = emoji;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getSubtotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
