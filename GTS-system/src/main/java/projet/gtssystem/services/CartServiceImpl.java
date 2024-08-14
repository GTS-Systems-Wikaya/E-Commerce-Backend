package projet.gtssystem.services;

import lombok.AllArgsConstructor;
import org.springframework.security.context.DelegatingApplicationListener;
import org.springframework.stereotype.Service;
import projet.gtssystem.User.User;
import projet.gtssystem.User.UserRepository;
import projet.gtssystem.entities.Cart;
import projet.gtssystem.entities.Product;
import projet.gtssystem.repositories.CartRepository;
import projet.gtssystem.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CartServiceImpl implements ICartService{
    private final DelegatingApplicationListener delegatingApplicationListener;
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

    @Override
    public List<Cart> getCartsByUserEmail(String userEmail) {
        List<Cart> allcarts=cartRepository.findAll();
        List<Cart> usercarts=new ArrayList<>();
        for(Cart cart:allcarts){
            if(cart.getUser().getEmail().equals(userEmail)){
                usercarts.add(cart);
            }

        }
        return usercarts;
    }

    @Override
    public Integer gettotalProductsinCartByEmail(String email) {
        List<Cart> carts = getCartsByUserEmail(email);
        return carts.size();
    }

    @Override
    public void deleteCartByid(int id) {
cartRepository.deleteById(id);
    }

    @Override
    public Float totalPrice(String email) {
        float totalprice=0;
        List<Cart> carts = getCartsByUserEmail(email);
        for(Cart cart:carts){
            totalprice+=cart.getProduct().getPrice();

        }

        return totalprice;
    }

    @Override
    public void deleteAllCartsByemail(String email) {


    }
}
