package fr.epsi.gostyleapi.external.repositories;

import fr.epsi.gostyleapi.external.entities.UserCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserCouponRepository extends JpaRepository<UserCouponEntity, UUID> {
    List<UserCouponEntity> findAll();
    Optional<UserCouponEntity> findById(UUID id);
    List<UserCouponEntity> findByUserId(UUID userId);
    List<UserCouponEntity> findByCouponId(UUID couponId);
    void deleteById(UUID id);
}
