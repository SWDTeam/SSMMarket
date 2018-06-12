/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thupnm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ssm.db.DBConnection;
import thupnm.dto.AccountDTO;
import thupnm.dto.CategoryDTO;

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

    /**
     * 
     * @param categoryName
     * @return 
     */
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

    public List<CategoryDTO> showAllCategory() {
        List<CategoryDTO> result = null;
        try {
            String sql = "select categoryId, categoryName, status from Category";
            conn = DBConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            CategoryDTO cate = null;
            result = new ArrayList<>();
            while (rs.next()) {
                cate = new CategoryDTO();
                cate.setCategoryId(rs.getInt("categoryId"));
                cate.setCategoryName(rs.getString("categoryName"));
                cate.setStatus(rs.getString("status"));
                result.add(cate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public int getProductCount(int cateId) {
        int productCount = 0;
        try {
            String sql = "SELECT count(Product.productId) as productCount " +
                       "FROM (Category c INNER JOIN Product ON c.categoryId = Product.categoryId) " +
                       "group by c.categoryId having c.categoryId = ?";
            conn = DBConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, cateId);
            rs = preStm.executeQuery();
            if (rs.next()) {
                productCount = rs.getInt("productCount");
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return productCount;
    }
    
    public boolean createNewCategory(CategoryDTO category) {
        boolean checked = false;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into Category(categoryName, status, imgPic) values (?, ?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, category.getCategoryName());
            preStm.setString(2, CategoryDTO.STATUS_ACTIVE);
            preStm.setString(3, category.getImgPic());
            checked = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checked;
    }
    
    public CategoryDTO viewInfoCategory(int id) {
        CategoryDTO category = new CategoryDTO();
        try {
            conn = DBConnection.getConnection();
            String sql = "select categoryName, status, imgPic from Category where categoryId = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                category.setCategoryName(rs.getString("categoryName"));
                category.setStatus(rs.getString("status"));
                category.setImgPic(rs.getString("imgPic"));
                return category;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }
}
