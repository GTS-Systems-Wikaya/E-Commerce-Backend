package projet.gtssystem.controllers;

import jdk.jfr.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import projet.gtssystem.entities.CategoryProduct;
import projet.gtssystem.entities.Product;
import projet.gtssystem.repositories.ProductRepository;
import projet.gtssystem.services.IProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level= AccessLevel.PUBLIC)
@RequestMapping("/product")
public class ProductController {
    IProductService productService;
    @GetMapping("/getall")
    public List<Product> getAllProducts() {
       return productService.getAllProducts();
    } @GetMapping("delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProductById(id);
    }
    @PutMapping("add")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }
    @PutMapping("add+catetgory/{id}")
    public void addProductCategory(@PathVariable int id, @RequestBody Product product) {
        productService.addProductWithCategoryID(product,id);
    }
}
