package org.example.backend.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.example.backend.model.Document;

import java.util.List;


@Component
public class DocumentDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public DocumentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Document> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Document", Document.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Document show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Document.class, id);
    }

    @Transactional
    public void save(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.save(document);
    }

    @Transactional
    public void update(int id, Document updatedDocument) {
        Session session = sessionFactory.getCurrentSession();
        Document documentToBeUpdated = session.get(Document.class, id);

        if (documentToBeUpdated != null) {
            documentToBeUpdated.setDocumentName(updatedDocument.getDocumentName());
            documentToBeUpdated.setAuthor(updatedDocument.getAuthor());
            session.update(documentToBeUpdated);
        } else {
            throw new IllegalArgumentException("Document with ID " + id + " not found");
        }
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Document documentToBeDeleted = session.get(Document.class, id);

        if (documentToBeDeleted != null) {
            session.remove(documentToBeDeleted);
        } else {
            throw new IllegalArgumentException("Document with ID " + id + " not found");
        }
    }
}