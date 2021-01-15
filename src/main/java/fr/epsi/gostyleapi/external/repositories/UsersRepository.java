package fr.epsi.gostyleapi.external.repositories;

import fr.epsi.gostyleapi.external.entities.UserEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UsersRepository extends Repository<UserEntity, Long> {
    Iterable<UserEntity> findAll();
    Optional<UserEntity> findById(int id);
    void deleteById(int id);
}
