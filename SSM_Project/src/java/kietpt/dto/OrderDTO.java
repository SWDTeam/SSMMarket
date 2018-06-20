/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietpt.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author kietp
 */
public class OrderDTO implements Serializable {

    private int orderId;
    private String orderCode;
    private Date startTime;
    private Date updateTime;
    private Date paymentTime;
    private String addressShip;
    private float totalPrice;
    private int userId;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, String orderCode, Date startTime, Date updateTime, Date paymentTime, String addressShip, float totalPrice, int userId, String status) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.startTime = startTime;
        this.updateTime = updateTime;
        this.paymentTime = paymentTime;
        this.addressShip = addressShip;
        this.totalPrice = totalPrice;
        this.userId = userId;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
