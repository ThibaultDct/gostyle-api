package fr.epsi.gostyleapi.services;

import fr.epsi.gostyleapi.external.dto.CategorieDTO;
import fr.epsi.gostyleapi.external.entities.CategorieEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class CategorieServiceTest {

    @InjectMocks
    private final CategorieService categorieService = new CategorieService();

    @Test
    void testConvertCategorieDtoToCategorieEntity() {
        CategorieEntity to;
        CategorieDTO from = new CategorieDTO("Sport");

        to = categorieService.convertCategorieDtoToCategorieEntity(from);

        assertEquals(to.getNom(), from.getNom());
    }

    @Test
    void testConvertCategorieDtosToCategorieEntities() {
        List<CategorieEntity> to;
        List<CategorieDTO> froms = new ArrayList<>();
        CategorieDTO from1 = new CategorieDTO("Sport");
        CategorieDTO from2 = new CategorieDTO("Musique");
        froms.add(from1);
        froms.add(from2);

        to = categorieService.convertCouponDtosToCouponEntities(froms);

        assertEquals(2, to.size());
        // First item
        assertEquals(to.get(0).getNom(), froms.get(0).getNom());
        // Second item
        assertEquals(to.get(1).getNom(), froms.get(1).getNom());
    }
}