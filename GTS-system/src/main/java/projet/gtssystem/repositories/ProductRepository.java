package projet.gtssystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.gtssystem.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
