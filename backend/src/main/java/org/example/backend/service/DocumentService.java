package org.example.backend.service;

import org.example.backend.dao.DocumentDAO;
import org.example.backend.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentService {
    private final DocumentDAO documentDAO;

    @Autowired
    public DocumentService(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Transactional(readOnly = true)
    public List<Document> findAll() {
        return documentDAO.index();
    }

    @Transactional(readOnly = true)
    public Document findById(int id) {
        return documentDAO.show(id);
    }

    @Transactional
    public void save(Document document) {
        documentDAO.save(document);
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
