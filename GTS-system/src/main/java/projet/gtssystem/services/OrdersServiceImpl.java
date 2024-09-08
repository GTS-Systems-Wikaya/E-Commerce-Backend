package projet.gtssystem.services;

import lombok.AllArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;
import projet.gtssystem.User.User;
import projet.gtssystem.User.UserRepository;
import projet.gtssystem.entities.Cart;
import projet.gtssystem.entities.OrderStatus;
import projet.gtssystem.entities.Orders;
import projet.gtssystem.entities.Product;
import projet.gtssystem.repositories.CartRepository;
import projet.gtssystem.repositories.OrdersRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrdersServiceImpl implements IOrdersService {
OrdersRepository ordersRepository;
        ICartService cartService;
        UserRepository userRepository;
        CartRepository cartRepository;

    @Override
    public Orders addOrder(String email) {
        Orders order = new Orders();
        Optional<User> user  ;
        List<Product> products=new ArrayList<>();


        List<Cart> carts = cartService.getCartsByUserEmail(email);
        for(Cart cart:carts){
            products.add(cart.getProduct());
        }
        user= userRepository.findByEmail(email);
        order.setUser(user.get());
        order.setItems(products);
        order.setStatus(OrderStatus.PROCESSING);
        order.setOrderDate(LocalDateTime.now());
        cartRepository.deleteAll(carts);
        return ordersRepository.save(order);
    }

    @Override
    public List<Orders> getAllOrdersByEmail(String email) {
        List<Orders> ordersbyEmail = new ArrayList<>();
        List<Orders> orders = ordersRepository.findAll();
        for(Orders order:orders){
            if(order.getUser().getEmail().equals(email)){
                ordersbyEmail.add(order);
            }
        }
        return ordersbyEmail;
    }

    @Override
    public List<Orders> getAllOrders() {
        return    ordersRepository.findAll();

    }

}
