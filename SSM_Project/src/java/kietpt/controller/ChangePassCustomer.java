/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietpt.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kietpt.dao.AccountDAO;
import kietpt.dto.AccountDTO;

/**
 *
 * @author kietp
 */
public class ChangePassCustomer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String json = "";
        try {
            System.out.println(" da vao change Pass Customer Controller ");
            AccountDAO dao = new AccountDAO();
            String email = request.getParameter("txtEmail");
            String oldPass = request.getParameter("txtOldPassword");
            String newPass = request.getParameter("txtNewPassword");
            System.out.println(email + " - " + oldPass + " - " + newPass);
            AccountDTO account = new AccountDTO();
            if (dao.checkPassword(email, oldPass)) {
                if (dao.changPassCustomer(email, newPass)) {
                    account.setEmail(email);
                    account.setPassword(newPass);
                    json = new Gson().toJson(account);
                    System.out.println("chage pass thanh cong " + json);
                    response.getWriter().write(json);
                } else {
                    System.out.println("kiet xau trai");
                    json = new Gson().toJson("{\"result\":\"fail\"}");
                    System.out.println("change pass that bai " + json);
                    response.getWriter().write(json);
                }
            } else {
                System.out.println("kiet xau trai");
                System.out.println("change pass that bai " + json);
                response.getWriter().write("{}");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
