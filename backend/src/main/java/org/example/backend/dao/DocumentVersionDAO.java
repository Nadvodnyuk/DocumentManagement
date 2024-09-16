package org.example.backend.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.example.backend.model.DocumentVersion;

import java.util.List;


@Component
public class DocumentVersionDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public DocumentVersionDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<DocumentVersion> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from DocumentVersion", DocumentVersion.class).getResultList();
    }

    @Transactional(readOnly = true)
    public DocumentVersion show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(DocumentVersion.class, id);
    }

    @Transactional
    public void save(DocumentVersion documentVersion) {
        Session session = sessionFactory.getCurrentSession();
        session.save(documentVersion);
    }

    @Transactional
    public void update(int id, DocumentVersion updatedDocumentVersion) {
        Session session = sessionFactory.getCurrentSession();
        DocumentVersion versionToBeUpdated = session.get(DocumentVersion.class, id);
        versionToBeUpdated.setVersionAuthor(updatedDocumentVersion.getVersionAuthor());
        versionToBeUpdated.setContent(updatedDocumentVersion.getContent());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(DocumentVersion.class, id));
    }
}
