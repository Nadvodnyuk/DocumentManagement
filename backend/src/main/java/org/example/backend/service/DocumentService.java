package org.example.backend.service;

import org.example.backend.dao.DocumentDAO;
import org.example.backend.dto.DocumentResponseDTO;
import org.example.backend.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    private final DocumentDAO documentDAO;

    @Autowired
    public DocumentService(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Transactional(readOnly = true)
    public List<DocumentResponseDTO> findAll() {
        List<Document> documents = documentDAO.index();
        System.out.println(documents);
        return documents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DocumentResponseDTO findById(int id) {
        Document document = documentDAO.show(id);
        System.out.println(document);
        return convertToDTO(document);
    }

    @Transactional(readOnly = true)
    public Document findDocumentById(int id) {
        Document document = documentDAO.show(id);
        return document;
    }

    private DocumentResponseDTO convertToDTO(Document document) {
        DocumentResponseDTO dto = new DocumentResponseDTO();
        dto.setDocumentId(document.getDocumentId());
        dto.setDocumentName(document.getDocumentName());
        dto.setAuthor(document.getAuthor());
        return dto;
    }

    @Transactional
    public int saveDocument(String documentName,
                                    String author) throws IOException {
        if (author == null || documentName == null) {
            throw new IllegalArgumentException("Invalid input data");
        }

        Document document = new Document();
        document.setDocumentName(documentName);
        document.setAuthor(author);
        documentDAO.save(document);
        return document.getDocumentId();
    }

    @Transactional
    public void update(int id, Document updatedDocument) {
        documentDAO.update(id, updatedDocument);
    }

    @Transactional
    public void delete(int id) {
        documentDAO.delete(id);
    }
}
