package projet.gtssystem.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.gtssystem.entities.Cart;
import projet.gtssystem.entities.Orders;
import projet.gtssystem.repositories.OrdersRepository;
import projet.gtssystem.services.IOrdersService;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level= AccessLevel.PUBLIC)
@RequestMapping("/cart")
public class OrdersController {
    IOrdersService ordersService;
    @GetMapping("/add/{useremail}")
    public Orders addCart(@PathVariable String useremail){
        return ordersService.addOrder(useremail);
    }
}
