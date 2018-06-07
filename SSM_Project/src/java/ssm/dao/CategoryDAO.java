/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ssm.db.DBConnection;
import ssm.dto.AccountDTO;
import ssm.dto.CategoryDTO;

/**
 *
 * @author ThuPMNSE62369
 */
public class CategoryDAO {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public CategoryDAO() {
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
    
    public List<CategoryDTO> getCategory() {
        List<CategoryDTO> result = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select categoryId, categoryName from Category";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                CategoryDTO dto = new CategoryDTO();
                dto.setCategoryId(rs.getInt("categoryId"));
                dto.setCategoryName(rs.getString("categoryName"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public int viewCategoryId(String categoryName) {
        int categoryId = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "select categoryId from Category where categoryName = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, categoryName);
            rs = preStm.executeQuery();
            if (rs.next()) {
                categoryId = rs.getInt("categoryId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return categoryId;
    }
}
