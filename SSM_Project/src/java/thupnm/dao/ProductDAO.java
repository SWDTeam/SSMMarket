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
import java.sql.Timestamp;
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
            String sql = "insert into Product(productName, categoryId, manufacturer, price, quantity, " +
                         "manuDate, expiredDate, description, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, product.getProductName());
            preStm.setInt(2, product.getCategoryId());
            preStm.setString(3, product.getManufacturer());
            preStm.setFloat(4, product.getPrice());
            preStm.setInt(5, product.getQuantity());
            Date manuDate = Date.valueOf(product.getManuDate().toString());
            preStm.setDate(6, manuDate);
            Date expiredDate = Date.valueOf(product.getExpiredDate().toString());
            preStm.setDate(7, expiredDate);
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

}
