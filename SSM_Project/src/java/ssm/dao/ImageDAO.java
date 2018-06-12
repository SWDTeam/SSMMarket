/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssm.dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import ssm.db.DBConnection;
import ssm.dto.ImageDTO;

/**
 *
 * @author ThuPMNSE62369
 */
public class ImageDAO {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public ImageDAO() {
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
    
    public boolean createImage(String img, int productId) {
        boolean checked = false;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into Images(imgKey, productId) values(?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, img);
            preStm.setInt(2, productId); 
            checked = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checked;
    }
}
