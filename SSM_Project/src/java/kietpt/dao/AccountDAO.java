/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietpt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import ssm.db.DBConnection;
import thupnm.dto.AccountDTO;

/**
 *
 * @author kietp
 */
public class AccountDAO {
    
    public void closeConnection(Connection conn, PreparedStatement stm, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int checkLogin(String email, String password) {
        int roleId = 0;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            String sql = "select ar.roleId from Account a, AccountRole ar where a.userId = ar.userId "
                    + "and a.email = ? and a.password = ?";
            conn = DBConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                roleId = rs.getInt("roleId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return roleId;
    }
    
    public AccountDTO find(String mail, String pass) {
        AccountDTO dto = null;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            String sql = "select * from Account where email = ? and password = ?";
            conn = DBConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, mail);
            preStm.setString(2, pass);
            rs = preStm.executeQuery();
            String email = "", username = "", gender = "", phone = "", password = "", address = "", status = "";
            int userId;
            if (rs.next()) {
                userId = rs.getInt("userId");
                email = rs.getString("email");
                username = rs.getString("userName");
                gender = rs.getString("gender");
                phone = rs.getString("phone");
                password = rs.getString("password");
                address = rs.getString("address");
                status = rs.getString("status");
                dto = new AccountDTO();
                dto.setUserId(userId);
                dto.setEmail(email);
                dto.setUsername(username);
                dto.setGender(gender);
                dto.setPhone(phone);
                dto.setPassword(password);
                dto.setAddress(address);
                dto.setStatus(status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return dto;
    }
    
    public boolean insertCustomer(AccountDTO dto) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "Insert into Account values(?,?,?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getEmail());
            preStm.setString(2, dto.getUsername());
            preStm.setString(3, dto.getGender());
            preStm.setString(4, dto.getPhone());
            preStm.setString(5, dto.getPassword());
            preStm.setString(6, dto.getAddress());
            preStm.setString(7, dto.getStatus());
            int row = preStm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return false;
    }
    
    public boolean updateCustomer(AccountDTO dto) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "Update Account set userName = ?,gender = ?, phone = ?, address = ? where email = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getGender());
            preStm.setString(3, dto.getPhone());
            preStm.setString(4, dto.getAddress());
            preStm.setString(5, dto.getEmail());
            int row = preStm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return false;
    }
    
    public boolean changPassCustomer(String email, String password) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "Update Account set password = ? where email = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, password);
            preStm.setString(2, email);
            int row = preStm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return false;
    }

    public boolean checkPassword(String email, String password) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "Select password from Account where email = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            rs = preStm.executeQuery();
            String pass = "";
            if (rs.next()) {
                pass = rs.getString("password");
                if (password.equals(pass)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return false;
    }
    
    public boolean checkEmail(String email) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBConnection.getConnection();
            String sql = "Select email from Account where email = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            rs = preStm.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return check;
    }
    
    public boolean addRole(int userId) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        boolean checked = false;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into AccountRole(userId, roleId, status) values(?, ?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, userId);
            preStm.setInt(2, 2);
            preStm.setString(3, "active");
            checked = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return checked;
    }
    
    public int getUserIdByEmail(String email) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        int userId = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "select userId from Account where email = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            rs = preStm.executeQuery();
            
            if (rs.next()) {
                userId = rs.getInt("userId");
                System.out.println("da vao day");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return userId;
    }
}
