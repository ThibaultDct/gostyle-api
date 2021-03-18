package fr.epsi.gostyleapi.external.controllers;

import fr.epsi.gostyleapi.external.dto.CategorieDTO;
import fr.epsi.gostyleapi.external.entities.CategorieEntity;
import fr.epsi.gostyleapi.services.CategorieService;
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
public class CategorieController {

    private final Logger LOGGER = LoggerFactory.getLogger(CategorieController.class);

    @Autowired
    private CategorieService categorieService;

    @GetMapping("/categories")
    public List<CategorieEntity> getAll(){
        List<CategorieEntity> results = new ArrayList<>();
        try {
            results = categorieService.getAll();
        } catch (Exception e) {
            LOGGER.error("Unable to retrieve the list of categories : {}", e.getMessage());
        }
        return results;
    }

    @GetMapping("/categories/{uuid}")
    public CategorieEntity getById(@PathVariable(value="uuid") UUID uuid){
        CategorieEntity result = new CategorieEntity();
        try {
            result = categorieService.getById(uuid);
        } catch (Exception e) {
            LOGGER.error("Unable to retrieve categorie with id {} : {}", uuid, e.getMessage());
        }
        return result;
    }

    @PostMapping(path = "/categories", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> create(@RequestBody CategorieDTO dto){
        try {
            CategorieEntity created = categorieService.create(dto);
            URI uri = URI.create(String.format("/users/%s", created.getId()));
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            LOGGER.error("Unable to create new categorie : {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(path ="/categories/{uuid}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> patch(@RequestBody CategorieDTO dto, @PathVariable(value="uuid") UUID uuid){
        try {
            categorieService.patch(dto, uuid);
            return ResponseEntity.accepted().build();
        } catch (EntityNotFoundException enfe) {
            LOGGER.error("Unable to patch categorie {} : {}", uuid, enfe.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error("Unable to patch categorie {} : {}", uuid, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "/categories/{uuid}")
    public ResponseEntity<String> delete(@PathVariable(value="uuid") UUID uuid){
        try {
            categorieService.delete(uuid);
            return ResponseEntity.accepted().build();
        } catch (EntityNotFoundException enfe) {
            LOGGER.error("Unable to delete categorie {} : {}", uuid, enfe.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error("Unable to delete categorie {} : {}", uuid, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/categories", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> collectOptions(){
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .build();
    }

}
