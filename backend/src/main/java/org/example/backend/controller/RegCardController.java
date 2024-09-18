package org.example.backend.controller;

import org.example.backend.dto.RegCardResponseDTO;
import org.example.backend.dto.RegCardUpdateDTO;
import org.example.backend.model.RegCard;
import org.example.backend.service.RegCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/regCards")
@CrossOrigin(origins = "http://localhost:5173")
public class RegCardController {
    private final RegCardService regCardService;

    @Autowired
    public RegCardController(RegCardService regCardService) {
        this.regCardService = regCardService;
    }

    @GetMapping()
    public ResponseEntity<List<RegCardResponseDTO>> index() {
        try {
            List<RegCardResponseDTO> regCards = regCardService.findAll();
            return new ResponseEntity<>(regCards, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegCardResponseDTO> show(@PathVariable("id") int id) {
        try {
            RegCardResponseDTO regCardResponseDTO = regCardService.findById(id);
            return new ResponseEntity<>(regCardResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    // скорее всего не нужно, это до тестов
    @GetMapping("/document/{documentId}")
    public ResponseEntity<?> findByDocumentId(@PathVariable("documentId") int documentId) {
        try {
            RegCardResponseDTO regCardDTO = regCardService.findByDocumentId(documentId);
            return new ResponseEntity<>(regCardDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
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

    @PatchMapping("/{regCardId}")
    public ResponseEntity<String> update(@PathVariable("regCardId") int regCardId,
                                         @RequestBody @Valid RegCardUpdateDTO regCardUpdateDTO,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Invalid registration card data", HttpStatus.BAD_REQUEST);
        }
        try {
            regCardService.update(regCardId, regCardUpdateDTO);
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
