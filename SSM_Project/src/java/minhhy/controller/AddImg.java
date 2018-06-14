package minhhy.controller;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhhy.dao.ImageDAO;
import minhhy.dto.ImageDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AddImg extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            ImageDAO dao = new ImageDAO();
            ImageDTO dto = new ImageDTO();
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
                String fileName = null;
                String realPath = null;
                FileItem itemImg = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        try {
                            String itemName = item.getName();
                            if (fileName == null) {
                                fileName = itemName;
                                System.out.println("Path " + fileName);
                                realPath = getServletContext().getRealPath("/") + "img\\" + fileName;
                                System.out.println("Realpath " + realPath);
                                File savedFile = new File(realPath);
                                item.write(savedFile);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                dto.setImgKey(fileName);
                System.out.println("aaaaaaa " + dto.getImgKey());
                if (dao.createImage(dto.getImgKey())) {
                    request.setAttribute("RESULT", "Add img successfully!");
                    request.setAttribute("IMG", dto.getImgKey());
                    request.getRequestDispatcher("show_img.jsp").forward(request, response);
                } else {
                    request.setAttribute("RESULT", "Add img failed!");
                    request.getRequestDispatcher("test_img.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            log("" + e.getMessage());
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
