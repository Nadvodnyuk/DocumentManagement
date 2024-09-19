package org.example.backend.controller;

import org.apache.tika.Tika;
import org.example.backend.dto.DocumentVersionCreateDTO;
import org.example.backend.dto.DocumentVersionResponseDTO;
import org.example.backend.model.DocumentVersion;
import org.example.backend.service.DocumentVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/documentVersions")
@CrossOrigin(origins = "http://localhost:5000")
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

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadDocumentVersion(@PathVariable int id,
                                                            HttpServletResponse response) throws IOException {
        DocumentVersionResponseDTO documentVersionResponseDTO = documentVersionService.findById(id);

        if (documentVersionResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        byte[] content = documentVersionResponseDTO.getContent();
        if (content == null || content.length == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        Tika tika = new Tika();
        String mimeType = tika.detect(content);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
        InputStreamResource resource = new InputStreamResource(inputStream);

        List<DocumentVersionResponseDTO> allVersions = documentVersionService.findByDocumentId(documentVersionResponseDTO.getDocument().getDocumentId());

        allVersions.sort(Comparator.comparingInt(DocumentVersionResponseDTO::getDocumentVersionId));

        int versionNumber = 0;
        for (int i = 0; i < allVersions.size(); i++) {
            if (allVersions.get(i).getDocumentVersionId() == id) {
                versionNumber = i + 1;
                break;
            }
        }

        String fileName = documentVersionResponseDTO.getDocument().getDocumentName()+ "_" + versionNumber;

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
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
