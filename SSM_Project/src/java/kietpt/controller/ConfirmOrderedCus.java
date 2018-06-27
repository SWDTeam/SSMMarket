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
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kietpt.dao.ProductDao;
import kietpt.dto.OrderDetailDto;
import kietpt.dto.OrderDto;
import kietpt.dto.ProductDto;

/**
 *
 * @author kietp
 */
public class ConfirmOrderedCus extends HttpServlet {

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
            System.out.println("da vao controller Order Confirm Customer");
            String confirmOrdered = request.getParameter("listConfirmOrdered");
            String orderId = request.getParameter("orderId");
            System.out.println("confirmOrdered = " + confirmOrdered);
            ProductDao dao = new ProductDao();

            JsonParser parser = new JsonParser();
            JsonArray jsonArray = (JsonArray) parser.parse(confirmOrdered);
            List<OrderDetailDto> listConfirmOrder = new ArrayList<>();

            int flag = 0;
            boolean checked = false;

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                int orderDetailId = jsonObject.get("orderDetailId").getAsInt();
                int productId = jsonObject.get("productId").getAsInt();
                String productName = jsonObject.get("productName").getAsString();
                float productPrice = jsonObject.get("productPrice").getAsFloat();
                int productQuantity = jsonObject.get("productQuantity").getAsInt();
                String productKey = jsonObject.get("productKey").getAsString();
                listConfirmOrder.add(new OrderDetailDto(orderDetailId, Integer.parseInt(orderId), productId, 
                        productPrice, productQuantity, productKey,productName));
            }

            List<ProductDto> listProduct = dao.getListProduct();
            List<OrderDetailDto> listOldOrderDetail = dao.getListOldOrderDetail();
            List<ProductDto> listAfterUpdateQuan = new ArrayList<>();
            for (int i = 0; i < listOldOrderDetail.size(); i++) {
                for (int j = 0; j < listConfirmOrder.size(); j++) {
                    if (listOldOrderDetail.get(i).getOrderDetailId() == listConfirmOrder.get(j).getOrderDetailId()) {
                        if (listOldOrderDetail.get(i).getQuantity() > listConfirmOrder.get(j).getQuantity()) {
                            int afterQuanDetail = listOldOrderDetail.get(i).getQuantity() - listConfirmOrder.get(j).getQuantity();
                            listAfterUpdateQuan.add(new ProductDto(listConfirmOrder.get(j).getProductId(),
                                    listConfirmOrder.get(j).getProductKey(), afterQuanDetail, true));
                            System.out.println("afterQuanDetail = " + afterQuanDetail + "id = " + listConfirmOrder.get(j).getProductId());

                        } else if (listOldOrderDetail.get(i).getQuantity() < listConfirmOrder.get(j).getQuantity()) {
                            int afterQuanDetail = listConfirmOrder.get(j).getQuantity() - listOldOrderDetail.get(i).getQuantity();
                            listAfterUpdateQuan.add(new ProductDto(listConfirmOrder.get(j).getProductId(),
                                    listConfirmOrder.get(j).getProductKey(), afterQuanDetail, false));
                            System.out.println("afterQuanDetail = " + afterQuanDetail + "id = " + listConfirmOrder.get(j).getProductId());
                        }
                    }
                    flag = 0;
                }
            }
            if (flag == 0) {
                for (int i = 0; i < listProduct.size(); i++) {
                    for (int j = 0; j < listConfirmOrder.size(); j++) {
                        if (listConfirmOrder.get(j).getProductId() == listProduct.get(i).getProductId()) {
                            if (listConfirmOrder.get(j).getQuantity() > listProduct.get(i).getQuantity()) {
                                flag = 1;
                                checked = true;
                                response.getWriter().append(listConfirmOrder.get(j).getProductName() + " has only "
                                        + listProduct.get(i).getQuantity() + " in stock.Please decrease quantity of product in your cart!!!" + "/");
                                System.out.println("đã fail khi số lượng vượt quá ");
                            } else {
                                flag = 0;
                                checked = false;
                            }
                        }
                        System.out.println(" i = " + i + " - " + "j = " + j);

                    }
                    if (flag == 1 && checked == true) {
                        break;
                    }
                }
            }
            // insert vào bảng order 
            if (flag == 0 && checked == false) {
                OrderDto dto = new OrderDto();
                float totalPrice = getTotalPrice(listConfirmOrder);
                int totalQuantity = getTotalQuantity(listConfirmOrder);
                dto.setOrderId(Integer.parseInt(orderId));
                Timestamp updateConfirm = new Timestamp(System.currentTimeMillis());
                System.out.println("updateConfirm + : " + updateConfirm);
                dto.setUpdateTime(updateConfirm);
                dto.setTotalPrice(totalPrice);
                dto.setTotalQuantity(totalQuantity);
                boolean orderChecked = dao.updateOrderConfirm(dto);

                if (orderChecked) {
                    if (dao.updateOrderDetail(Integer.parseInt(orderId), listConfirmOrder)) {
                        response.getWriter().append("successfully");
                        flag = 2;
                        System.out.println("updateorder detail thanh cong ");
                    } else {
                        System.out.println("update order detail that bai ");
                    }
                } else {
                    String json = "";
                    response.setContentType("application/json");
                    json = new Gson().toJson("failed");
                    response.getWriter().write(json);
                    System.out.println("OrderDetail ID ko ton tai");
                }
                if (flag == 2) {
                    ProductDto productUpdate = null;
                    List<ProductDto> listUpdateProduct = new ArrayList<>();
                    for (int i = 0; i < listProduct.size(); i++) {
                        for (int j = 0; j < listAfterUpdateQuan.size(); j++) {
                            if (listProduct.get(i).getProductId() == listAfterUpdateQuan.get(j).getProductId()) {
                                if (listAfterUpdateQuan.get(j).isChecked() == true) {
                                    productUpdate = new ProductDto();
                                    productUpdate.setProductId(listProduct.get(i).getProductId());
                                    productUpdate.setQuantity(listProduct.get(i).getQuantity()
                                            + listAfterUpdateQuan.get(j).getQuantity());
                                    listUpdateProduct.add(productUpdate);
                                } else {
                                    productUpdate = new ProductDto();
                                    productUpdate.setProductId(listProduct.get(i).getProductId());
                                    productUpdate.setQuantity(listProduct.get(i).getQuantity()
                                            - listAfterUpdateQuan.get(j).getQuantity());
                                    listUpdateProduct.add(productUpdate);
                                }
                            }
                        }
                    }
                    for (int i = 0; i < listUpdateProduct.size(); i++) {
                        System.out.println("id = " + listUpdateProduct.get(i).getProductId()
                                + "Quantity = " + listUpdateProduct.get(i).getQuantity());
                    }
                    if (dao.updateQuantity(listUpdateProduct)) {
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

    private float getTotalPrice(List<OrderDetailDto> listConfirmOrder) {
        float totalPrice = 0;
        for (int i = 0; i < listConfirmOrder.size(); i++) {
            totalPrice += listConfirmOrder.get(i).getPrice();
        }
        return totalPrice;
    }

    private int getTotalQuantity(List<OrderDetailDto> listConfirmOrder) {
        int totalQuantity = 0;
        for (int i = 0; i < listConfirmOrder.size(); i++) {
            totalQuantity += listConfirmOrder.get(i).getQuantity();
        }
        return totalQuantity;
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
