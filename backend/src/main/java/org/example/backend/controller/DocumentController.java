package org.example.backend.controller;

import org.example.backend.dto.DocumentCreateDTO;
import org.example.backend.dto.DocumentResponseDTO;
import org.example.backend.model.Document;
import org.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import java.util.List;

@RestController
@MultipartConfig
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;
    private final DocumentVersionService documentVersionService;
    private final RegCardService regCardService;

    @Autowired
    public DocumentController(DocumentService documentService,
                              DocumentVersionService documentVersionService,
                              RegCardService regCardService) {
        this.documentService = documentService;
        this.documentVersionService = documentVersionService;
        this.regCardService = regCardService;
    }

    @GetMapping()
    public ResponseEntity<List<DocumentResponseDTO>> index() {
        try {
            List<DocumentResponseDTO> documents = documentService.findAll();
            return new ResponseEntity<>(documents, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponseDTO> show(@PathVariable("id") int id) {
        try {
            DocumentResponseDTO document = documentService.findById(id);
            return new ResponseEntity<>(document, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> create(@RequestPart("file") MultipartFile file,
                                         @ModelAttribute @Valid DocumentCreateDTO documentCreateDTO,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Invalid document data", HttpStatus.BAD_REQUEST);
        }
        try {
            int documentId = documentService.saveDocument(documentCreateDTO.getDocumentName(),
                    documentCreateDTO.getAuthor());

            documentVersionService.saveDocumentVersion(file,
                    documentCreateDTO.getAuthor(),
                    documentId);
            regCardService.saveRegCard(documentCreateDTO.getDocumentIntroNumber(),
                    documentCreateDTO.getDateIntro(),
                    documentId);

            return new ResponseEntity<>("Document created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating document", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id,
                                         @RequestBody @Valid Document document,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Invalid document data", HttpStatus.BAD_REQUEST);
        }
        try {
            documentService.update(id, document);
            return new ResponseEntity<>("Document updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating document", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            documentService.delete(id);
            return new ResponseEntity<>("Document deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error deleting document", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
