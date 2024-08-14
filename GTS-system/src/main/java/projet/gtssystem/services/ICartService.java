package projet.gtssystem.services;

import projet.gtssystem.entities.Cart;

import java.util.List;

public interface ICartService {
    Cart addCart(Integer productId,String userEmail);
    List<Cart> getAllCarts();
    List<Cart> getCartsByUserEmail(String userEmail);
    Integer gettotalProductsinCartByEmail(String email);
    void deleteCartByid(int id);
    Float totalPrice(String email);
    void deleteAllCartsByemail(String email);

}
