/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietpt.dao;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ssm.db.DBConnection;
import kietpt.dto.CategoryDto;
import kietpt.dto.OrderDetailDto;
import kietpt.dto.OrderDto;
import kietpt.dto.ProductDto;

/**
 *
 * @author kietp
 */
public class ProductDao {

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

    public List<CategoryDto> getListCategory() {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<CategoryDto> listCate = new ArrayList<>();
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

                CategoryDto dto = new CategoryDto();
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

    public List<CategoryDto> getListCategoryByName(String search) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<CategoryDto> listCate = new ArrayList<>();
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
                CategoryDto dto = new CategoryDto();
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

    public List<ProductDto> getListCusHotProduct() {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<ProductDto> listProduct = new ArrayList<>();
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
                ProductDto dto = new ProductDto();
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

    public List<ProductDto> getListProductByCateId(int id) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<ProductDto> listProduct = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select pro.productId,pro.productKey,pro.productName,pro.price,pro.description, img.imgKey,"
                    + "pro.manufacturer,pro.manuDate,pro.expiredDate,pro.quantity,pro.categoryId "
                    + "from Product pro, Images img "
                    + "where pro.categoryId = ? and pro.productId = img.productId ";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            String name = "", des = "", urlPic = "", productKey = "", manufacture = "";
            int proId, cateId, quantity;
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
                cateId = rs.getInt("categoryId");
                quantity = rs.getInt("quantity");
                ProductDto dto = new ProductDto();
                dto.setProductId(proId);
                dto.setProductName(name);
                dto.setDescription(des);
                dto.setUrlPic(urlPic);
                dto.setPrice(price);
                dto.setProductKey(productKey);
                dto.setManufacturer(manufacture);
                dto.setManuDate(manuDate);
                dto.setExpiredDate(expiredDate);
                dto.setCategoryId(cateId);
                dto.setQuantity(quantity);
                listProduct.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return listProduct;
    }

    public List<ProductDto> getListProduct() {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<ProductDto> listProduct = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select productId,productKey,quantity,price from Product where status = 'active'";
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
                ProductDto dto = new ProductDto();
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

    public String insertOrder(OrderDto dto) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        int orderId = 0;
        String orderCode = "";
        float totalPrice = 0;
        try {
            System.out.println("da vao Insert order ");
            conn = DBConnection.getConnection();
            String sql = "insert into Orders values(?,?,?,?,?,?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getOrderCode());
            preStm.setTimestamp(2, dto.getStartTime());
            preStm.setTimestamp(3, dto.getUpdateTime());
            preStm.setTimestamp(4, dto.getPaymentTime());

            preStm.setString(5, dto.getAddressShip());
            preStm.setFloat(6, dto.getTotalPrice());
            preStm.setInt(7, dto.getCustomerId());
            preStm.setInt(8, dto.getCashierId());
            preStm.setString(9, dto.getStatus());
            preStm.setInt(10, dto.getTotalQuantity());
            int row = preStm.executeUpdate();
            if (row > 0) {
                String sqlOrderId = "select orderId,totalPrice from Orders where orderCode = ? ";
                preStm = conn.prepareStatement(sqlOrderId);
                preStm.setString(1, dto.getOrderCode());
                rs = preStm.executeQuery();
                if (rs.next()) {
                    orderId = rs.getInt("orderId");
                    totalPrice = rs.getFloat("totalPrice");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        System.out.println("da ra Insert order ");
        return orderId + "-" + dto.getOrderCode() + "-" + totalPrice;
    }

    public boolean insertOrderDetail(int orderId, List<ProductDto> listCart) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into Orders_Product values(?,?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            Iterator<ProductDto> it = listCart.iterator();
            while (it.hasNext()) {
                ProductDto p = it.next();
                preStm.setInt(1, orderId);
                preStm.setInt(2, p.getProductId());
                preStm.setFloat(3, p.getPrice());
                preStm.setInt(4, p.getQuantity());
                preStm.setString(5, null);
                preStm.setString(6, "pending");
                preStm.addBatch();
            }
            int[] numUpdates = preStm.executeBatch();
            for (int i = 0; i < numUpdates.length; i++) {
                if (numUpdates[i] == -2) {
                    System.out.println("Execution " + i
                            + ": unknown number of rows inserted");
                } else {
                    System.out.println("Execution " + i
                            + "successful: " + numUpdates[i] + " rows inserted");
                }
            }
            conn.commit();
            return true;
        } catch (BatchUpdateException b) {
            // process BatchUpdateException
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateQuantity(List<ProductDto> listAfterCart) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            String sql = "update Product set quantity = ? where productId = ? ";
            preStm = conn.prepareStatement(sql);
            Iterator<ProductDto> it = listAfterCart.iterator();
            while (it.hasNext()) {
                ProductDto p = it.next();
                preStm.setInt(1, p.getQuantity());
                preStm.setInt(2, p.getProductId());
                preStm.addBatch();
            }
            int[] numUpdates = preStm.executeBatch();
            for (int i = 0; i < numUpdates.length; i++) {
                if (numUpdates[i] == -2) {
                    System.out.println("Execution " + i
                            + ": unknown number of rows updated");
                } else {
                    System.out.println("Execution " + i
                            + "successful: " + numUpdates[i] + " rows updated");
                }
            }
            conn.commit();
            return true;
        } catch (BatchUpdateException b) {
            // process BatchUpdateException
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<OrderDto> getListOrderedPending(int userId) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<OrderDto> listOrdered = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from Orders where customerId = ? and status = 'pending' ";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, userId);
            rs = preStm.executeQuery();
            int orderId, quantity, customerId, cashierId;
            String orderCode = "", status = "";
            Timestamp startDate;
            float price;
            while (rs.next()) {
                orderId = rs.getInt("orderId");
                orderCode = rs.getString("orderCode");
                startDate = rs.getTimestamp("startDate");
                price = rs.getFloat("totalPrice");
                customerId = rs.getInt("customerId");
                status = rs.getString("status");
                quantity = rs.getInt("totalQuantity");
                cashierId = rs.getInt("cashierId");
                OrderDto dto = new OrderDto();
                dto.setOrderId(orderId);
                dto.setOrderCode(orderCode);
                dto.setStartTime(startDate);
                dto.setTotalPrice(price);
                dto.setCustomerId(customerId);
                dto.setStatus(status);
                dto.setTotalQuantity(quantity);
                dto.setCashierId(cashierId);
                listOrdered.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return listOrdered;
    }

    public List<OrderDto> getListOrderedCanceled(int userId) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<OrderDto> listOrdered = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from Orders where customerId = ? and status = 'canceled' ";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, userId);
            rs = preStm.executeQuery();
            int orderId, quantity, customerId;
            String orderCode = "", status = "";
            Timestamp startDate;
            float price;
            while (rs.next()) {
                orderId = rs.getInt("orderId");
                orderCode = rs.getString("orderCode");
                startDate = rs.getTimestamp("startDate");
                price = rs.getFloat("totalPrice");
                customerId = rs.getInt("customerId");
                status = rs.getString("status");
                quantity = rs.getInt("totalQuantity");

                OrderDto dto = new OrderDto();
                dto.setOrderId(orderId);
                dto.setOrderCode(orderCode);
                dto.setStartTime(startDate);
                dto.setTotalPrice(price);
                dto.setCustomerId(customerId);
                dto.setStatus(status);
                dto.setTotalQuantity(quantity);
                listOrdered.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return listOrdered;
    }

    public List<OrderDetailDto> getListOrderDetail(int orderId) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<OrderDetailDto> listOrderDetail = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select orProduct.id,orProduct.productId,orProduct.orderId,pro.productName,"
                    + "orProduct.quantity,orProduct.price,orProduct.status,pro.productKey,img.imgKey "
                    + "from Product pro LEFT OUTER JOIN Orders_Product orProduct "
                    + "on orProduct.productId = pro.productId "
                    + "LEFT OUTER JOIN Images img "
                    + "on img.productId=pro.productId "
                    + "where orProduct.orderId= ? and orProduct.status = 'pending' ";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, orderId);
            rs = preStm.executeQuery();
            int id, orderid, productId, quantity;
            String imgKey = "", status = "", productKey = "", productName = "";
            float price;
            while (rs.next()) {
                id = rs.getInt("id");
                productId = rs.getInt("productId");
                orderid = rs.getInt("orderId");
                productName = rs.getString("productName");
                quantity = rs.getInt("quantity");
                price = rs.getFloat("price");
                status = rs.getString("status");
                productKey = rs.getString("productKey");
                imgKey = rs.getString("imgKey");

                OrderDetailDto dto = new OrderDetailDto();
                dto.setOrderDetailId(id);
                dto.setProductId(productId);
                dto.setOrderId(orderid);
                dto.setProductName(productName);
                dto.setQuantity(quantity);
                dto.setPrice(price);
                dto.setStatus(status);
                dto.setProductKey(productKey);
                dto.setImgKey(imgKey);
                listOrderDetail.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return listOrderDetail;
    }

    public boolean updateOrderConfirm(OrderDto dto) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "update Orders set updateDate = ?, totalPrice = ?,totalQuantity = ? where orderId = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setTimestamp(1, dto.getUpdateTime());
            preStm.setFloat(2, dto.getTotalPrice());
            preStm.setInt(3, dto.getTotalQuantity());
            preStm.setInt(4, dto.getOrderId());
            int checked = preStm.executeUpdate();
            if (checked > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return false;
    }

    public boolean updateOrderDetail(int orderId, List<OrderDetailDto> listConfirm) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            String sql = "update Orders_Product set price = ?, quantity = ? where orderId = ? and id = ? ";
            preStm = conn.prepareStatement(sql);
            Iterator<OrderDetailDto> it = listConfirm.iterator();
            while (it.hasNext()) {
                OrderDetailDto orDetail = it.next();
                preStm.setFloat(1, orDetail.getPrice());
                preStm.setInt(2, orDetail.getQuantity());
                preStm.setInt(3, orderId);
                preStm.setInt(4, orDetail.getOrderDetailId());
                preStm.addBatch();
            }
            int[] numUpdates = preStm.executeBatch();
            for (int i = 0; i < numUpdates.length; i++) {
                if (numUpdates[i] == -2) {
                    System.out.println("Execution " + i
                            + ": unknown number of rows updated");
                } else {
                    System.out.println("Execution " + i
                            + "successful: " + numUpdates[i] + " rows updated");
                }
            }
            conn.commit();
            return true;
        } catch (BatchUpdateException b) {
            // process BatchUpdateException
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<OrderDetailDto> getListOldOrderDetail() {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<OrderDetailDto> list = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            String sql = "Select * from Orders_Product ";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            int id, orderId, productId, quantity;
            float price;
            while (rs.next()) {
                id = rs.getInt("id");
                orderId = rs.getInt("orderId");
                productId = rs.getInt("productId");
                price = rs.getFloat("price");
                quantity = rs.getInt("quantity");

                OrderDetailDto dto = new OrderDetailDto();
                dto.setOrderDetailId(id);
                dto.setOrderId(orderId);
                dto.setProductId(productId);
                dto.setPrice(price);
                dto.setQuantity(quantity);
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return list;
    }

    public ProductDto getProductByBarcode(String barcode) {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        ProductDto dto = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select pro.productId,pro.productKey,pro.productName,pro.manufacturer,"
                    + "pro.manuDate,pro.expiredDate,img.imgKey, "
                    + "pro.price,pro.description "
                    + "from Product pro LEFT OUTER JOIN Images img "
                    + "on pro.productId = img.productId "
                    + "where pro.productKey = ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, barcode);
            rs = preStm.executeQuery();
            int id;
            float price;
            String name = "", productKey = "", manufacture = "", des = "", urlPic = "";
            Date manuDate, expiredDate;
            if (rs.next()) {
                id = rs.getInt("productId");
                name = rs.getString("productName");
                des = rs.getString("description");
                urlPic = rs.getString("imgKey");
                price = rs.getFloat("price");
                productKey = rs.getString("productKey");
                manufacture = rs.getString("manufacturer");
                manuDate = rs.getDate("manuDate");
                expiredDate = rs.getDate("expiredDate");
                dto = new ProductDto();
                dto.setProductId(id);
                dto.setProductName(name);
                dto.setDescription(des);
                dto.setUrlPic(urlPic);
                dto.setPrice(price);
                dto.setProductKey(productKey);
                dto.setManufacturer(manufacture);
                dto.setManuDate(manuDate);
                dto.setExpiredDate(expiredDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, preStm, rs);
        }
        return dto;
    }
}
