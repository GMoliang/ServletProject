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

/**
 * @program: ServletProject
 * @description:
 * @author: Mr.Gml
 * @create: 2020-07-10 15:36
 **/
@WebServlet("/orderInfo")
public class OrderinfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取门店id
        Integer id = Integer.parseInt(request.getParameter("id"));
        //根据门店id查询门店信息
        OrderDao orderDao = new OrderDao();
        Order order = orderDao.findById(id);
        System.out.println(order);
        request.setAttribute("order",order);
        request.getRequestDispatcher("/order_update.jsp").forward(request,response);
    }
}
