package fr.epsi.gostyleapi.external.repositories;

import fr.epsi.gostyleapi.external.entities.CategorieEntity;
import fr.epsi.gostyleapi.external.entities.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoriesRepository extends JpaRepository<CategorieEntity, UUID> {
    List<CategorieEntity> findAll();
    Optional<CategorieEntity> findById(UUID id);
    void deleteById(UUID id);
}
