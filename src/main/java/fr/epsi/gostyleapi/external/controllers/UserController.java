package fr.epsi.gostyleapi.external.controllers;

import fr.epsi.gostyleapi.external.dto.UserDTO;
import fr.epsi.gostyleapi.external.entities.UserEntity;
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
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserEntity> getAll(){
        List<UserEntity> results = new ArrayList<>();
        try {
            results = userService.getAll();
        } catch (Exception e) {
            LOGGER.error("Unable to retrieve the list of users : {}", e.getMessage());
        }
        return results;
    }

    @GetMapping("/users/{uuid}")
    public UserEntity getById(@PathVariable(value="uuid") UUID uuid){
        UserEntity result = new UserEntity();
        try {
            result = userService.getById(uuid);
        } catch (Exception e) {
            LOGGER.error("Unable to retrieve user with id {} : {}", uuid, e.getMessage());
        }
        return result;
    }

    @PostMapping(path = "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> create(@RequestBody UserDTO dto){
        try {
            UserEntity created = userService.create(dto);
            URI uri = URI.create(String.format("/users/%s", created.getId()));
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            LOGGER.error("Unable to create new user : {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(path ="/users/{uuid}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> patch(@RequestBody UserDTO dto, @PathVariable(value="uuid") UUID uuid){
        try {
            userService.patch(dto, uuid);
            return ResponseEntity.accepted().build();
        } catch (EntityNotFoundException enfe) {
            LOGGER.error("Unable to patch user {} : {}", uuid, enfe.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error("Unable to patch user {} : {}", uuid, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "/users/{uuid}")
    public ResponseEntity<String> delete(@PathVariable(value="uuid") UUID uuid){
        try {
            userService.delete(uuid);
            return ResponseEntity.accepted().build();
        } catch (EntityNotFoundException enfe) {
            LOGGER.error("Unable to delete user {} : {}", uuid, enfe.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error("Unable to delete user {} : {}", uuid, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> collectOptions(){
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .build();
    }

}
