package com.gml.servlet;

import com.gml.dao.DoorDao;
import com.gml.dao.OrderDao;

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
 * @create: 2020-07-10 15:31
 **/
@WebServlet("/orderDelete")
public class OrderDeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        OrderDao orderDao = new OrderDao();
        orderDao.deleteById(id);
        response.sendRedirect(""+request.getContextPath()+"/orderList");
    }


}
