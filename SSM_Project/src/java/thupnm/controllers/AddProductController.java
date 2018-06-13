package thupnm.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thupnm.dao.CategoryDAO;
import thupnm.dao.ImageDAO;
import thupnm.dao.ProductDAO;
import thupnm.dto.ImageDTO;
import thupnm.dto.ProductDTO;

public class AddProductController extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String url = "";
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
      System.out.println("bbbbbbbbbbb" + categoryName);
      CategoryDAO cateDAO = new CategoryDAO();
      int categoryId = cateDAO.viewCategoryId(categoryName);
      System.out.println("aaaaaaaaaaaaa" + categoryId);
      String picture = request.getParameter("picture");
      System.out.println("ppppppppp " + picture);
      ImageDTO image = new ImageDTO();
      ImageDAO imageDAO = new ImageDAO();

      ProductDAO dao = new ProductDAO();
      ProductDTO dto = new ProductDTO();
      dto.setProductName(productName);
      dto.setCategoryId(categoryId);
      dto.setManufacturer(manu);
      dto.setPrice(Float.parseFloat(price));
      dto.setQuantity(Integer.parseInt(quantity));
      dto.setManuDate(started);
      dto.setExpiredDate(ended);
      dto.setDescription(description);

      if (dao.createNewProduct(dto)) {
        int proId = dto.getProductId();
        System.out.println("ssss " + proId);
        imageDAO.createImage(picture, proId);
        request.setAttribute("RESULT", "Add product successfully!");
        url = "ShowCategoryController";
      } else {
        request.setAttribute("RESULT", "Add product failed!");
        url = "ShowCategoryController";
      }

    } catch (Exception e) {
      log("ERROR at AddProductController " + e.getMessage());
    } finally {
      request.getRequestDispatcher(url).forward(request, response);
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
