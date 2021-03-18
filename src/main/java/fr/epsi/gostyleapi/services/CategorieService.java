package fr.epsi.gostyleapi.services;

import fr.epsi.gostyleapi.external.dto.CategorieDTO;
import fr.epsi.gostyleapi.external.entities.CategorieEntity;
import fr.epsi.gostyleapi.external.repositories.CategoriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategorieService {

    private Logger LOGGER = LoggerFactory.getLogger(CategorieService.class);

    @Autowired
    private CategoriesRepository categoriesRepository;

    public CategorieEntity convertCategorieDtoToCategorieEntity(CategorieDTO dto){
        CategorieEntity result = new CategorieEntity();
        result.setId(UUID.randomUUID());
        result.setNom(dto.getNom());
        return result;
    }

    public List<CategorieEntity> convertCouponDtosToCouponEntities(List<CategorieDTO> dtos){
        List<CategorieEntity> result = new ArrayList<>();
        dtos.forEach(dto -> {
            result.add(convertCategorieDtoToCategorieEntity(dto));
        });
        return result;
    }

    public List<CategorieEntity> getAll(){
        return categoriesRepository.findAll();
    }

    public CategorieEntity getById(UUID uuid){
        Optional<CategorieEntity> query = categoriesRepository.findById(uuid);
        return query.orElse(null);
    }

    public CategorieEntity create(CategorieDTO dto){
        CategorieEntity toCreate = convertCategorieDtoToCategorieEntity(dto);
        LOGGER.info("CREATE categorie {}", toCreate.toString());
        return categoriesRepository.save(toCreate);
    }

    public CategorieEntity patch(CategorieDTO dto, UUID uuid) throws EntityNotFoundException {
        CategorieEntity toPatch;
        Optional<CategorieEntity> query = categoriesRepository.findById(uuid);
        if (query.isPresent()){
            toPatch = query.get();
            LOGGER.info("PATCH categorie {} : {}", toPatch.getId(), dto.toString());
            toPatch.setNom(dto.getNom());
            return categoriesRepository.save(toPatch);
        } else {
            throw new EntityNotFoundException("No entities to patch found with given id");
        }
    }

    public void delete(UUID uuid) throws EntityNotFoundException {
        CategorieEntity toDelete;
        Optional<CategorieEntity> query = categoriesRepository.findById(uuid);
        if (query.isPresent()){
            toDelete = query.get();
            LOGGER.info("DELETE categorie {}", toDelete.toString());
            categoriesRepository.delete(toDelete);
        } else {
            throw new EntityNotFoundException("No entity to delete found with given id");
        }
    }

}
