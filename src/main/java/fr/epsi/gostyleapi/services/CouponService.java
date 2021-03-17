package fr.epsi.gostyleapi.services;

import fr.epsi.gostyleapi.business.Coupon;
import fr.epsi.gostyleapi.external.dto.CouponDTO;
import fr.epsi.gostyleapi.external.entities.CouponEntity;
import fr.epsi.gostyleapi.external.repositories.CouponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CouponService {

    @Autowired
    private CouponsRepository couponsRepository;

    public CouponEntity convertCouponDtoToCouponEntity(CouponDTO dto){
        CouponEntity result = new CouponEntity();
        result.setId(UUID.randomUUID());
        result.setEmplacement(dto.getEmplacement());
        result.setNbUtilisation(dto.getNb_utilisation());
        result.setLibelle(dto.getLibelle());
        result.setPourcReduc(dto.getPourc_reduc());
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
        return couponsRepository.save(convertCouponDtoToCouponEntity(dto));
    }

    public CouponEntity patch(CouponDTO dto, UUID uuid) throws EntityNotFoundException {
        CouponEntity toPatch;
        Optional<CouponEntity> query = couponsRepository.findById(uuid);
        if (query.isPresent()){
            toPatch = query.get();
            toPatch.setEmplacement(dto.getEmplacement());
            toPatch.setNbUtilisation(dto.getNb_utilisation());
            toPatch.setLibelle(dto.getLibelle());
            toPatch.setPourcReduc(dto.getPourc_reduc());
            return couponsRepository.save(toPatch);
        } else {
            throw new EntityNotFoundException("No entities to patch found with given id");
        }
    }

    public void delete(UUID uuid) throws EntityNotFoundException {
        Optional<CouponEntity> query = couponsRepository.findById(uuid);
        if (query.isPresent()){
            couponsRepository.delete(query.get());
        } else {
            throw new EntityNotFoundException("No entity to delete found with given id");
        }
    }

}
