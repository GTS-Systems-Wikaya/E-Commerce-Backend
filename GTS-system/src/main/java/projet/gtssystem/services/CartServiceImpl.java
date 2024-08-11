package projet.gtssystem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projet.gtssystem.User.User;
import projet.gtssystem.User.UserRepository;
import projet.gtssystem.entities.Cart;
import projet.gtssystem.entities.Product;
import projet.gtssystem.repositories.CartRepository;
import projet.gtssystem.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CartServiceImpl implements ICartService{
    CartRepository cartRepository;
    IProductService IproductService;
    UserRepository userRepository;

    @Override
    public Cart addCart(Integer productId,String userEmail) {
        Optional<User> user  ;
        Cart cart = new Cart();
        Product product = IproductService.getProductById(productId);
        cart.setProduct(product);
       user= userRepository.findByEmail(userEmail);
        System.out.printf(user.get().getEmail());
       cart.setUser(user.get());

        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
}
