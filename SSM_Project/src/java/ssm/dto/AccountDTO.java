/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssm.dto;

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

<<<<<<< HEAD
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
=======
    
    public AccountDTO() {
    }

    public AccountDTO(int userId, String email, String username, String gender, String phone, String password, String address, String status) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.gender = gender;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.status = status;
>>>>>>> kietweb
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

<<<<<<< HEAD
=======
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

>>>>>>> kietweb
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

<<<<<<< HEAD
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

=======
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    

>>>>>>> kietweb
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

<<<<<<< HEAD
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
=======
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
>>>>>>> kietweb
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
<<<<<<< HEAD

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
    
    
=======
    private String status;
>>>>>>> kietweb
}
