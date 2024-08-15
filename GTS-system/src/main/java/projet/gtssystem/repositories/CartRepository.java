package projet.gtssystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.gtssystem.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
