package com.campypro.campyprobackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slug;
    private String name;
    private String brand;
    private String category;
    private Double price;
    @Column(length = 1000) // veya daha büyük bir değer
    private String description;
    private Integer stock;

    @ElementCollection
    @JsonIgnoreProperties({"product", "reviews", "cartItems", "orderItems", "images"})
    private List<String> images;

    @ElementCollection
    private List<String> bulletPoints;

    private String fuelType;
    private String powerSource;
    private String maximumEnergyOutput;
    private String material;
    private String dimensions;
    private String upc;
    private String asin;
    private String includedComponents;
    private String itemHeight;
    private String manufactureYear;

    // Review ile ilgili alan ve annotationlar kaldırıldı

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"product", "reviews", "cartItems", "orderItems", "images"})
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"product", "reviews", "cartItems", "orderItems", "images"})
    private List<OrderItem> orderItems;

    public Product() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public java.util.List<String> getImages() { return images; }
    public void setImages(java.util.List<String> images) { this.images = images; }
    public java.util.List<String> getBulletPoints() { return bulletPoints; }
    public void setBulletPoints(java.util.List<String> bulletPoints) { this.bulletPoints = bulletPoints; }
    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public String getPowerSource() { return powerSource; }
    public void setPowerSource(String powerSource) { this.powerSource = powerSource; }
    public String getMaximumEnergyOutput() { return maximumEnergyOutput; }
    public void setMaximumEnergyOutput(String maximumEnergyOutput) { this.maximumEnergyOutput = maximumEnergyOutput; }
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    public String getDimensions() { return dimensions; }
    public void setDimensions(String dimensions) { this.dimensions = dimensions; }
    public String getUpc() { return upc; }
    public void setUpc(String upc) { this.upc = upc; }
    public String getAsin() { return asin; }
    public void setAsin(String asin) { this.asin = asin; }
    public String getIncludedComponents() { return includedComponents; }
    public void setIncludedComponents(String includedComponents) { this.includedComponents = includedComponents; }
    public String getItemHeight() { return itemHeight; }
    public void setItemHeight(String itemHeight) { this.itemHeight = itemHeight; }
    public String getManufactureYear() { return manufactureYear; }
    public void setManufactureYear(String manufactureYear) { this.manufactureYear = manufactureYear; }
    public java.util.List<CartItem> getCartItems() { return cartItems; }
    public void setCartItems(java.util.List<CartItem> cartItems) { this.cartItems = cartItems; }
    public java.util.List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(java.util.List<OrderItem> orderItems) { this.orderItems = orderItems; }
} 