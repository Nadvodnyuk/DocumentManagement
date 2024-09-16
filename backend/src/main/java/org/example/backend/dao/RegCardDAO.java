package org.example.backend.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.example.backend.model.RegCard;

import java.util.List;


@Component
public class RegCardDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public RegCardDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<RegCard> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from RegCard", RegCard.class).getResultList();
    }

    @Transactional(readOnly = true)
    public RegCard show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(RegCard.class, id);
    }

    @Transactional(readOnly = true)
    public RegCard showByDocumentId(int documentId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from RegCard where document.id = :documentId", RegCard.class)
                .setParameter("documentId", documentId)
                .uniqueResult();
    }

    @Transactional
    public void save(RegCard regCard) {
        Session session = sessionFactory.getCurrentSession();
        session.save(regCard);
    }

    @Transactional
    public void update(int id, RegCard updatedRegCard) {
        Session session = sessionFactory.getCurrentSession();
        RegCard cardToBeUpdated = session.get(RegCard.class, id);

        if (cardToBeUpdated != null) {
            cardToBeUpdated.setDocumentIntroNumber(updatedRegCard.getDocumentIntroNumber());
            cardToBeUpdated.setDocumentExternNumber(updatedRegCard.getDocumentExternNumber());
            cardToBeUpdated.setDateExtern(updatedRegCard.getDateExtern());
            session.update(cardToBeUpdated);
        } else {
            throw new IllegalArgumentException("RegCard with ID " + id + " not found");
        }
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        RegCard cardToBeDeleted = session.get(RegCard.class, id);

        if (cardToBeDeleted != null) {
            session.remove(cardToBeDeleted);
        } else {
            throw new IllegalArgumentException("RegCard with ID " + id + " not found");
        }
    }
}
