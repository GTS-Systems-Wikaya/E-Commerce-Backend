package projet.gtssystem.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import projet.gtssystem.entities.Cart;
import projet.gtssystem.services.ICartService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level= AccessLevel.PUBLIC)
@RequestMapping("/cart")
public class CartController {

    ICartService cartService;

    @GetMapping("/add/{idproduct}/{useremail}")
    public Cart addCart(@PathVariable Integer idproduct,@PathVariable String useremail){
        return cartService.addCart(idproduct,useremail);
    }
    @GetMapping("/getcartsbyemail/{useremail}")
    public List<Cart> getCartsByEmail(@PathVariable String useremail){
        return cartService.getCartsByUserEmail(useremail);
    }
    @DeleteMapping("/delete/{id}")
    public void  getCartsByEmail(@PathVariable Integer id){
         cartService.deleteCartByid(id);
    }
    @GetMapping("/totalproducts/{useremail}")
    public Integer getTotalProducts(@PathVariable String useremail){
       return cartService.gettotalProductsinCartByEmail(useremail);

    }

}
