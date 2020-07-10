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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: ServletProject
 * @description:
 * @author: Mr.Gml
 * @create: 2020-07-10 15:44
 **/
@WebServlet("/orderUpdate")
public class OrderUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer doorId = Integer.parseInt(request.getParameter("doorId"));
        String orderNo = request.getParameter("orderNo");
        String orderType = request.getParameter("orderType");
        Integer pnum = Integer.parseInt(request.getParameter("pnum"));
        String cashier = request.getParameter("cashier");

        String dataTime=null;
        Date orderTime=null;
        Date payTime=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        dataTime = request.getParameter("orderTime");
        try {
            orderTime = sdf.parse(dataTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dataTime = request.getParameter("payTime");
        System.out.println(dataTime);
        try {
            payTime = sdf.parse(dataTime);
            System.out.println(payTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        String payType = request.getParameter("payType");
        Double price= Double.valueOf(request.getParameter("price"));

        Order order = new Order();
        order.setId(id);
        order.setDoorId(doorId);
        order.setOrderNo(orderNo);
        order.setOrderType(orderType);

        order.setPnum(pnum);
        order.setCashier(cashier);
        order.setOrderTime(orderTime);
        order.setPayTime(payTime);
        order.setPayType(payType);
        order.setPrice(price);

        System.out.println(order.toString());

        OrderDao orderDao = new OrderDao();
        orderDao.updateById(order);
        response.sendRedirect(""+request.getContextPath()+"/orderList");
    }
}
