/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thupnm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import ssm.db.DBConnection;
import thupnm.dto.AccountDTO;
import thupnm.dto.AccountRoleDTO;
import thupnm.dto.RoleDTO;

/**
 *
 * @author ThuPMNSE62369
 */
public class AccountDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

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

    public int checkLogin(String email, String password) {
        int roleId = 0;
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
            closeConnection();
        }
        return roleId;
    }

    public AccountDTO find(String mail, String pass) {
        AccountDTO dto = null;
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
                dto = new AccountDTO(userId, username, password, phone, email, gender, address, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }

    public void sendEmail(String host, String port, String toAddress, String message, String subject, String password)
            throws AddressException, MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(toAddress, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(toAddress));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSentDate(new Date());
        msg.setSubject(subject);
        msg.setText(message);
        // sends the e-mail
        Transport.send(msg);
    }

    public String resetPassword() {
        String temp = Long.toHexString(Double.doubleToLongBits(Math.random()));
        return temp;
    }

    public boolean changePassword(String email, String newPassword) {
        try {
            String sql = "UPDATE Account SET password = ? WHERE email = ?";
            conn = DBConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, newPassword);
            preStm.setString(2, email);
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public List<AccountDTO> getAllUser() {
        List<AccountDTO> result = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select a.userId, a.userName, a.email, a.gender, r.roleName, a.status, ar.roleId "
                    + "from Account a, AccountRole ar, Role r "
                    + "where a.userId = ar.userId and r.roleId = ar.roleId";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                AccountDTO dto = new AccountDTO();
                dto.setUserId(rs.getInt("userId"));
                dto.setUsername(rs.getString("userName"));
                dto.setEmail(rs.getString("email"));
                dto.setGender(rs.getString("gender"));
                dto.setRole(rs.getString("roleName"));
                dto.setStatus(rs.getString("status"));
                dto.setRoleId(rs.getInt("roleId"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<AccountDTO> findByRole(String role) {
        List<AccountDTO> result = new ArrayList<>();
        AccountDTO dto = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select a.userId, a.userName, a.email, a.gender, r.roleName, a.status "
                    + "from Account a, AccountRole ar, Role r "
                    + "where a.userId = ar.userId and r.roleId = ar.roleId and r.roleName = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, role);
            rs = preStm.executeQuery();
            String email = "", username = "", gender = "", phone = "", password = "", address = "", status = "", roleName = "";
            int userId;
            Date dob;
            while (rs.next()) {
                userId = rs.getInt("userId");
                email = rs.getString("email");
                username = rs.getString("userName");
                gender = rs.getString("gender");
                phone = rs.getString("phone");
                password = rs.getString("password");
                address = rs.getString("address");
                status = rs.getString("status");
                roleName = rs.getString("roleName");
                dto = new AccountDTO(userId, username, password, phone, email, gender, address, status, role);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateBasicInfo(AccountDTO account) {
        try {
            conn = DBConnection.getConnection();
            String sql = "update Account set email = ?, userName = ?, phone = ?, "
                    + "gender = ?, address = ?, status = ? where userId = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, account.getEmail());
            preStm.setString(2, account.getUsername());
            preStm.setString(3, account.getPhone());
            preStm.setString(4, account.getGender());
            preStm.setString(5, account.getAddress());
            preStm.setString(6, account.getStatus());
            preStm.setInt(7, account.getUserId());
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public AccountDTO viewInfoAccount(int id, int roleId) {
        AccountDTO account = new AccountDTO();
        try {
            conn = DBConnection.getConnection();
            String sql = "select a.email, a.userName, a.phone, a.gender, a.address, a.status "
                    + "from Account a, AccountRole ar where a.userId = ar.userId and a.userId = ? and ar.roleId = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            preStm.setInt(2, roleId);
            rs = preStm.executeQuery();
            if (rs.next()) {
                account.setEmail(rs.getString("email"));
                account.setUsername(rs.getString("userName"));
                account.setPhone(rs.getString("phone").trim());
                account.setGender(rs.getString("gender"));
                account.setAddress(rs.getString("address"));
                account.setStatus(rs.getString("status"));
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public RoleDTO viewRole(int id, int roleId) {
        RoleDTO role = new RoleDTO();
        try {
            conn = DBConnection.getConnection();
            String sql = "select r.roleName from AccountRole ar, Role r where ar.roleId = r.roleId and ar.userId = ? and r.roleId = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            preStm.setInt(2, roleId);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role.setRoleName(rs.getString("roleName"));
                return role;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean addNewUser(AccountDTO account) {
        boolean checked = false;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO Account(email, userName, gender, address, phone, password, status) VALUES(?, ?, ?, ?, ?, ?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, account.getEmail());
            preStm.setString(2, account.getUsername());
            preStm.setString(3, account.getGender());
            preStm.setString(4, account.getAddress());
            preStm.setString(5, account.getPhone());
            preStm.setString(6, account.getPassword());
            preStm.setString(7, AccountDTO.STATUS_ACTIVE);
            checked = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checked;
    }

    public boolean checkEmail(String email) {
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
            closeConnection();
        }
        return check;
    }

    public boolean addRole(int userId) {
        boolean checked = false;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into AccountRole(userId, roleId, status) values(?, ?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, userId);
            preStm.setInt(2, 2);
            preStm.setString(3, AccountDTO.STATUS_ACTIVE);
            checked = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checked;
    }

    public boolean addRoleAdmin(int userId) {
        boolean checked = false;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into AccountRole(userId, roleId, status) values(?, ?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, userId);
            preStm.setInt(2, 1);
            preStm.setString(3, AccountDTO.STATUS_ACTIVE);
            checked = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checked;
    }

    public int getUserIdByEmail(String email) {
        int userId = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "select userId from Account where email = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            rs = preStm.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("userId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return userId;
    }

    public List<AccountDTO> findByLikeUsername(String search) {
        List<AccountDTO> result = new ArrayList<>();
        try {
            String sql = "select a.userId, a.userName, a.email, a.gender, r.roleName, a.status, ar.roleId "
                    + "from Account a, AccountRole ar, Role r "
                    + "where a.userId = ar.userId and r.roleId = ar.roleId and a.userName like ?";
            conn = DBConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            int userId, roleId;
            String username = "", email = "", gender = "", roleName = "", status = "";
            while (rs.next()) {
                userId = rs.getInt("userId");
                username = rs.getString("userName");
                email = rs.getString("email");
                gender = rs.getString("gender");
                roleName = rs.getString("roleName");
                status = rs.getString("status");
                roleId = rs.getInt("roleId");
                AccountDTO dto = new AccountDTO();
                dto.setUserId(userId);
                dto.setUsername(username);
                dto.setEmail(email);
                dto.setGender(gender);
                dto.setRole(roleName);
                dto.setStatus(status);
                dto.setRoleId(roleId);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean changePassword(int id, String newPassword) {
        try {
            conn = DBConnection.getConnection();
            String sql = "update Account set password = ? where userId = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, newPassword);
            preStm.setInt(2, id);
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public AccountDTO findInfo(int userId) {
        AccountDTO dto = null;
        try {
            String sql = "select password from Account where userId = ?";
            conn = DBConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, userId);
            rs = preStm.executeQuery();
            String password = "";
            if (rs.next()) {
                password = rs.getString("password");
                dto = new AccountDTO(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean addNewAdmin(AccountDTO account) {
        boolean checked = false;
        try {
            conn = DBConnection.getConnection();
            String sql = "insert into Account(email, userName, password, status) values(?, ?, ?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, account.getEmail());
            preStm.setString(2, account.getUsername());
            preStm.setString(3, account.getPassword());
            preStm.setString(4, AccountDTO.STATUS_ACTIVE);
            checked = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checked;
    }
    
    public boolean banOrActiveAccount(int id, String status) {
        boolean checked = false;
        try {
            conn = DBConnection.getConnection();
            String sql = "UPDATE Account SET status = ? WHERE userId = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, status);
            preStm.setInt(2, id);
            checked = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checked;
    }
}
