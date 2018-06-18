/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thupnm.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thupnm.dao.ImageDAO;
import thupnm.dto.ImageDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import thupnm.dao.ProductDAO;
import thupnm.dto.ProductDTO;

/**
 *
 * @author ThuPMNSE62369
 */
public class ShowProductDetail extends HttpServlet {

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
            String productId = request.getParameter("productId");
            System.out.println("ggggggg " + productId);
            ImageDAO dao = new ImageDAO();
            ImageDTO dto = dao.showImage(Integer.parseInt(productId));
            System.out.println("ffffffff " + dto.getImgKey());
            
            ProductDAO product = new ProductDAO();
            ProductDTO productDto = product.showProductDetail(Integer.parseInt(productId));
            List items = null;

            String fileName = dto.getImgKey();
            System.out.println("ddddd" + fileName);
            String realPath = null;
            FileItem itemImg = null;

            System.out.println("bbbbbbbbb");
            try {
                System.out.println("Path " + fileName);
                realPath = getServletContext().getRealPath("/") + "img\\" + fileName;
                System.out.println("Realpath " + realPath);

            } catch (Exception e) {
                e.printStackTrace();
            }
            
            System.out.println("aaaaaaa " + dto.getImgKey());
            request.setAttribute("IMG", dto.getImgKey());
            request.setAttribute("PRODUCT", productDto);
            request.getRequestDispatcher("show_product.jsp").forward(request, response);

        } catch (Exception e) {
            log("ERROR at ShowProductController " + e.getMessage());
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
