package projet.gtssystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.gtssystem.entities.CategoryProduct;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Integer> {
}
