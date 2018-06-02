/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssm.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ssm.dao.AccountDAO;
import ssm.dto.AccountDTO;

/**
 *
 * @author ThuPMNSE62369
 */
public class RegisterController extends HttpServlet {

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
        try {
            /* TODO output your page here. You may use following sample code. */
            String email = request.getParameter("txtEmail");
            String name = request.getParameter("txtUsername");
            String gender = request.getParameter("txtGender");
            if (gender.equals("male")) {
                gender = "male";
            } else {
                gender = "female";
            }
            String address = request.getParameter("txtAddress");
            String phone = request.getParameter("txtPhone");
            String password = request.getParameter("txtPassword");
            String confirmPassword = request.getParameter("txtConfirmPassword");
            boolean checked = false;
            if (!password.equals(confirmPassword)) {
                request.setAttribute("ERROR", "Confirm password and password must be the same!");
                checked = true;
            }
            AccountDAO dao = new AccountDAO();
            AccountDTO user = new AccountDTO();
            user.setEmail(email);
            user.setUsername(name);
            user.setGender(gender);
            user.setAddress(address);
            user.setPhone(phone);
            user.setPassword(password);

            if (!checked) {
                if (!dao.checkEmail(email)) {
                    if (dao.addNewUser(user)) {
                        PrintWriter out = response.getWriter();
                        out.append("success");
                        int id = dao.getUserIdByEmail(email);
                        user.setUserId(id);
                        boolean result = dao.addRole(user.getUserId());
                    } else {
                        request.setAttribute("ERROR", "Failed!");
                    }
                } else {
                    request.setAttribute("EmailError", "Email is existed!");
                    request.getRequestDispatcher("").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("").forward(request, response);
            }
        } catch (Exception e) {
            log("Error at RegisterController " + e.getMessage());
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
