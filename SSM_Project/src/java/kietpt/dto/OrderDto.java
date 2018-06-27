/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietpt.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author kietp
 */
public class OrderDto implements Serializable {

    private int orderId;
    private String orderCode;
    private Timestamp startTime;
    private Timestamp updateTime;
    private Timestamp paymentTime;
    private String addressShip;
    private float totalPrice;
    private int cashierId;
    private int customerId;
    private String status;
    private int totalQuantity;
    
    

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public OrderDto() {
    }

    public OrderDto(int orderId, String orderCode, Timestamp startTime, Timestamp updateTime, Timestamp paymentTime, String addressShip, float totalPrice, int customerId,int cashierId, String status) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.startTime = startTime;
        this.updateTime = updateTime;
        this.paymentTime = paymentTime;
        this.addressShip = addressShip;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.cashierId = cashierId;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Timestamp paymentTime) {
        this.paymentTime = paymentTime;
    }
    public String getAddressShip() {
        return addressShip;
    }

    public void setAddressShip(String addressShip) {
        this.addressShip = addressShip;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

}
