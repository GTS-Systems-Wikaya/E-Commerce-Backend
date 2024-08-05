package projet.gtssystem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projet.gtssystem.entities.CategoryProduct;
import projet.gtssystem.repositories.CategoryProductRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class CategoryProductServiceImpl implements ICategoryProductService{
     CategoryProductRepository categoryProductRepository;
    @Override
    public CategoryProduct addCategory(CategoryProduct category) {

        return categoryProductRepository.save(category);
    }

    @Override
    public CategoryProduct updateCategory(CategoryProduct category) {
        return categoryProductRepository.save(category);
    }

    @Override
    public List<CategoryProduct> getAllCategorys() {
        return categoryProductRepository.findAll();
    }

    @Override
    public CategoryProduct getCategoryById(int id) {
        return categoryProductRepository.findById(id).get();
    }

    @Override
    public void deleteCategoryById(int id) {
        categoryProductRepository.deleteById(id);

    }
}
