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
import java.util.List;

/**
 * @program: ServletProject
 * @description:
 * @author: Mr.Gml
 * @create: 2020-07-10 14:39
 **/
@WebServlet("/orderList")
public class OrderListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("商品列表查询");
        OrderDao  orderDao = new OrderDao();
        try {
            List<Order> list = orderDao.findAll();
            request.setAttribute("list",list);
            request.getRequestDispatcher("/order_list.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
