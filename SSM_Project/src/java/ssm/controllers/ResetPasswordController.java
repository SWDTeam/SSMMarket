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
public class ResetPasswordController extends HttpServlet {

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
            String userId = request.getParameter("userId");
            String oldPass = request.getParameter("oldPassword");
            String newPass = request.getParameter("newPassword");
            String confirm = request.getParameter("confirmPassword");
            boolean check = false;
            if (!confirm.equals(newPass)) {
                request.setAttribute("errorConfirm", "Confirm password must be same as new password");
                check = true;
            }
            AccountDAO dao = new AccountDAO();
            AccountDTO dto = new AccountDTO();
            AccountDTO pass = dao.findInfo(Integer.parseInt(userId.trim()));
            if (!oldPass.equals(pass.getPassword())) {
                request.setAttribute("errorPass", "Wrong password!");
                check = true;
            }
            dto.setPassword(newPass);
            request.setAttribute("RESETPASS", dto);
            if (!check) {
                if (dao.changePassword(Integer.parseInt(userId.trim()), newPass)) {
                    request.setAttribute("RESULT", "Reset Successfully!!!");
                    request.getRequestDispatcher("change_password.jsp").forward(request, response);
                } else {
                    request.setAttribute("RESULT", "Reset Failed!");
                    request.getRequestDispatcher("change_password.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("change_password.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log("Error at ResetPasswordController " + e.getMessage());
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
