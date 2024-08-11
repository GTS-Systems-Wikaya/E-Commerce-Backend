package projet.gtssystem.controllers;

import jdk.jfr.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projet.gtssystem.entities.CategoryProduct;
import projet.gtssystem.entities.Product;
import projet.gtssystem.repositories.ProductRepository;
import projet.gtssystem.services.IProductService;
import projet.gtssystem.services.StorageService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level= AccessLevel.PUBLIC)
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final String FOLDER_PATH="C:/Users/ahmed/OneDrive/Desktop/images/";
    IProductService productService;
    @Autowired
    private StorageService storageService;
    @GetMapping("/getall")
    public List<Product> getAllProducts() {
       return productService.getAllProducts();
    }
    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProductById(id);
    }
    @PostMapping("add")
    public void addProduct(@RequestBody Product product,@RequestBody MultipartFile file) throws IOException {
        String uploadImage = storageService.uploadImageToFileSystem(file);
        ResponseEntity.status(HttpStatus.OK).body(uploadImage);
        String filePath=FOLDER_PATH+file.getOriginalFilename();
        //product.setImagePath(filePath);
        productService.addProduct(product);
    }
    @PutMapping("add+catetgory/{id}")
    public void addProductCategory(@PathVariable int id, @RequestBody Product product) {
        productService.addProductWithCategoryID(product,id);
    }
    @PutMapping("/update")
    public void updateProdutc( @RequestBody Product product) {
        productService.updateProduct(product);
    }
    @GetMapping("/getById/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }
}
