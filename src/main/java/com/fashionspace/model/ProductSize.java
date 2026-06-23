package com.fashionspace.model;

public class ProductSize {

    private int productSizeId;
    private int productId;
    private String sizeLabel;
    private int stockQuantity;
    private double unitPrice;
    private boolean isAvailable;

    public ProductSize() {
    }

    public ProductSize(int productSizeId, int productId,
                       String sizeLabel, int stockQuantity,
                       double unitPrice, boolean isAvailable) {

        this.productSizeId = productSizeId;
        this.productId = productId;
        this.sizeLabel = sizeLabel;
        this.stockQuantity = stockQuantity;
        this.unitPrice = unitPrice;
        this.isAvailable = isAvailable;
    }

    public int getProductSizeId() {
        return productSizeId;
    }

    public void setProductSizeId(int productSizeId) {
        this.productSizeId = productSizeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSizeLabel() {
        return sizeLabel;
    }

    public void setSizeLabel(String sizeLabel) {
        this.sizeLabel = sizeLabel;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}