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
import java.util.List;
import ssm.db.DBConnection;
import ssm.dto.CategoryDTO;

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
}
