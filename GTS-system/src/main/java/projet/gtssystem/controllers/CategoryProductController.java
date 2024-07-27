package projet.gtssystem.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.gtssystem.entities.CategoryProduct;
import projet.gtssystem.services.CategoryProductServiceImpl;
import projet.gtssystem.services.ICategoryProductService;
import projet.gtssystem.services.IProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level= AccessLevel.PUBLIC)
@RequestMapping("/category")
public class CategoryProductController {
    ICategoryProductService categoryProductService;
    @GetMapping("/getall")
    public List<CategoryProduct> getAllCategorys(){
return         categoryProductService.getAllCategorys();
    }
    @PutMapping("/add")
    public CategoryProduct addCategory(@RequestBody CategoryProduct categoryProduct){
        return categoryProductService.addCategory(categoryProduct);
    }
    @GetMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Integer id){
         categoryProductService.deleteCategoryById(id);
    }

}
