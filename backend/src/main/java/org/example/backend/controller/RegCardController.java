package org.example.backend.controller;

import org.example.backend.model.RegCard;
import org.example.backend.service.RegCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/regCards")
public class RegCardController {
    private final RegCardService regCardService;

    @Autowired
    public RegCardController(RegCardService regCardService) {
        this.regCardService = regCardService;
    }

    @GetMapping()
    public ResponseEntity<List<RegCard>> index() {
        try {
            List<RegCard> regCards = regCardService.findAll();
            return new ResponseEntity<>(regCards, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegCard> show(@PathVariable("id") int id) {
        try {
            RegCard regCard = regCardService.findById(id);
            return new ResponseEntity<>(regCard, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody @Valid RegCard regCard, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Invalid registration card data", HttpStatus.BAD_REQUEST);
        }
        try {
            regCardService.save(regCard);
            return new ResponseEntity<>("Registration card created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating registration card", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody @Valid RegCard regCard, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Invalid registration card data", HttpStatus.BAD_REQUEST);
        }
        try {
            regCardService.update(id, regCard);
            return new ResponseEntity<>("Registration card updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating registration card", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            regCardService.delete(id);
            return new ResponseEntity<>("Registration card deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error deleting registration card", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
