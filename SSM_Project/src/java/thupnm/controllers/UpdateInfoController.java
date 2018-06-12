/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thupnm.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thupnm.dao.AccountDAO;
import thupnm.dto.AccountDTO;

/**
 *
 * @author ThuPMNSE62369
 */
public class UpdateInfoController extends HttpServlet {

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
            String email = request.getParameter("email");
            String name = request.getParameter("username");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            String phoneNo = request.getParameter("phoneNumber");
            String status = request.getParameter("status");
           
            AccountDTO dto = new AccountDTO();
            AccountDAO dao = new AccountDAO();
            dto.setUserId(Integer.parseInt(userId.trim()));
            dto.setEmail(email);
            dto.setUsername(name);
            dto.setGender(gender);
            dto.setAddress(address);
            dto.setPhone(phoneNo);
            dto.setStatus(status);

            if (dao.updateBasicInfo(dto)) {
                request.setAttribute("UPDATEBASIC", dto);
                request.setAttribute("RESULT", "Update Successfully!!!");
                request.getRequestDispatcher("UpdateBasicInfo").forward(request, response);
            } else {
                request.setAttribute("UPDATEBASIC", dto);
                request.setAttribute("RESULT", "Update Failed!!!");
                request.getRequestDispatcher("UpdateBasicInfo").forward(request, response);
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
