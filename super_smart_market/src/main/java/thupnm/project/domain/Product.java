package thupnm.project.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @Column(name = "productId", nullable = false, unique = true)
    private String productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "manuOfDate")
    private LocalDateTime manuOfDate;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name = "categoryId")
    private int categoryId;

    @Column(name = "status")
    private String status;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDateTime getManuOfDate() {
        return manuOfDate;
    }

    public void setManuOfDate(LocalDateTime manuOfDate) {
        this.manuOfDate = manuOfDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
