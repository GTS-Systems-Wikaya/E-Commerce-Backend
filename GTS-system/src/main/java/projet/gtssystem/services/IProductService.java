package projet.gtssystem.services;

import projet.gtssystem.entities.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);
    Product updateProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(int id);
    void deleteProductById(int id);
    Product addProductWithCategoryID(Product product, int categoryID);
}
