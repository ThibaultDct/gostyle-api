package fr.epsi.gostyleapi.external.repositories;

import fr.epsi.gostyleapi.external.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, UUID> {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(UUID id);
    Optional<UserEntity> findByPseudo(String pseudo);
    void deleteById(UUID id);
}
