package org.example.backend.controller;

import org.example.backend.dto.DocumentVersionCreateDTO;
import org.example.backend.dto.DocumentVersionResponseDTO;
import org.example.backend.model.DocumentVersion;
import org.example.backend.service.DocumentVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/documentVersions")
public class DocumentVersionController {
    private final DocumentVersionService documentVersionService;

    @Autowired
    public DocumentVersionController(DocumentVersionService documentVersionService) {
        this.documentVersionService = documentVersionService;
    }

    @GetMapping()
    public ResponseEntity<List<DocumentVersionResponseDTO>> index() {
        try {
            List<DocumentVersionResponseDTO> documentVersionResponseDTO = documentVersionService.findAll();
            return new ResponseEntity<>(documentVersionResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentVersionResponseDTO> show(@PathVariable("id") int id) {
        try {
            DocumentVersionResponseDTO documentVersionResponseDTO = documentVersionService.findById(id);
            return new ResponseEntity<>(documentVersionResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byDocument/{documentId}")
    public ResponseEntity<List<DocumentVersionResponseDTO>> findByDocumentId(@PathVariable("documentId") int documentId) {
        try {
            List<DocumentVersionResponseDTO> documentVersionResponseDTO = documentVersionService.findByDocumentId(documentId);
            return new ResponseEntity<>(documentVersionResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestParam("file") MultipartFile file,
                                         @ModelAttribute @Valid DocumentVersionCreateDTO documentVersionCreateDTO,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Invalid document version data", HttpStatus.BAD_REQUEST);
        }
        try {
            documentVersionService.save(file, documentVersionCreateDTO);
            return new ResponseEntity<>("Document version created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating document version", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id,
                                         @RequestBody @Valid DocumentVersion documentVersion,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Invalid document version data", HttpStatus.BAD_REQUEST);
        }
        try {
            documentVersionService.update(id, documentVersion);
            return new ResponseEntity<>("Document version updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating document version", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            documentVersionService.delete(id);
            return new ResponseEntity<>("Document version deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error deleting document version", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
