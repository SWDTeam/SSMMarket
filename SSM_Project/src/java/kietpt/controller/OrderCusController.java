/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kietpt.dao.ProductDAO;
import kietpt.dto.OrderDTO;
import kietpt.dto.ProductDTO;

/**
 *
 * @author kietp
 */
public class OrderCusController extends HttpServlet {

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

            List<ProductDTO> listCart = new ArrayList<>();
            listCart.add(new ProductDTO("D1", 10, 1, 300));
            listCart.add(new ProductDTO("D2", 5, 2, 400));

            ProductDAO dao = new ProductDAO();
            List<ProductDTO> listProduct = dao.getListProduct();
            int afterQuantity = 0;
            int flag = 0;
            for (int i = 0; i < listProduct.size(); i++) {
                for (int j = 0; j < listCart.size(); j++) {
                    if (listCart.get(j).getProductId() == listProduct.get(i).getProductId()) {
                        if (listCart.get(j).getQuantity() <= listProduct.get(i).getQuantity()) {
                            afterQuantity = listCart.get(j).getQuantity() - listProduct.get(i).getQuantity();
                            flag = 1;
                        } else {
                            flag = 0;
                            break;
                        }
                    }
                }
            }
            if (flag == 1) {
                OrderDTO dto = new OrderDTO();
                Random randomCode = new Random();
                dto.setOrderCode(String.valueOf(randomCode.nextInt(1000000)));
                Date date = Calendar.getInstance().getTime();
                System.out.println("Starttime + : "+date);
                dto.setStartTime(date);
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
