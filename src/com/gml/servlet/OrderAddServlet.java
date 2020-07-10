package com.gml.servlet;

import com.gml.dao.DoorDao;
import com.gml.dao.OrderDao;
import com.gml.pojo.Door;
import com.gml.pojo.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @program: ServletProject
 * @description:
 * @author: Mr.Gml
 * @create: 2020-07-10 16:49
 **/
@WebServlet("/orderAdd")
public class OrderAddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String orderNo = request.getParameter("orderNo");
        String orderType = request.getParameter("orderType");
        Integer pnum = Integer.parseInt(request.getParameter("pnum"));
        String cashier = request.getParameter("cashier");
        String payType = request.getParameter("payType");
        Double price = Double.parseDouble(request.getParameter("price"));

        Order order = new Order();
        order.setDoorId(1);
        order.setOrderNo(orderNo);
        order.setOrderType(orderType);
        order.setPnum(pnum);
        order.setCashier(cashier);
        order.setPayType(payType);
        order.setPrice(price);

        OrderDao orderDao = new OrderDao();
        try {
            orderDao.addOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(""+request.getContextPath()+"/orderList");

    }
}
