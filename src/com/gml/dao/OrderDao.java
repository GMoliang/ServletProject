package com.gml.dao;

import com.gml.pojo.Door;
import com.gml.pojo.Order;
import com.gml.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.CheckedOutputStream;

/**
 * @program: ServletProject
 * @description:
 * @author: Mr.Gml
 * @create: 2020-07-10 14:58
 **/
public class OrderDao {
    //查询所有信息
    public List<Order> findAll() throws Exception {
        Connection conn = DBUtils.getConnectionByDatasource();
        String sql = "select * from tb_order";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Order order =null;
        List<Order> list = new ArrayList<>();
        while (rs.next()){
            order =new Order();
            order.setId(rs.getInt("id"));
            order.setDoorId(rs.getInt("door_id"));
            order.setOrderNo(rs.getString("order_no"));
            order.setOrderType(rs.getString("order_type"));
            order.setPnum(rs.getInt("pnum"));
            order.setCashier(rs.getString("cashier"));
            order.setOrderTime(rs.getTimestamp("order_time"));
            order.setPayTime(rs.getTimestamp("pay_time"));
            order.setPayType(rs.getString("pay_type"));
            order.setPrice(rs.getDouble("price"));
            list.add(order);
        }
        System.out.println(list);
        DBUtils.close(conn);
        return list;
    }
    public void addOrder(Order order) throws SQLException {
        Connection conn = DBUtils.getConnectionByDatasource();
        String sql = "insert into tb_order values (null,?,?,?,?,?,null,null,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, order.getDoorId());
        ps.setString(2, order.getOrderNo());
        ps.setString(3, order.getOrderType());
        ps.setInt(4, order.getPnum());
        ps.setString(5, order.getCashier());
        ps.setString(6, order.getPayType());
        ps.setDouble(7, order.getPrice());
        ps.executeUpdate();
        DBUtils.close(conn);
    }
    public Order findById(Integer id){
        Order order = null;
        Connection conn =null;
        try {
            conn = DBUtils.getConnectionByDatasource();
            String sql = "select * from tb_order where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                order =new Order();
                order.setId(rs.getInt("id"));
                order.setDoorId(rs.getInt("door_id"));
                order.setOrderNo(rs.getString("order_no"));
                order.setOrderType(rs.getString("order_type"));
                order.setPnum(rs.getInt("pnum"));
                order.setCashier(rs.getString("cashier"));
                order.setOrderTime(rs.getTimestamp("order_time"));
                order.setPayTime(rs.getTimestamp("pay_time"));
                order.setPayType(rs.getString("pay_type"));
                order.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn);
        }
        return order;
    }
    public void updateById(Order order) {
        System.out.println(order.toString());
        Connection conn = null;
        try {
            conn = DBUtils.getConnectionByDatasource();
            String sql = "update tb_order set door_id=?,order_no=?,order_type=?," +
                    "pnum=?,cashier=?,order_time=?,pay_time=?,pay_type=?,price=?" +
                    "where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, order.getDoorId());
            ps.setString(2, order.getOrderNo());
            ps.setString(3, order.getOrderType());
            ps.setInt(4, order.getPnum());
            ps.setString(5, order.getCashier());
            ps.setDate(6, new java.sql.Date( order.getOrderTime().getTime()));
            ps.setDate(7, new java.sql.Date( order.getPayTime().getTime()));
            ps.setString(8, order.getPayType());
            ps.setDouble(9, order.getPrice());
            ps.setInt(10,order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }
    }
    public void deleteById(Integer id){
        Connection conn = null;
        try {
            conn = DBUtils.getConnectionByDatasource();
            String sql = "delete from tb_order where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn);
        }
    }


}
