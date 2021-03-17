package fr.epsi.gostyleapi.services;

import fr.epsi.gostyleapi.external.dto.CouponDTO;
import fr.epsi.gostyleapi.external.entities.CouponEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
class CouponServiceTest {

    @InjectMocks
    private final CouponService couponService = new CouponService();

    @Test
    void convertCouponDtoToCouponEntity() {
        CouponEntity to;
        CouponDTO from = new CouponDTO("Nantes", 5, "Promotion", 10);

        to = couponService.convertCouponDtoToCouponEntity(from);

        assertEquals(to.getEmplacement(), from.getEmplacement());
        assertEquals(to.getNb_utilisation(), from.getNb_utilisation());
        assertEquals(to.getLibelle(), from.getLibelle());
        assertEquals(to.getPourc_reduc(), from.getPourc_reduc());
    }

    @Test
    void convertCouponDtosToCouponEntities() {
        List<CouponEntity> to;
        List<CouponDTO> froms = new ArrayList<>();
        CouponDTO from1 = new CouponDTO("Nantes", 5, "Promotion", 10);
        CouponDTO from2 = new CouponDTO("Le Mans", 6, "Promotion 2", 20);
        froms.add(from1);
        froms.add(from2);

        to = couponService.convertCouponDtosToCouponEntities(froms);

        assertEquals(2, to.size());
        // First item
        assertEquals(to.get(0).getEmplacement(), froms.get(0).getEmplacement());
        assertEquals(to.get(0).getNb_utilisation(), froms.get(0).getNb_utilisation());
        assertEquals(to.get(0).getLibelle(), froms.get(0).getLibelle());
        assertEquals(to.get(0).getPourc_reduc(), froms.get(0).getPourc_reduc());
        // Second item
        assertEquals(to.get(1).getEmplacement(), froms.get(1).getEmplacement());
        assertEquals(to.get(1).getNb_utilisation(), froms.get(1).getNb_utilisation());
        assertEquals(to.get(1).getLibelle(), froms.get(1).getLibelle());
        assertEquals(to.get(1).getPourc_reduc(), froms.get(1).getPourc_reduc());
    }
}