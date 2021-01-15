package fr.epsi.gostyleapi.external.repositories;

import fr.epsi.gostyleapi.external.entities.CouponEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface CouponsRepository extends Repository<CouponEntity, Long> {
    Iterable<CouponEntity> findAll();
    Optional<CouponEntity> findById(int id);
    void deleteById(int id);
}
