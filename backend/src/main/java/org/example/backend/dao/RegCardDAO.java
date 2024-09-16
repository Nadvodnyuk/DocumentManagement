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

    @Transactional
    public void save(RegCard regCard) {
        Session session = sessionFactory.getCurrentSession();
        session.save(regCard);
    }

    @Transactional
    public void update(int id, RegCard updatedRegCard) {
        Session session = sessionFactory.getCurrentSession();
        RegCard cardToBeUpdated = session.get(RegCard.class, id);
        cardToBeUpdated.setDocumentIntroNumber(updatedRegCard.getDocumentIntroNumber());
        cardToBeUpdated.setDocumentExternNumber(updatedRegCard.getDocumentExternNumber());
        cardToBeUpdated.setDateExtern(updatedRegCard.getDateExtern());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(RegCard.class, id));
    }
}
