package projet.gtssystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.gtssystem.entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {

}
