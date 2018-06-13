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
import thupnm.dao.CategoryDAO;
import thupnm.dto.CategoryDTO;

/**
 *
 * @author ThuPMNSE62369
 */
public class AddAndUpdateCategory extends HttpServlet {

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
            String cateId = request.getParameter("cateId");
            String cateName = request.getParameter("cateName");
            String imgPic = request.getParameter("imgPic");
            boolean checked = false;

            CategoryDAO dao = new CategoryDAO();
            CategoryDTO cate = new CategoryDTO();
            cate.setCategoryName(cateName);
            cate.setImgPic(imgPic);

            if (cateId.isEmpty() || cateId == null) {
                if (dao.createNewCategory(cate)) {
                    request.setAttribute("RESULT", "Add new category successfully!");
                    request.getRequestDispatcher("GetAllCategoriesController").forward(request, response);
                } else {
                    request.setAttribute("RESULT", "Add new category failed!");
                    request.getRequestDispatcher("GetAllCategoriesController").forward(request, response);
                }
            } else {
                if (dao.updateCategory(cate)) {
                    request.setAttribute("RESULT", "Update category successfully!");
                    request.getRequestDispatcher("GetAllCategoriesController").forward(request, response);
                } else {
                    request.setAttribute("RESULT", "Update category failed!");
                    request.getRequestDispatcher("GetAllCategoriesController").forward(request, response);
                }
            }
        } catch (Exception e) {
            log("Error at AddAndUpdateCategoryController " + e.getMessage());
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
