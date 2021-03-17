package fr.epsi.gostyleapi.services;

import fr.epsi.gostyleapi.external.dto.CouponDTO;
import fr.epsi.gostyleapi.external.entities.CouponEntity;
import fr.epsi.gostyleapi.external.repositories.CouponsRepository;
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
public class CouponService {

    private Logger LOGGER = LoggerFactory.getLogger(CouponService.class);

    @Autowired
    private CouponsRepository couponsRepository;

    public CouponEntity convertCouponDtoToCouponEntity(CouponDTO dto){
        CouponEntity result = new CouponEntity();
        result.setId(UUID.randomUUID());
        result.setEmplacement(dto.getEmplacement());
        result.setNb_utilisation(dto.getNb_utilisation());
        result.setLibelle(dto.getLibelle());
        result.setPourc_reduc(dto.getPourc_reduc());
        return result;
    }

    public List<CouponEntity> convertCouponDtosToCouponEntities(List<CouponDTO> dtos){
        List<CouponEntity> result = new ArrayList<>();
        dtos.forEach(dto -> {
            result.add(convertCouponDtoToCouponEntity(dto));
        });
        return result;
    }

    public List<CouponEntity> getAll(){
        return couponsRepository.findAll();
    }

    public CouponEntity getById(UUID uuid){
        Optional<CouponEntity> query = couponsRepository.findById(uuid);
        return query.orElse(null);
    }

    public CouponEntity create(CouponDTO dto){
        CouponEntity toCreate = convertCouponDtoToCouponEntity(dto);
        LOGGER.info("CREATE coupon {}", toCreate.toString());
        return couponsRepository.save(toCreate);
    }

    public CouponEntity patch(CouponDTO dto, UUID uuid) throws EntityNotFoundException {
        CouponEntity toPatch;
        Optional<CouponEntity> query = couponsRepository.findById(uuid);
        if (query.isPresent()){
            toPatch = query.get();
            LOGGER.info("PATCH coupon {} : {}", toPatch.getId(), dto.toString());
            toPatch.setEmplacement(dto.getEmplacement());
            toPatch.setNb_utilisation(dto.getNb_utilisation());
            toPatch.setLibelle(dto.getLibelle());
            toPatch.setPourc_reduc(dto.getPourc_reduc());
            return couponsRepository.save(toPatch);
        } else {
            throw new EntityNotFoundException("No entities to patch found with given id");
        }
    }

    public void delete(UUID uuid) throws EntityNotFoundException {
        CouponEntity toDelete;
        Optional<CouponEntity> query = couponsRepository.findById(uuid);
        if (query.isPresent()){
            toDelete = query.get();
            LOGGER.info("DELETE coupon {}", toDelete.toString());
            couponsRepository.delete(toDelete);
        } else {
            throw new EntityNotFoundException("No entity to delete found with given id");
        }
    }

}
