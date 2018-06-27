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
import javax.servlet.http.HttpSession;
import kietpt.dao.AccountDao;
import kietpt.dto.AccountDto;
import thupnm.dao.AccountDAO;
import thupnm.dto.AccountDTO;

/**
 *
 * @author kietp
 */
public class LoginCusController extends HttpServlet {

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
        HttpSession session = request.getSession();
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String json = "";
            System.out.println("do vao loginCustomerController " + email + " - " + password);
            AccountDao dao = new AccountDao();
            AccountDTO dto = new AccountDTO();
            dto = dao.find(email, password);
            session.setAttribute("INFO", dto);
            int role = dao.checkLogin(email, password);
            if (role == 1) {
                System.out.println("chuyen sang trang admin");
                session.setAttribute("ROLEADMIN", role);
                request.getRequestDispatcher("index.jsp").forward(request, response);               
            } else if (role == 2) {
                json = new Gson().toJson(dto);
                System.out.println("KIETTT " + json);
                response.getWriter().write(json);                
            } else{
                System.out.println("vào web error ");
                request.setAttribute("INVALID", "Invalid username or password. Please try again!");
                response.getWriter().write("{}");
                request.getRequestDispatcher("login.jsp").forward(request, response);               
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
