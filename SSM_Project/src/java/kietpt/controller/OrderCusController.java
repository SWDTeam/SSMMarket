/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kietpt.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.parser.JSONParser;
import kietpt.dao.ProductDao;
import kietpt.dto.OrderDto;
import kietpt.dto.ProductDto;

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
        response.setCharacterEncoding("UTF-8");

        try {
            System.out.println("da vao controller OrderCusController");
            String cart = request.getParameter("listCart");
            String userId = request.getParameter("userId");
            String addressShip = request.getParameter("addressShip");
            System.out.println("car = " + cart);

            JsonParser parser = new JsonParser();
            JsonArray jsonArray = (JsonArray) parser.parse(cart);
            List<ProductDto> listCart = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                int productId = jsonObject.get("productId").getAsInt();
                String productKey = jsonObject.get("productKey").getAsString();
                String productName = jsonObject.get("productName").getAsString();
                float productPrice = jsonObject.get("productPrice").getAsFloat();
                int productQuantity = jsonObject.get("productQuantity").getAsInt();
                listCart.add(new ProductDto(productId, productKey, productName, productQuantity, productPrice));
            }
            ProductDao dao = new ProductDao();
            List<ProductDto> listProduct = dao.getListProduct();

            float totalPrice = 0;
            for (int i = 0; i < listCart.size(); i++) {
                totalPrice += listCart.get(i).getPrice();
            }
            int totalQuantity = 0;
            for (int i = 0; i < listCart.size(); i++) {
                totalQuantity += listCart.get(i).getQuantity();
            }
            String json = "";
            List<ProductDto> listafter = new ArrayList<>();
            int flag = 0;
            boolean checked = false;
            for (int i = 0; i < listProduct.size(); i++) {
                for (int j = 0; j < listCart.size(); j++) {
                    if (listCart.get(j).getProductId() == listProduct.get(i).getProductId()) {
                        if (listCart.get(j).getQuantity() > listProduct.get(i).getQuantity()) {
                            flag = 0;
                            checked = true;
                            response.getWriter().append(listCart.get(j).getProductName() + " has only "
                                    + listProduct.get(i).getQuantity() + " in stock.Please decrease quantity of product in your cart!!!" + "/");
                            System.out.println("đã fail khi số lượng vượt quá ");

                        } else {
                            // gửi tin nhắn qua bên mobile vì số lương sản phẩm trong kho không đủ
                            int afterQuantity = listProduct.get(i).getQuantity() - listCart.get(j).getQuantity();
                            System.out.println("quantity after:  " + afterQuantity);
                            listafter.add(new ProductDto(listCart.get(j).getProductId(),
                                    listCart.get(j).getProductKey(), afterQuantity));
                            flag = 1;
                            checked = true;
                        }
                    }
                    System.out.println(" i = " + i + " - " + "j = " + j);
                }
                if (flag == 0 && checked == true) {
                    break;
                }
            }
            // insert vào bảng order 
            if (flag == 1 && checked == true) {
                OrderDto dto = new OrderDto();
                Random randomCode = new Random();
                dto.setOrderCode(String.valueOf(randomCode.nextInt(10000000)));
                Timestamp start = new Timestamp(System.currentTimeMillis());
                System.out.println("Starttime + : " + start);
                dto.setStartTime(start);
                dto.setUpdateTime(null);
                dto.setPaymentTime(null);
                dto.setAddressShip(addressShip);
                dto.setTotalPrice(totalPrice);
                dto.setCustomerId(Integer.parseInt(userId));
                dto.setCashierId(Integer.parseInt(userId));
                dto.setStatus("pending");
                dto.setTotalQuantity(totalQuantity);
                String order = dao.insertOrder(dto);
                String[] orderArr = order.split("-");
                if (Integer.parseInt(orderArr[0]) > 0) {
                    if (dao.insertOrderDetail(Integer.parseInt(orderArr[0]), listCart)) {
                        response.getWriter().append(orderArr[1] + "-" + orderArr[2]);
                        flag = 2;
                        System.out.println("Insert order detail thanh cong ");
                    } else {
                        System.out.println("Insert order detail that bai ");
                    }
                } else {
                    response.setContentType("application/json");
                    json = new Gson().toJson("failed");
                    response.getWriter().write(json);
                    System.out.println("OrderID ko ton tai");
                }
                if (flag == 2) {
                    if (dao.updateQuantity(listafter)) {
                        System.out.println("update quantity thanh cong");
                    } else {
                        System.out.println("update quantity khong thanh cong, FAIL ");
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
