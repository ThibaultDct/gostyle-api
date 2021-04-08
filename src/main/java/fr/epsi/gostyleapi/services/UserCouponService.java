package fr.epsi.gostyleapi.services;

import fr.epsi.gostyleapi.external.dto.CouponDTO;
import fr.epsi.gostyleapi.external.dto.QueryCouponDTO;
import fr.epsi.gostyleapi.external.dto.UserCouponDTO;
import fr.epsi.gostyleapi.external.entities.CouponEntity;
import fr.epsi.gostyleapi.external.entities.UserCouponEntity;
import fr.epsi.gostyleapi.external.repositories.CouponsRepository;
import fr.epsi.gostyleapi.external.repositories.UserCouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserCouponService {

    private Logger LOGGER = LoggerFactory.getLogger(UserCouponService.class);

    @Autowired
    private UserCouponRepository userCouponRepository;

    @Autowired
    private CouponsRepository couponsRepository;

    public UserCouponEntity convertUserCouponDtoToUserCouponEntity(UserCouponDTO dto){
        UserCouponEntity result = new UserCouponEntity();
        result.setId(UUID.randomUUID());
        result.setUserId(dto.getUser_id());
        result.setCouponId(dto.getCoupon_id());
        result.setValidity(new java.sql.Date(dto.getValidity().getTime()));
        result.setIs_used(dto.isIs_used());
        return result;
    }

    public List<UserCouponEntity> convertUserCouponDtosToUserCouponEntities(List<UserCouponDTO> dtos){
        List<UserCouponEntity> result = new ArrayList<>();
        dtos.forEach(dto -> {
            result.add(convertUserCouponDtoToUserCouponEntity(dto));
        });
        return result;
    }

    public QueryCouponDTO convertToQueryCouponDto(UserCouponEntity userCoupon, CouponEntity coupon){
        QueryCouponDTO result = new QueryCouponDTO();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(userCoupon.getValidity());

        result.setId(coupon.getId());
        result.setCode(coupon.getCode());
        result.setLibelle(coupon.getLibelle());
        result.setPourc_reduc(coupon.getPourc_reduc());
        result.setDate(date);

        return result;
    }

    public List<UserCouponEntity> getAll(){
        return userCouponRepository.findAll();
    }

    public UserCouponEntity getById(UUID uuid){
        Optional<UserCouponEntity> query = userCouponRepository.findById(uuid);
        return query.orElse(null);
    }

    public List<UserCouponEntity> getByUserId(UUID uuid){
        return userCouponRepository.findByUserId(uuid);
    }

    public List<UserCouponEntity> getByCouponId(UUID uuid){
        return userCouponRepository.findByCouponId(uuid);
    }

    public List<QueryCouponDTO> getAllCouponsFromUser(UUID uuid){
        List<UserCouponEntity> relations = getByUserId(uuid);
        List<QueryCouponDTO> coupons = new ArrayList<>();

        relations.forEach(relation -> {
            CouponEntity coupon = couponsRepository.findById(relation.getCouponId()).get();
            coupons.add(convertToQueryCouponDto(relation, coupon));
        });

        return coupons;
    }

    public UserCouponEntity create(UserCouponDTO dto){
        UserCouponEntity toCreate = convertUserCouponDtoToUserCouponEntity(dto);
        LOGGER.info("CREATE user coupon {}", toCreate.toString());
        return userCouponRepository.save(toCreate);
    }

    public UserCouponEntity patch(UserCouponDTO dto, UUID uuid) throws EntityNotFoundException {
        UserCouponEntity toPatch;
        Optional<UserCouponEntity> query = userCouponRepository.findById(uuid);
        if (query.isPresent()){
            toPatch = query.get();
            LOGGER.info("PATCH user coupon {} : {}", toPatch.getId(), dto.toString());
            return userCouponRepository.save(toPatch);
        } else {
            throw new EntityNotFoundException("No entities to patch found with given id");
        }
    }

    public void delete(UUID uuid) throws EntityNotFoundException {
        UserCouponEntity toDelete;
        Optional<UserCouponEntity> query = userCouponRepository.findById(uuid);
        if (query.isPresent()){
            toDelete = query.get();
            LOGGER.info("DELETE user coupon {}", toDelete.toString());
            userCouponRepository.delete(toDelete);
        } else {
            throw new EntityNotFoundException("No entity to delete found with given id");
        }
    }

}
