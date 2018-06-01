/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
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
import ssm.dto.AccountDTO;

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

    public int checkLogin(String username, String password) {
        int roleId = 0;
        try {
            String sql = "select ar.roleId from Account a, AccountRole ar where a.userId = ar.userId "
                    + "and a.email = ? and a.password = ?";
            conn = DBConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
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

    public AccountDTO find(String search) {
        AccountDTO dto = null;
        try {
            String sql = "select email, userName, password from Account where email = ?";
            conn = DBConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, search);
            rs = preStm.executeQuery();
            String email = "", username = "", password = "";
            if (rs.next()) {
                email = rs.getString("email");
                username = rs.getString("userName");
                password = rs.getString("password");
                dto = new AccountDTO(username, password, email);
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
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
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
    
}
