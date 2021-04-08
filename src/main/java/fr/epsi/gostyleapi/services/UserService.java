package fr.epsi.gostyleapi.services;

import fr.epsi.gostyleapi.external.dto.UserDTO;
import fr.epsi.gostyleapi.external.entities.UserEntity;
import fr.epsi.gostyleapi.external.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserEntity convertUserDtoToUserEntity(UserDTO dto){
        UserEntity result = new UserEntity();
        result.setId(UUID.randomUUID());
        result.setNom(dto.getNom());
        result.setPrenom(dto.getPrenom());
        result.setPseudo(dto.getPseudo());
        result.setMail(dto.getMail());
        result.setMdp(bCryptPasswordEncoder.encode(dto.getMdp()));
        return result;
    }

    public List<UserEntity> convertUserDtosToUserEntities(List<UserDTO> dtos){
        List<UserEntity> result = new ArrayList<>();
        dtos.forEach(dto -> {
            result.add(convertUserDtoToUserEntity(dto));
        });
        return result;
    }

    public List<UserEntity> getAll() { return usersRepository.findAll(); }

    public UserEntity getById(UUID uuid){
        Optional<UserEntity> query = usersRepository.findById(uuid);
        return query.orElse(null);
    }

    public UserEntity getByPseudo(String pseudo){
        Optional<UserEntity> query = usersRepository.findByPseudo(pseudo);
        return query.orElse(null);
    }

    public UserEntity create(UserDTO dto) { return usersRepository.save(convertUserDtoToUserEntity(dto)); }

    public UserEntity patch(UserDTO dto, UUID uuid) throws EntityNotFoundException {
        UserEntity toPatch;
        Optional<UserEntity> query = usersRepository.findById(uuid);
        if (query.isPresent()){
            toPatch = query.get();
            toPatch.setNom(dto.getNom());
            toPatch.setPrenom(dto.getPrenom());
            toPatch.setPseudo(dto.getPseudo());
            toPatch.setMail(dto.getMail());
            toPatch.setMdp(bCryptPasswordEncoder.encode(dto.getMdp()));
            return usersRepository.save(toPatch);
        } else {
            throw new EntityNotFoundException("No entities to patch found with given id");
        }
    }

    public void delete(UUID uuid) throws EntityNotFoundException {
        Optional<UserEntity> query = usersRepository.findById(uuid);
        if (query.isPresent()){
            usersRepository.delete(query.get());
        } else {
            throw new EntityNotFoundException("No entity to delete found with given id");
        }
    }

}
