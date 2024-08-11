package projet.gtssystem.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import projet.gtssystem.entities.Cart;
import projet.gtssystem.services.ICartService;

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
}
