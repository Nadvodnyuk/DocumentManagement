package org.example.backend.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.example.backend.model.RegCard;


@Component
public class RegCardDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public RegCardDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
