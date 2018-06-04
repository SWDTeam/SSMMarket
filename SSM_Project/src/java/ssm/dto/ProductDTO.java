/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssm.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author ThuPMNSE62369
 */
public class ProductDTO implements Serializable {
    
    public static final String STATUS_ACTIVE = "active";
    
    private String productId, productName, manufacturer, description, status;
    private int quantity, categoryId;
    private Timestamp manuDate, expiredDate;
    private float price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Timestamp getManuDate() {
        return manuDate;
    }

    public void setManuDate(Timestamp manuDate) {
        this.manuDate = manuDate;
    }

    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
}
