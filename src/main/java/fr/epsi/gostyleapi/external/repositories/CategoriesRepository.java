package fr.epsi.gostyleapi.external.repositories;

import fr.epsi.gostyleapi.external.entities.CategorieEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface CategoriesRepository extends Repository<CategorieEntity, Long> {
    Iterable<CategorieEntity> findAll();
    Optional<CategorieEntity> findById(int id);
    void deleteById(int id);
}
