/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thupnm.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ThuPMNSE62369
 */
public class AccountDTO implements Serializable {
    
    public static final int ROLE_USER = 2;
    public static final String STATUS_ACTIVE = "active";
    
    private int userId, roleId;  
    private String username, password, phone, email, gender, address, status, role;

    public AccountDTO() {
    }

    public AccountDTO(String password) {
        this.password = password;
    }

    public AccountDTO(int roleId) {
        this.roleId = roleId;
    }

    public AccountDTO(int userId, String username, String password, String phone, String email, String gender, String address, String status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.status = status;
    }


    public AccountDTO(int userId, String username, String password, String phone, String email, String gender, String address, String status, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.status = status;
        this.role = role;

        }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
   
}
