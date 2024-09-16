package org.example.backend.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.example.backend.model.DocumentVersion;


@Component
public class DocumentVersionDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public DocumentVersionDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}
