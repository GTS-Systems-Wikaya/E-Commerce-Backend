package projet.gtssystem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projet.gtssystem.entities.CategoryProduct;
import projet.gtssystem.entities.Product;
import projet.gtssystem.repositories.CategoryProductRepository;
import projet.gtssystem.repositories.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {
    ProductRepository productRepository;
    ICategoryProductService   categoryProductService;
    CategoryProductRepository categoryProductRepository;
    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteById(id);

    }

    @Override
    public Product addProductWithCategoryID(Product product, int categoryID) {
CategoryProduct category=categoryProductService.getCategoryById(categoryID);
product.setCategory(category);


        return productRepository.save(product);
    }
}
