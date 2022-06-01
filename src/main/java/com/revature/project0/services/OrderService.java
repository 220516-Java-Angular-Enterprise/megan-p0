package com.revature.project0.services;

import com.revature.project0.daos.OrderDAO;
import com.revature.project0.models.Order;
import com.revature.project0.util.custom_exceptions.InvalidSQLException;
import com.revature.project0.util.annotations.Inject;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    @Inject
    private final OrderDAO orderDAO;

    @Inject
    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void register(Order order) {
        orderDAO.save(order);
    }
    public List<Order> getAllOrders() {
        return orderDAO.getAll();
    }

    public Order getById(String id) {
        return orderDAO.getById(id);
    }

    public boolean deleteOrder(String id) {
        try {
            orderDAO.delete(id);
            return true;
        } catch (InvalidSQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public List<Order> storeOrders(String store_id) {
        return orderDAO.getOrdersByStore(store_id);
    }

    public List<Order> userOrders(String user_id) {
        return orderDAO.getOrdersByUser(user_id);
    }
}
