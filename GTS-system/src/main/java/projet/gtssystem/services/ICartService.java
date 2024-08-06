package projet.gtssystem.services;

import projet.gtssystem.entities.Cart;

public interface ICartService {
    Cart addCart(Integer productId,String userEmail);
}
