package fr.epsi.gostyleapi.external.controllers;

import fr.epsi.gostyleapi.external.dto.CouponDTO;
import fr.epsi.gostyleapi.external.entities.CouponEntity;
import fr.epsi.gostyleapi.services.CouponService;
import io.swagger.models.Response;
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
public class CouponController {

    private final Logger LOGGER = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    private CouponService couponService;

    @GetMapping("/coupons")
    public List<CouponEntity> getAll(){
        List<CouponEntity> results = new ArrayList<>();
        try {
            results = couponService.getAll();
        } catch (Exception e) {
            LOGGER.error("Unable to retrieve the list of coupons : {}", e.getMessage());
        }
        return results;
    }

    @GetMapping("/coupons/{uuid}")
    public CouponEntity getById(@PathVariable(value="uuid") UUID uuid){
        CouponEntity result = new CouponEntity();
        try {
            result = couponService.getById(uuid);
        } catch (Exception e) {
            LOGGER.error("Unable to retrieve coupon with id {} : {}", uuid, e.getMessage());
        }
        return result;
    }

    @PostMapping(path = "/coupons", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> create(@RequestBody CouponDTO dto){
        try {
            CouponEntity created = couponService.create(dto);
            URI uri = URI.create(String.format("/coupons/%s", created.getId()));
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            LOGGER.error("Unable to create new coupon : {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(path = "/coupons/{uuid}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> patch(@RequestBody CouponDTO dto, @PathVariable(value="uuid") UUID uuid){
        try {
            couponService.patch(dto, uuid);
            return ResponseEntity.accepted().build();
        } catch (EntityNotFoundException enfe) {
            LOGGER.error("Unable to patch coupon {} : {}", uuid, enfe.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error("Unable to patch coupon {} : {}", uuid, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "/coupons/{uuid}")
    public ResponseEntity<String> delete(@PathVariable(value="uuid") UUID uuid){
        try {
            couponService.delete(uuid);
            return ResponseEntity.accepted().build();
        } catch (EntityNotFoundException enfe) {
            LOGGER.error("Unable to delete coupon {} : {}", uuid, enfe.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error("Unable to delete coupon {} : {}", uuid, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/coupons", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> collectOptions(){
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .build();
    }

}
