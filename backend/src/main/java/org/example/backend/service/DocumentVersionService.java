package org.example.backend.service;

import org.example.backend.dao.DocumentVersionDAO;
import org.example.backend.model.DocumentVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentVersionService {
    private final DocumentVersionDAO documentVersionDAO;

    @Autowired
    public DocumentVersionService(DocumentVersionDAO documentVersionDAO) {
        this.documentVersionDAO = documentVersionDAO;
    }

    @Transactional(readOnly = true)
    public List<DocumentVersion> findAll() {
        return documentVersionDAO.index();
    }

    @Transactional(readOnly = true)
    public DocumentVersion findById(int id) {
        return documentVersionDAO.show(id);
    }

    @Transactional
    public void save(DocumentVersion documentVersion) {
        documentVersionDAO.save(documentVersion);
    }

    @Transactional
    public void update(int id, DocumentVersion updatedDocumentVersion) {
        documentVersionDAO.update(id, updatedDocumentVersion);
    }

    @Transactional
    public void delete(int id) {
        documentVersionDAO.delete(id);
    }
}
