package fr.epsi.gostyleapi.external.controllers;

import fr.epsi.gostyleapi.external.dto.UserCouponDTO;
import fr.epsi.gostyleapi.external.entities.UserCouponEntity;
import fr.epsi.gostyleapi.services.CouponService;
import fr.epsi.gostyleapi.services.UserCouponService;
import fr.epsi.gostyleapi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UserCouponController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserCouponController.class);

    @Autowired
    private UserCouponService userCouponService;

    @Autowired
    private UserService userService;

    @Autowired
    private CouponService couponService;

    @GetMapping("/user_coupons")
    public List<UserCouponEntity> getAll(){
        List<UserCouponEntity> results = new ArrayList<>();
        try {
            results = userCouponService.getAll();
        } catch (Exception e) {
            LOGGER.error("Unable to retrieve the list of user coupons : {}", e.getMessage());
        }
        return results;
    }

    @GetMapping("/user_coupons/{uuid}")
    public UserCouponEntity getById(@PathVariable(value="uuid") UUID uuid){
        UserCouponEntity result = new UserCouponEntity();
        try {
            result = userCouponService.getById(uuid);
        } catch (Exception e) {
            LOGGER.error("Unable to retrieve coupons of user {} : {}", uuid, e.getMessage());
        }
        return result;
    }

    @GetMapping("/user_coupons/byUser/{uuid}")
    public List<UserCouponEntity> getByUserId(@PathVariable(value="uuid") UUID uuid){
        List<UserCouponEntity> results = new ArrayList<>();
        try {
            results = userCouponService.getByUserId(uuid);
        } catch (Exception e) {
            LOGGER.error("Unable to retrieve coupons of user {} : {}", uuid, e.getMessage());
        }
        return results;
    }

    @GetMapping("/user_coupons/byCoupon/{uuid}")
    public List<UserCouponEntity> getByCouponId(@PathVariable(value="uuid") UUID uuid){
        List<UserCouponEntity> results = new ArrayList<>();
        try {
            results = userCouponService.getByCouponId(uuid);
        } catch (Exception e) {
            LOGGER.error("Unable to retrieve users of coupon {} : {}", uuid, e.getMessage());
        }
        return results;
    }

    @PostMapping(path = "/user_coupons", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> create(@RequestBody UserCouponDTO dto){
        if (userService.getById(dto.getUser_id()) != null && couponService.getById(dto.getCoupon_id()) != null) {
            try {
                UserCouponEntity created = userCouponService.create(dto);
                URI uri = URI.create(String.format("/user_coupons/%s", created.getId()));
                return ResponseEntity.created(uri).build();
            } catch (Exception e) {
                LOGGER.error("Unable to create new user coupon : {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            LOGGER.error("Unable to create new user coupon : given user or coupon does not exist");
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(path ="/user_coupons/{uuid}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> patch(@RequestBody UserCouponDTO dto, @PathVariable(value="uuid") UUID uuid){
        try {
            userCouponService.patch(dto, uuid);
            return ResponseEntity.accepted().build();
        } catch (EntityNotFoundException enfe) {
            LOGGER.error("Unable to patch user coupon {} : {}", uuid, enfe.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error("Unable to patch user coupon {} : {}", uuid, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "/user_coupons/{uuid}")
    public ResponseEntity<String> delete(@PathVariable(value="uuid") UUID uuid){
        try {
            userCouponService.delete(uuid);
            return ResponseEntity.accepted().build();
        } catch (EntityNotFoundException enfe) {
            LOGGER.error("Unable to delete user coupon {} : {}", uuid, enfe.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error("Unable to delete user_coupon {} : {}", uuid, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/user_coupons", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> collectOptions(){
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .build();
    }

}
