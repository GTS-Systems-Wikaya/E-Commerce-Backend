package projet.gtssystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.gtssystem.entities.FileData;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData,Integer> {
    Optional<FileData> findByName(String fileName);

}
