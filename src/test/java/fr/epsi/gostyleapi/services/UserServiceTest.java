package fr.epsi.gostyleapi.services;

import fr.epsi.gostyleapi.external.dto.UserDTO;
import fr.epsi.gostyleapi.external.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class UserServiceTest {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    private final UserService userService = new UserService(bCryptPasswordEncoder);

    @Test
    void testConvertUserDtoToUserEntity() {
        UserEntity to;
        UserDTO from = new UserDTO("nom", "prenom", "pseudo", "mail", "mdp");

        to = userService.convertUserDtoToUserEntity(from);

        assertEquals(to.getNom(), from.getNom());
        assertEquals(to.getPrenom(), from.getPrenom());
        assertEquals(to.getPseudo(), from.getPseudo());
        assertEquals(to.getMail(), from.getMail());
        assertTrue(bCryptPasswordEncoder.matches(from.getMdp(), to.getMdp()));
    }

    @Test
    void testConvertUserDtosToUserEntities() {
        List<UserEntity> to;
        List<UserDTO> froms = new ArrayList<>();
        UserDTO from1 = new UserDTO("nom", "prenom", "pseudo", "mail", "mdp");
        UserDTO from2 = new UserDTO("nom2", "prenom2", "pseudo2", "mail2", "mdp2");
        froms.add(from1);
        froms.add(from2);

        to = userService.convertUserDtosToUserEntities(froms);

        assertEquals(2, to.size());
        // First item
        assertEquals(to.get(0).getNom(), froms.get(0).getNom());
        assertEquals(to.get(0).getPrenom(), froms.get(0).getPrenom());
        assertEquals(to.get(0).getPseudo(), froms.get(0).getPseudo());
        assertEquals(to.get(0).getMail(), froms.get(0).getMail());
        assertTrue(bCryptPasswordEncoder.matches(froms.get(0).getMdp(), to.get(0).getMdp()));
        // Second item
        assertEquals(to.get(1).getNom(), froms.get(1).getNom());
        assertEquals(to.get(1).getPrenom(), froms.get(1).getPrenom());
        assertEquals(to.get(1).getPseudo(), froms.get(1).getPseudo());
        assertEquals(to.get(1).getMail(), froms.get(1).getMail());
        assertTrue(bCryptPasswordEncoder.matches(froms.get(1).getMdp(), to.get(1).getMdp()));
    }
}