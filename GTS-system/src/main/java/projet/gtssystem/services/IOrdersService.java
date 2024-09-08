package projet.gtssystem.services;

import org.hibernate.query.Order;
import projet.gtssystem.entities.Orders;

import java.util.List;

public interface IOrdersService {
    Orders addOrder(String email);
    List<Orders> getAllOrdersByEmail(String email);
    List<Orders> getAllOrders();
}
