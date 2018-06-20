/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietpt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ssm.db.DBConnection;
import kietpt.dto.CategoryDTO;
import kietpt.dto.OrderDTO;
import kietpt.dto.ProductDTO;

/**
 *
 * @author kietp
 */
public class ProductDAO {

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

    public List<CategoryDTO> getListCategory() {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<CategoryDTO> listCate = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            String sql = "Select * From Category ";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            String name = "", status = "", urlPic = "";
            int id;
            while (rs.next()) {
                id = rs.getInt("categoryId");
                name = rs.getString("categoryName");
                status = rs.getString("status");
                urlPic = rs.getString("imgPic");
                
                CategoryDTO dto = new CategoryDTO();
                dto.setCateId(id);
                dto.setCateName(name);
                dto.setStatus(status);
                dto.setImgPic(urlPic);
                listCate.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return listCate;
    }

    public List<CategoryDTO> getListCategoryByName(String search) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<CategoryDTO> listCate = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            String sql = "Select * From Category where categoryName LIKE ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            String name = "", status = "", urlPic = "";
            int id;
            while (rs.next()) {
                id = rs.getInt("categoryId");
                name = rs.getString("categoryName");
                status = rs.getString("status");
                urlPic = rs.getString("imgPic");
                CategoryDTO dto = new CategoryDTO();
                dto.setCateId(id);
                dto.setCateName(name);
                dto.setStatus(status);
                dto.setImgPic(urlPic);
                listCate.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return listCate;
    }

    public List<ProductDTO> getListCusHotProduct() {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select top 6 pro.productName, pro.productId,img.imgKey,pro.price,pro.description,pro.productKey,"
                    + "pro.manufacturer,pro.manuDate,pro.expiredDate "
                    + "from Product pro, Images img "
                    + "where pro.productId = img.productId "
                    + "order by pro.productId desc";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            String name = "", des = "", urlPic = "", productKey = "", manufacture = "";
            int id;
            Date manuDate, expiredDate;
            float price;
            while (rs.next()) {
                id = rs.getInt("productId");
                name = rs.getString("productName");
                des = rs.getString("description");
                urlPic = rs.getString("imgKey");
                price = rs.getFloat("price");
                productKey = rs.getString("productKey");
                manufacture = rs.getString("manufacturer");
                manuDate = rs.getDate("manuDate");
                expiredDate = rs.getDate("expiredDate");
                ProductDTO dto = new ProductDTO();
                dto.setProductId(id);
                dto.setProductName(name);
                dto.setDescription(des);
                dto.setUrlPic(urlPic);
                dto.setPrice(price);
                dto.setProductKey(productKey);
                dto.setManufacturer(manufacture);
                dto.setManuDate(manuDate);
                dto.setExpiredDate(expiredDate);
                listProduct.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return listProduct;
    }

    public List<ProductDTO> getListProductByCateId(int id) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select pro.productId,pro.productKey,pro.productName,pro.price,pro.description, img.imgKey,"
                    + "pro.manufacturer,pro.manuDate,pro.expiredDate "
                    + "from Product pro, Images img "
                    + "where pro.categoryId = ? and pro.productId = img.productId ";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            String name = "", des = "", urlPic = "", productKey = "", manufacture = "";
            int proId;
            Date manuDate, expiredDate;
            float price;
            while (rs.next()) {
                proId = rs.getInt("productId");
                name = rs.getString("productName");
                des = rs.getString("description");
                urlPic = rs.getString("imgKey");
                price = rs.getFloat("price");
                productKey = rs.getString("productKey");
                manufacture = rs.getString("manufacturer");
                manuDate = rs.getDate("manuDate");
                expiredDate = rs.getDate("expiredDate");
                ProductDTO dto = new ProductDTO();
                dto.setProductId(id);
                dto.setProductName(name);
                dto.setDescription(des);
                dto.setUrlPic(urlPic);
                dto.setPrice(price);
                dto.setProductKey(productKey);
                dto.setManufacturer(manufacture);
                dto.setManuDate(manuDate);
                dto.setExpiredDate(expiredDate);
                listProduct.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return listProduct;
    }

    public List<ProductDTO> getListProduct() {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select productId,productKey,quantity,price from Product where status = active";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            String productKey = "";
            int proId, quan;

            float price;
            while (rs.next()) {
                proId = rs.getInt("productId");
                productKey = rs.getString("productKey");
                quan = rs.getInt("quantity");
                price = rs.getFloat("price");
                ProductDTO dto = new ProductDTO();
                dto.setProductId(proId);
                dto.setProductKey(productKey);
                dto.setQuantity(quan);
                dto.setPrice(price);
                listProduct.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return listProduct;
    }

    public int insertOrder(OrderDTO dto) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "insert into Orders values(?,?,?,?,?,?,?,?)";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, dto.getOrderCode());
                preStm.setDate(2, (java.sql.Date) dto.getStartTime());
                //preStm.setDate(0, x);
                preStm.setString(3, "pending");
            
            int row = preStm.executeUpdate();
            if (row > 0) {
                //return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return 1;
    }

    public boolean insertOrderDetail(int orderId, List<ProductDTO> listProduct) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "insert into Orders_Product values(?,?,?)";
            for (int i = 0; i < listProduct.size(); i++) {
                preStm = conn.prepareStatement(sql);
                preStm.setInt(1, orderId);
                preStm.setInt(2, listProduct.get(i).getProductId());
                preStm.setString(3, "pending");
            }
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

}
