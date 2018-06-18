package thupnm.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
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
import thupnm.dao.ImageDAO;
import thupnm.dao.ProductDAO;
import thupnm.dto.ImageDTO;
import thupnm.dto.ProductDTO;

@MultipartConfig
public class AddProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String productName = request.getParameter("productName");
            String categoryName = request.getParameter("categoryName");
            String manu = request.getParameter("manufacturer");
            String price = request.getParameter("price");
            String quantity = request.getParameter("quantity");

            String manuDateStr = request.getParameter("manuDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date manuDate = sdf.parse(manuDateStr);
            Timestamp started = new Timestamp(manuDate.getTime());

            String expiredDateStr = request.getParameter("expiredDate");
            SimpleDateFormat sdfExpired = new SimpleDateFormat("yyyy-MM-dd");
            Date expiredDate = sdfExpired.parse(expiredDateStr);
            Timestamp ended = new Timestamp(expiredDate.getTime());

            String description = request.getParameter("editor1");
            CategoryDAO cateDAO = new CategoryDAO();
            int categoryId = cateDAO.viewCategoryId(categoryName);

            Part filePart = request.getPart("picture");

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
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
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

                ImageDTO image = new ImageDTO();
                ImageDAO imageDAO = new ImageDAO();

                ProductDAO dao = new ProductDAO();
                ProductDTO dto = new ProductDTO();
                Random r = new Random();
                dto.setProductKey(String.valueOf(r.nextInt(1000000)));
                dto.setProductName(productName);
                dto.setCategoryId(categoryId);
                dto.setManufacturer(manu);
                dto.setPrice(Float.parseFloat(price));
                dto.setQuantity(Integer.parseInt(quantity));
                dto.setManuDate(started);
                dto.setExpiredDate(ended);
                dto.setDescription(description);
                image.setImgKey(fileName);

                System.out.println("aaaaaaa " + image.getImgKey());
                
                if (dao.createNewProduct(dto)) {
                    int proId = dao.viewProductId(dto.getProductKey());
                    System.out.println("ssss " + proId);
                    imageDAO.createImage(image.getImgKey(), proId);
                    request.setAttribute("RESULT", "Add product successfully!");
                    request.setAttribute("IMG", image.getImgKey());
                    request.getRequestDispatcher("ShowCategoryController").forward(request, response);
                } else {
                    System.out.println("2222222222222222");
                    request.setAttribute("RESULT", "Add product failed!");
                    request.getRequestDispatcher("ShowCategoryController").forward(request, response);
                }
            }
        } catch (Exception e) {
            log("ERROR at AddProductController " + e.getMessage());
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
