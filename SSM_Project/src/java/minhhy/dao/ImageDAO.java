package minhhy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import minhhy.dto.ImageDTO;
import ssm.db.DBConnection;

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

    public ImageDTO show(int id) {
        ImageDTO dto = new ImageDTO();
        try {
            conn = DBConnection.getConnection();
            String sql = "select imgKey from Images where imgId =?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
//        dto.setImgId(rs.getInt("imgId"));
                dto.setImgKey(rs.getString("imgKey"));
                return dto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean createImage(String img) {
        boolean checked = false;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into Images(imgKey) values(?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, img);
            checked = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checked;
    }
}
