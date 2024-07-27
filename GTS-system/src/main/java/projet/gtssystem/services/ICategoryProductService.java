package projet.gtssystem.services;

import projet.gtssystem.entities.CategoryProduct;

import java.util.List;

public interface ICategoryProductService {
    CategoryProduct addCategory(CategoryProduct category);
    CategoryProduct updateCategory(CategoryProduct category);
    List<CategoryProduct> getAllCategorys();
    CategoryProduct getCategoryById(int id);
    void deleteCategoryById(int id);
}
