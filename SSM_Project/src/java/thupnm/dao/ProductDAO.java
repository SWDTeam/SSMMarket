/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thupnm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ssm.db.DBConnection;
import thupnm.dto.ProductDTO;

/**
 *
 * @author ThuPMNSE62369
 */
public class ProductDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public ProductDAO() {
    }

    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createNewProduct(ProductDTO product) {
        boolean checked = false;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into Product(productkey, productName, categoryId, manufacturer, price, quantity, " +
                         "manuDate, expiredDate, description, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, product.getProductKey());
            preStm.setString(2, product.getProductName());
            preStm.setInt(3, product.getCategoryId());
            preStm.setString(4, product.getManufacturer());
            preStm.setFloat(5, product.getPrice());
            preStm.setInt(6, product.getQuantity());
            Date manuDate = new Date(product.getManuDate().getTime());
            preStm.setDate(7, manuDate);
            Date expiredDate = new Date(product.getExpiredDate().getTime());
            preStm.setDate(8, expiredDate);
            preStm.setString(9, product.getDescription());
            preStm.setString(10, ProductDTO.STATUS_ACTIVE);
            checked = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checked;
    }

    public int viewProductId(String productKey) {
        int productId = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "select productId from Product where productKey = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, productKey);
            rs = preStm.executeQuery();
            if (rs.next()) {
                productId = rs.getInt("productId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return productId;
    }
    
    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> result = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select productId, productName, price, quantity, status from Product";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<ProductDTO>();
            while (rs.next()) {
                ProductDTO dto = new ProductDTO();
                dto.setProductId(rs.getInt("productId"));
                dto.setProductName(rs.getString("productName"));
                dto.setPrice(rs.getFloat("price"));
                dto.setQuantity(rs.getInt("quantity"));
                dto.setStatus(rs.getString("status"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public ProductDTO showProductDetail(int productId) {
        ProductDTO dto = new ProductDTO();
        try {
            conn = DBConnection.getConnection();
            String sql = "select p.productName, p.description, p.manuDate, p.expiredDate, " +
                         "p.manufacturer, p.price, p.quantity from Product p where p.productId = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, productId);
            rs = preStm.executeQuery();
            if (rs.next()) {
                dto.setProductName(rs.getString("productName"));
                dto.setDescription(rs.getString("description"));
                dto.setManuDate(rs.getDate("manuDate"));
                dto.setExpiredDate(rs.getDate("expiredDate"));
                dto.setManufacturer(rs.getString("manufacturer"));
                dto.setPrice(rs.getFloat("price"));
                dto.setQuantity(rs.getInt("quantity"));
                return dto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }
}
