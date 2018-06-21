/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thupnm.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
            //Part filePart = request.getPart("imgPic");
            CategoryDAO dao = new CategoryDAO();
            CategoryDTO cate = new CategoryDTO();
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;

            //xu li main image
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            System.out.println("aaa" + isMultiPart);
            if (!isMultiPart) {
            } else {
                try {
                    items = upload.parseRequest(request);
                    System.out.println("1111 " + items.toString());
                } catch (FileUploadException ex) {
                    ex.printStackTrace();
                }
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String fileName = null;
                String realPath = null;
//                FileItem itemImg = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        try {
                            String itemName = item.getName();
                            fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            System.out.println("path " + fileName);
                            realPath = getServletContext().getRealPath("/").substring(0, getServletContext().getRealPath("/").indexOf("build"))
                                    + "web\\img\\" + fileName;
                            System.out.println("Real path " + realPath);
                            File savedFile = new File(realPath);
                            item.write(savedFile);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                cate.setImgPic(fileName);
                System.out.println("aaaaaaa " + cate.getImgPic());

                String cateId = (String) params.get("cateId");
                System.out.println("cateid " + cateId);

                String cateName = (String) params.get("cateName");
                cate.setCategoryName(cateName);
                System.out.println("catename " + cateName);

                if (cateId.isEmpty() || Objects.isNull(cateId)) {
                    if (!dao.checkCategoryName(cateName)) {
                        if (dao.createNewCategory(cate)) {
                            request.setAttribute("RESULT", "Add new category successfully!");                          
                            request.getRequestDispatcher("GetAllCategoriesController").forward(request, response);
                        } else {
                            request.setAttribute("RESULT", "Add new category failed!");
                            request.getRequestDispatcher("GetAllCategoriesController").forward(request, response);
                        }
                    } else {
                        //request.setAttribute("ERRORCATE", "Category name is existed!");
                        String fail = "Category name is existed!";
                        response.sendRedirect("GetAllCategoriesController?ERRORCATE=" + fail);
                        //request.getRequestDispatcher("GetAllCategoriesController").forward(request, response);
                    }
                } else {
                    cate.setCategoryId(Integer.parseInt(cateId));
                    if (dao.updateCategory(cate)) {
                        request.setAttribute("RESULT", "Update category successfully!");
                        //request.setAttribute("IMG", cate.getImgPic());
                        request.getRequestDispatcher("GetAllCategoriesController").forward(request, response);
                    } else {
                        request.setAttribute("RESULT", "Update category failed!");
                        request.getRequestDispatcher("GetAllCategoriesController").forward(request, response);
                    }
                }
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
