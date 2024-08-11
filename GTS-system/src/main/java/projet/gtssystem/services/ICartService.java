package projet.gtssystem.services;

import projet.gtssystem.entities.Cart;

import java.util.List;

public interface ICartService {
    Cart addCart(Integer productId,String userEmail);
    List<Cart> getAllCarts();
}
